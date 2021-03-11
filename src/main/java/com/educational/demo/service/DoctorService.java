package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Doctor;
import com.educational.demo.model.Registration;
import com.educational.demo.query.DoctorQuery;
import com.educational.demo.query.RegistrationQuery;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-06 12:09
 */
public interface DoctorService {

    /**
     * 根据科室id和挂号类型id获取相应的医生
     * @param departmentId 科室id
     * @param typeId 类型id
     * @return 医生列表
     */
    List<Doctor> ListByDptIdandTypeId(Integer departmentId, Integer typeId);


    /**
     * 分页查询所有的医生
     * @param current 当前页
     * @param size 页面大小
     * @param doctorQuery 查询条件
     * @return 医生列表
     */
    Page<Doctor> listTableByPage(int current, int size, DoctorQuery doctorQuery);

    /**
     * 根据id删除
     * @param id 医生id
      */
    void deleteById(Integer id);

    /**
     * 根据idList删除数据
     * @param idList 医生idList
     */
    void deleteByIdList(List<Integer> idList);
}
