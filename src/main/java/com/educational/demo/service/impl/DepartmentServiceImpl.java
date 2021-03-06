package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.educational.demo.dao.DepartmentMapper;
import com.educational.demo.model.Department;
import com.educational.demo.model.Role;
import com.educational.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-05 21:39
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> listAllPid() {
      return departmentMapper.listAllPid();
    }

    @Override
    public List<Department> listByPid(Integer id) {
        return departmentMapper.listByPid(id);
    }


}
