package com.educational.demo.service;


import com.educational.demo.model.Department;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-05 21:34
 */
public interface DepartmentService {

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<Department> listAllPid();

    /**
     * 根据父id
     * @param id 父Id
     * @return id列表
     */
    List<Department> listByPid(Integer id);
}
