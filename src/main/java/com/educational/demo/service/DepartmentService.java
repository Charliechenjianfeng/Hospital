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

    /**
     * 列出所有科室
     * @return 科室list
     */
    List<Department> listAll();

    /**
     * 新增或修改科室
     * @param department 科室对象
     */
    void saveOrUpdate(Department department);

    /**
     * 根据id查对象
     * @param id 科室id
     * @return  科室对象
     */
    Department selectById(Integer id);

    /**
     * 根据id删除
     * @param id id
     */
    void deleteById(Integer id);

    /**
     * 根据父id删除
     * @param pid
     */
    void deleteByPid(Integer pid);
}
