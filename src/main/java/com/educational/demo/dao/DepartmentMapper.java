package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educational.demo.model.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-05 22:11
 */
@Repository
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 查询出科室大类
     * @return List
     */
    List<Department> listAllPid();

    /**
     *
     * @param id 父id
     * @return idList
     */
    List<Department> listByPid(@Param("id") Integer id);

    /**
     * 根据父id删除
     * @param id id
     */
    void deleteByPid(@Param("id") Integer id);

}
