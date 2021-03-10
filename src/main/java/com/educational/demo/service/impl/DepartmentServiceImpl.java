package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.educational.demo.dao.DepartmentMapper;
import com.educational.demo.exception.EntityExistException;
import com.educational.demo.model.Department;
import com.educational.demo.model.Menu;
import com.educational.demo.model.RegistrationType;
import com.educational.demo.model.Role;
import com.educational.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Department> listAll() {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.select(Department.Table.ID,Department.Table.DEPARTMENTNAME,Department.Table.PID,Department.Table.CREATE_TIME,Department.Table.UPDATE_TIME);
        return departmentMapper.selectList(wrapper);
    }

    @Override
    public void saveOrUpdate(Department department) {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        if (department.getDepartmentId() ==null){
            wrapper.eq(Department.Table.DEPARTMENTNAME,department.getDepartmentName());
            if (null != departmentMapper.selectOne(wrapper)){
                throw new EntityExistException("科室名：" + department.getDepartmentName() + "已存在");
            }
            departmentMapper.insert(department);
        }else {
            wrapper.eq(Department.Table.DEPARTMENTNAME,department.getDepartmentName());
            List<Department> departments= departmentMapper.selectList(wrapper);
            departments =  departments.stream().filter(r -> !r.getDepartmentName().equals(department.getDepartmentName())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty( departments)) {
                throw new EntityExistException("类型名：" + department.getDepartmentName() + "已存在");
            }
            departmentMapper.updateById(department);
        }
    }

    @Override
    public Department selectById(Integer id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        departmentMapper.deleteById(id);
    }

    @Override
    public void deleteByPid(Integer pid) {
        departmentMapper.deleteByPid(pid);
    }


}
