package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.TableConstant;
import com.educational.demo.dao.RoleUserMapper;
import com.educational.demo.dao.UserMapper;
import com.educational.demo.exception.BadRequestException;
import com.educational.demo.exception.EntityExistException;
import com.educational.demo.model.RoleUser;
import com.educational.demo.model.User;
import com.educational.demo.query.UserQuery;
import com.educational.demo.service.UserService;
import com.educational.demo.vo.UserInfoVO;
import com.educational.demo.vo.UserLoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-17 16:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleUserMapper roleUserMapper;

    @Override
    public Page<User> listTableByPage(int current, int size, UserQuery userQuery) {
        Page<User> page = new Page<>(current,size);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userQuery.getUsername())){
            wrapper.like(User.Table.USERNAME,userQuery.getUsername());
        }
        if (!StringUtils.isEmpty(userQuery.getEmail())) {
            wrapper.like(User.Table.EMAIL,userQuery.getEmail());
        }
        if (userQuery.getStartDate()!=null&& userQuery.getEndDate()!=null){
            wrapper.between(TableConstant.USER_ALIAS+User.Table.CREATE_TIME,userQuery.getStartDate(),userQuery.getEndDate());
        }

        return userMapper.listTableByPage(page,wrapper);
    }

    @Override
    public User checkUser(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select(User.Table.ID,User.Table.USERNAME)
                .eq(User.Table.USERNAME,username)
                .eq(User.Table.PASSWORD,password);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public void saveOfUpdate(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (user.getId() == null) {
            //保存
            //验证用户名是否唯一
            wrapper.eq(User.Table.USERNAME, user.getUsername());
            if (null != userMapper.selectOne(wrapper)) {
                throw new EntityExistException("用户", "用户名", user.getUsername());
            }
            //验证邮箱是否唯一
            wrapper.clear();
            wrapper.eq(User.Table.EMAIL, user.getEmail());
            if (null != userMapper.selectOne(wrapper)) {
                throw new EntityExistException("用户", "邮箱", user.getEmail());
            }
            //验证手机号码是否唯一
            wrapper.clear();
            wrapper.eq(User.Table.PHONE, user.getPhone());
            if (null != userMapper.selectOne(wrapper)) {
                throw new EntityExistException("用户", "手机号码", user.getPhone());
            }
            userMapper.insert(user);
            RoleUser roleUser = new RoleUser();
            roleUser.setRoleId(user.getRoleId());
            roleUser.setUserId(user.getId());
            roleUserMapper.insert(roleUser);
        } else {
            //更新
            //验证手机号码是否唯一
            wrapper.eq(User.Table.PHONE, user.getPhone());
            List<User> users = userMapper.selectList(wrapper);
            users = users.stream().filter(u -> !u.getId().equals(user.getId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(users)) {
                throw new EntityExistException("用户", "手机号码", user.getPhone());
            }
            //验证邮箱是否唯一
            wrapper.clear();
            wrapper.eq(User.Table.EMAIL, user.getPhone());
            users = userMapper.selectList(wrapper);
            users = users.stream().filter(u -> !u.getId().equals(user.getId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(users)) {
                throw new EntityExistException("用户", "邮箱", user.getEmail());
            }
            //首先更新用户
            userMapper.updateById(user);
            //再添加用户角色关联
            RoleUser roleUser = new RoleUser();
            roleUser.setUserId(user.getId());
            roleUser.setRoleId(user.getRoleId());
            QueryWrapper<RoleUser> roleUserWrapper = new QueryWrapper<>();
            roleUserWrapper.eq(RoleUser.Table.USER_ID, user.getId());
            if (!CollectionUtils.isEmpty(roleUserMapper.selectList(roleUserWrapper))) {
                //有记录则先删除
                roleUserMapper.delete(roleUserWrapper);
            }
            //最后添加
            roleUserMapper.insert(roleUser);
        }
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void changeStatus(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void removeById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void removeByIdList(List<Long> idList) {
        userMapper.deleteBatchIds(idList);

    }

    @Override
    public void changePassword(UserLoginVO passwordVO) {
        QueryWrapper<User> wrapper =new QueryWrapper<>();
        wrapper.select(User.Table.PASSWORD).eq(User.Table.ID,passwordVO.getUserId());
        User user =userMapper.selectOne(wrapper);
        //对密码进行加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //判断旧密码是否匹配
        if (!encoder.matches(passwordVO.getOldPassword(),user.getPassword())){
            throw new BadRequestException(HttpStatus.BAD_REQUEST,"旧密码输入不正确");
        }
        user.setId(passwordVO.getUserId());
        user.setPassword(encoder.encode(passwordVO.getNewPassword()));
        userMapper.updateById(user);

    }

    @Override
    public void updateInfo(UserInfoVO userInfoVO) {
        //验证手机号码是否唯一
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(User.Table.PHONE, userInfoVO.getPhone());
        List<User> users = userMapper.selectList(wrapper);
        //Stream操作
        users = users.stream().filter(u -> !u.getId().equals(userInfoVO.getId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(users)) {
            throw new EntityExistException("用户", "手机号码", userInfoVO.getPhone());
        }

        //验证邮箱是否唯一
        wrapper.clear();
        wrapper.eq(User.Table.EMAIL, userInfoVO.getPhone());
        users = userMapper.selectList(wrapper);
        users = users.stream().filter(u -> !u.getId().equals(userInfoVO.getId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(users)) {
            throw new EntityExistException("用户", "邮箱", userInfoVO.getEmail());
        }
    }


    @Override
    public Integer countAll() {

        return userMapper.selectCount(null);
    }
}
