package com.educational.demo.service.impl;

import com.educational.demo.dao.DoctorMapper;
import com.educational.demo.model.Doctor;
import com.educational.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-07 17:48
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public List<Doctor> ListByDptIdandTypeId(Integer departmentId, Integer typeId) {
        return doctorMapper.ListByDptIdandTypeId(departmentId, typeId);
    }
}
