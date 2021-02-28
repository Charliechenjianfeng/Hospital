package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.educational.demo.common.Constant;
import com.educational.demo.dao.MenuMapper;
import com.educational.demo.exception.EntityExistException;
import com.educational.demo.model.Menu;
import com.educational.demo.service.MenuService;
import com.educational.demo.util.MenuTreeUtil;
import com.educational.demo.vo.InitInfoVO;
import com.educational.demo.vo.MenuCheckboxVO;
import com.educational.demo.vo.MenuSelectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-17 17:17
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public InitInfoVO menu(Long userId) {
        List<Menu> menuList=menuMapper.listMenuByUserId(userId);
        return InitInfoVO.init(menuList);
    }

    @Override
    public List<Menu> listAll() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.select(Menu.Table.ID, Menu.Table.PID, Menu.Table.TITLE, Menu.Table.HREF, Menu.Table.AUTHORITY, Menu.Table.ICON, Menu.Table.SORT, Menu.Table.TYPE, Menu.Table.STATUS, Menu.Table.CREATE_TIME, Menu.Table.UPDATE_TIME);
        return menuMapper.selectList(wrapper);
    }

    @Override
    public List<MenuSelectVO> listBySelectTree() {
        QueryWrapper<Menu> wrapper= new QueryWrapper<>();
        wrapper.select(Menu.Table.ID,Menu.Table.PID,Menu.Table.TITLE);
        List<Menu> menus = menuMapper.selectList(wrapper);
        List<MenuSelectVO> treeList = new ArrayList<>();
        for (Menu menu : menus){
            MenuSelectVO menuSelectVO = new MenuSelectVO();
            menuSelectVO.setValue(menu.getId());
            menuSelectVO.setName(menu.getTarget());
            menuSelectVO.setPid(menu.getPid());
            treeList.add(menuSelectVO);
        }
        return MenuTreeUtil.toSelectTree(treeList, Constant.MENU_TREE_START);
    }

    @Override
    public Long countAll() {
        return Long.valueOf(menuMapper.selectCount(null));
    }

    @Override
    public void saveOrUpdate(Menu menu) {
        if (menu.getId()==null){
            //保存
            QueryWrapper<Menu> wrapper = new QueryWrapper<>();
            wrapper.eq(Menu.Table.TITLE,menu.getTitle());
            if (!CollectionUtils.isEmpty(menuMapper.selectList(wrapper))){
                throw new EntityExistException("菜单标题："+menu.getTitle()+"已经存在");
            }
            menuMapper.insert(menu);
        }else {
            //更新
            QueryWrapper<Menu> wrapper = new QueryWrapper<>();
            wrapper.eq(Menu.Table.TITLE,menu.getTitle());
            List<Menu> menus = menuMapper.selectList(wrapper);
            menus = menus.stream().filter(m -> !m.getId().equals(menu.getId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(menus)){
                throw new EntityExistException("菜单标题：" +menu.getTitle()+"已经存在");
            }
            menuMapper.updateById(menu);
        }
    }

    @Override
    public Menu getById(Long id) {
        return menuMapper.selectById(id);
    }

    @Override
    public void removeById(Long id) {
        menuMapper.deleteById(id);
    }

    @Override
    public List<MenuCheckboxVO> listByCheckboxTree() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.select(Menu.Table.ID,Menu.Table.PID,Menu.Table.TITLE);
        List<Menu> menus = menuMapper.selectList(wrapper);
        List<MenuCheckboxVO> treeList = new ArrayList<>();
        for (Menu menu : menus){
            MenuCheckboxVO menuCheckboxVO = new MenuCheckboxVO();
            menuCheckboxVO.setId(menu.getId());
            menuCheckboxVO.setParentId(menu.getPid());
            menuCheckboxVO.setTitle(menu.getTitle());
            menuCheckboxVO.setCheckArr(Constant.MENU_TREE_NOT_SELECTED);
            treeList.add(menuCheckboxVO);
        }
        return MenuTreeUtil.toCheckBoxTree(treeList,Constant.MENU_TREE_START);
    }
}
