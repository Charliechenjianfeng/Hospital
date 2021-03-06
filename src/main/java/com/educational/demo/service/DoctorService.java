package com.educational.demo.service;

import com.educational.demo.model.Doctor;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-06 12:09
 */
public interface DoctorService {

    /**
     * 根据部门和挂号类型列出相应的医生
     * @return 医生列表
     */
    List<Doctor> ListByDptIdandTypeId();
}
