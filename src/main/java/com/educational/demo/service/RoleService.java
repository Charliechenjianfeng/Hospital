package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Role;
import com.educational.demo.query.RoleQuery;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-17 14:50
 */
public interface RoleService {
    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<Role> listAll();

    /**
     * 分页查询所有角色
     *
     * @param current   当前页码
     * @param size      页面大小
     * @param roleQuery 条件
     * @return 角色列表
     */
    Page<Role> listTableByPage(Integer current, Integer size, RoleQuery roleQuery);

    /**
     * 保存或者更新角色
     *
     * @param role 角色
     */
    void saveOfUpdate(Role role);

    /**
     * 根据ID获取角色
     *
     * @param id 角色ID
     * @return 角色
     */
    Role getById(Long id);

    /**
     * 根据id删除角色
     *
     * @param id 角色ID
     */
    void removeById(Long id);

    /**
     * 根据id列表批量删除角色
     *
     * @param idList 角色ID列表
     */
    void removeByIdList(List<Long> idList);

    /**
     * 修改角色状态
     *
     * @param role 角色
     */
    void changeStatus(Role role);
}
