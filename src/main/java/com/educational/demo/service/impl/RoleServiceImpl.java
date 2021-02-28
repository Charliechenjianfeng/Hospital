package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.dao.RoleMapper;
import com.educational.demo.dao.RoleMenuMapper;
import com.educational.demo.dao.RoleUserMapper;
import com.educational.demo.dao.UserMapper;
import com.educational.demo.exception.AssociationExistException;
import com.educational.demo.exception.EntityExistException;
import com.educational.demo.model.Role;
import com.educational.demo.model.RoleMenu;
import com.educational.demo.model.RoleUser;
import com.educational.demo.model.User;
import com.educational.demo.query.RoleQuery;
import com.educational.demo.service.RoleService;
import com.educational.demo.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-17 20:48
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleUserMapper roleUserMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserMapper userMapper;



    @Override
    public List<Role> listAll() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.select(Role.Table.ID,Role.Table.ROLE_NAME);
        return roleMapper.selectList(wrapper);
    }

    @Override
    public Page<Role> listTableByPage(Integer current, Integer size, RoleQuery roleQuery) {
        Page<Role> page = new Page<>(current, size);
        QueryWrapper<Role> roleWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(roleQuery.getRoleName())) {
            roleWrapper.like(Role.Table.ROLE_NAME, roleQuery.getRoleName());
        }
        if (!StringUtils.isEmpty(roleQuery.getDescription())) {
            roleWrapper.like(Role.Table.DESCRIPTION, roleQuery.getDescription());
        }
        if (roleQuery.getStartDate() != null && roleQuery.getEndDate() != null) {
            roleWrapper.between(Role.Table.CREATE_TIME, roleQuery.getStartDate(), roleQuery.getEndDate());
        }

        return roleMapper.listTableByPage(page,roleWrapper);
    }

    @Override
    public void saveOfUpdate(Role role) {
        if (role.getId() == null) {
            //保存
            //检查角色名称是否唯一
            QueryWrapper<Role> wrapper = new QueryWrapper<>();
            wrapper.eq(Role.Table.ROLE_NAME, role.getRoleName());
            if (!CollectionUtils.isEmpty(roleMapper.selectList(wrapper))) {
                throw new EntityExistException("角色名称：" + role.getRoleName() + "已存在");
            }
            //保存角色
            roleMapper.insert(role);
            //保存角色权限
            roleMenuMapper.insertBatch(role.getId(), role.getMenuIdList());
        } else {
            //更新
            //检查角色名称是否唯一
            QueryWrapper<Role> roleWrapper = new QueryWrapper<>();
            roleWrapper.eq(Role.Table.ROLE_NAME, role.getRoleName());
            List<Role> roles = roleMapper.selectList(roleWrapper);
            roles = roles.stream().filter(r -> !r.getId().equals(role.getId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(roles)) {
                throw new EntityExistException("角色名称：" + role.getRoleName() + "已存在");
            }
            //更新角色
            roleMapper.updateById(role);
            //更新角色权限
            //先删除原有角色权限
            QueryWrapper<RoleMenu> roleMenuWrapper = new QueryWrapper<>();
            roleMenuWrapper.eq(RoleMenu.Table.ROLE_ID, role.getId());
            roleMenuMapper.delete(roleMenuWrapper);
            //再添加新的角色权限
            roleMenuMapper.insertBatch(role.getId(), role.getMenuIdList());
        }
    }

    @Override
    public Role getById(Long id) {
       Role role = new Role();
       QueryWrapper <Role> roleQueryWrapper = new QueryWrapper<>();
       roleQueryWrapper.select(Role.Table.ID, Role.Table.ROLE_NAME, Role.Table.DESCRIPTION, Role.Table.RANK, Role.Table.COLOR, Role.Table.STATUS).eq(Role.Table.ID, id);
       Role role1 = roleMapper.selectOne(roleQueryWrapper);
        BeanUtils.copyProperties(role1,role);
        //查询角色的权限
        QueryWrapper<RoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
        roleMenuQueryWrapper.select(RoleMenu.Table.MENU_ID)
                .eq(RoleMenu.Table.ROLE_ID,id);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(roleMenuQueryWrapper);
        List<Long> menuIdList = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        role.setMenuIdList(menuIdList);
        return role;
    }

    @Override
    public void removeById(Long id) {
        QueryWrapper<RoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq(RoleUser.Table.ROLE_ID,id);
        //角色存在关联用户不允许删除
        Integer count  = roleUserMapper.selectCount(wrapper);
        if (count>=1){
            throw new AssociationExistException("该角色存在关联用户，不能删除");
        }
        roleMapper.deleteById(id);
    }

    @Override
    public void removeByIdList(List<Long> idList) {
        for (Long id : idList){
            QueryWrapper<RoleUser> roleUserQueryWrapper = new QueryWrapper<>();
            Integer count = roleUserMapper.selectCount(roleUserQueryWrapper);
            if(count>=1){
                QueryWrapper<Role> roleQueryWrapper =new QueryWrapper<>();
                roleQueryWrapper.select(Role.Table.ROLE_NAME).eq(Role.Table.ID,id);
                Role role= roleMapper.selectOne(roleQueryWrapper);
                throw new AssociationExistException("角色："+role.getRoleName()+"存在关联用户，不能删除");
            }
        }
    }

    @Override
    public void changeStatus(Role role) {

       //更新角色状态
        roleMapper.updateById(role);
       //个性用户状态
        QueryWrapper<RoleUser> wrapper = new QueryWrapper<>();
        wrapper.select(RoleUser.Table.USER_ID).eq(RoleUser.Table.ROLE_ID,role.getId());
        List<RoleUser> roleUsers = roleUserMapper.selectList(wrapper);
        for(RoleUser roleUser :roleUsers){
            User user = new User();
            user.setId(roleUser.getUserId());
            user.setStatus(role.getStatus());
            userMapper.updateById(user);
        }
    }
}
