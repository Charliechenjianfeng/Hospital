package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.TableConstant;
import com.educational.demo.dao.RegistrationMapper;
import com.educational.demo.model.Registration;
import com.educational.demo.query.RegistrationQuery;
import com.educational.demo.service.RegistrationService;
import com.educational.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-02 15:36
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public Page<Registration> listTableByPage(int current, int size, RegistrationQuery registrationQuery) {
        Page<Registration> page = new Page<>(current,size);
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(registrationQuery.getPatientName())){
            wrapper.like(Registration.Table.PATIENTNAME,registrationQuery.getPatientName());
        }
        if (!StringUtils.isEmpty(registrationQuery.getPhone())){
            wrapper.like(Registration.Table.PHONE, registrationQuery.getPhone());
        }
        if (registrationQuery.getStartDate()!=null&& registrationQuery.getEndDate()!=null){
            wrapper.between(TableConstant.REGISTRATION_ALIAS+Registration.Table.CREATE_TIME,registrationQuery.getStartDate(),registrationQuery.getEndDate());
        }
        return registrationMapper.listTableByPage(page,wrapper);
    }

    @Override
    public Page<Registration> listTableById(int current, int size, RegistrationQuery registrationQuery, Integer id) {
        Page<Registration> page = new Page<>(current,size);
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(registrationQuery.getPatientName())){
            wrapper.like(Registration.Table.PATIENTNAME,registrationQuery.getPatientName());
        }
        if (!StringUtils.isEmpty(registrationQuery.getPhone())){
            wrapper.like(Registration.Table.PHONE, registrationQuery.getPhone());
        }
        if (registrationQuery.getStartDate()!=null&& registrationQuery.getEndDate()!=null){
            wrapper.between(TableConstant.REGISTRATION_ALIAS+Registration.Table.CREATE_TIME,registrationQuery.getStartDate(),registrationQuery.getEndDate());
        }
        return registrationMapper.listTableById(page, wrapper, id);
    }


    @Override
    public void saveOfUpdate(Registration registration) {

    }

    @Override
    public void removeByRegistrationId(Long id) {
       registrationMapper.deleteByRegistrationId(id);

    }

    @Override
    public void removeByIdList(List<Long> idList) {
        for (Long id : idList){
            registrationMapper.deleteByRegistrationId(id);
        }
    }

    @Override
    public void changeStatus(Registration registration) {
        Long registrationId = registration.getRegistrationId();
        Integer status = registration.getStatus();
        registrationMapper.transfer(registrationId,status);
    }

}
