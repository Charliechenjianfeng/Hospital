package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.TableConstant;
import com.educational.demo.dao.DoctorMapper;
import com.educational.demo.exception.EntityExistException;
import com.educational.demo.model.Department;
import com.educational.demo.model.Doctor;
import com.educational.demo.query.DoctorQuery;
import com.educational.demo.service.DoctorService;
import com.educational.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Page<Doctor> listTableByPage(int current, int size, DoctorQuery doctorQuery) {
        Page<Doctor> page = new Page<>(current,size);
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(doctorQuery.getDoctorName())){
            wrapper.like(Doctor.Table.DOCTORNAME,doctorQuery.getDoctorName());
        }
        if (!StringUtils.isEmpty(doctorQuery.getPhone())){
            wrapper.like(Doctor.Table.PHONE,doctorQuery.getPhone());
        }
        if (doctorQuery.getEndDate()!=null && doctorQuery.getEndDate()!=null){
            wrapper.between(TableConstant.DOCTOR_ALIAS+Doctor.Table.CREATE_TIME,doctorQuery.getStartDate(),doctorQuery.getEndDate());
        }
        return doctorMapper.listTableByPage(page, wrapper);
    }

    @Override
    public void deleteById(Integer id) {
        doctorMapper.deleteById(id);
    }

    @Override
    public void deleteByIdList(List<Integer> idList) {
        doctorMapper.deleteBatchIds(idList);
    }

    @Override
    public void saveOrUpdate(Doctor doctor) {
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        if (doctor.getDoctorId() == null){
            wrapper.eq(Doctor.Table.PHONE,doctor.getPhone());
            if (null != (doctorMapper.selectOne(wrapper))){
                throw new EntityExistException("手机号：" + doctor.getPhone() + "已存在");
            }
            doctorMapper.insert(doctor);
        }else{
            wrapper.eq(Doctor.Table.PHONE,doctor.getPhone());
            List<Doctor> doctors= doctorMapper.selectList(wrapper);
            doctors =  doctors.stream().filter(r -> !r.getPhone().equals(doctor.getPhone())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(doctors)){
                throw new EntityExistException("手机号：" + doctor.getPhone() + "已存在");
            }
            doctorMapper.updateById(doctor);
        }
    }

    @Override
    public Doctor selectById(Integer id) {
        return doctorMapper.selectById(id);
    }
}
