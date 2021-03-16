package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.TableConstant;
import com.educational.demo.dao.RegistrationMapper;
import com.educational.demo.exception.EntityExistException;
import com.educational.demo.model.Registration;
import com.educational.demo.model.Role;
import com.educational.demo.model.User;
import com.educational.demo.query.RegistrationQuery;
import com.educational.demo.service.RegistrationService;
import com.educational.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        if (registration.getRegistrationId() == null){
            //判断手机号是否唯一
            wrapper.eq(Registration.Table.PHONE, registration.getPhone());
            if (null != registrationMapper.selectOne(wrapper)){
                throw new EntityExistException("挂号", "电话", registration.getPhone());
            }
            wrapper.clear();
            wrapper.eq(Registration.Table.IDCARD, registration.getIdCard());
            if (null != registrationMapper.selectOne(wrapper)){
                throw new EntityExistException("挂号","身份证",registration.getIdCard());
            }
            registrationMapper.insert(registration);
        } else {
            QueryWrapper<Registration> registrationWrapper = new QueryWrapper<>();
            registrationWrapper.eq(Registration.Table.PATIENTNAME, registration.getPatientName());
            List<Registration> registrations = registrationMapper.selectList(registrationWrapper);
            registrations = registrations.stream().filter(r -> !r.getPhone().equals(registration.getPhone())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(registrations)) {
                throw new EntityExistException("电话号码：" + registration.getPhone() + "已存在");
            }
            registrationWrapper.clear();
            registrationWrapper.eq(Registration.Table.IDCARD,  registration.getIdCard());
            List<Registration> registrations1 = registrationMapper.selectList(registrationWrapper);
            registrations1 = registrations1.stream().filter(r -> !r.getIdCard().equals(registration.getIdCard())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(registrations1)) {
                throw new EntityExistException("身份证：" + registration.getIdCard() + "已存在");
            }

            registrationMapper.updateById(registration);
        }
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
        Integer status = registration.getRstatus();
        registrationMapper.transfer(registrationId,status);
    }

    @Override
    public Registration selectById(Long id) {
        return registrationMapper.selectById(id);
    }

    @Override
    public void finish(Long finishId) {
        registrationMapper.finish(finishId);
    }

    @Override
    public void addDescribe(Long registrationId, String description) {
        registrationMapper.addDescribe(registrationId,description);
    }


}
