package com.educational.demo.service;


import com.educational.demo.model.Menu;
import com.educational.demo.vo.InitInfoVO;
import com.educational.demo.vo.MenuCheckboxVO;
import com.educational.demo.vo.MenuSelectVO;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-17 14:39
 */
public interface MenuService {

    /**
     * 获取菜单树
     * @param userId 用户ID
     * @return 菜单树
     */
    InitInfoVO menu(Long userId);

    /**
     *查询所有的菜单
     * @return 菜单列表
     */
    List<Menu> listAll();

    /**
     * 查询菜单树(单选)
     *
     * @return 菜单树
     */
    List<MenuSelectVO> listBySelectTree();

    /**
     * 统计菜单条数
     * @return 菜单数
     */
    Long countAll();

    /**
     * 保存或者更新菜单
     * @param menu 菜单
     */
    void saveOrUpdate(Menu menu);

    /**
     * 根据Id查询菜单
     * @param id 菜单Id
     * @return 菜单
     */
    Menu getById(Long id);


    /**
     * 根据Id删除订单
     * @param id
     */
    void removeById(Long id);

    /**
     * 查询菜单树（复选）
     * @return 菜单树
     */
    List<MenuCheckboxVO> listByCheckboxTree();
}
