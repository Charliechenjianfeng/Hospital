package com.educational.demo.service.impl;

import com.educational.demo.dao.RegistrationTypeMapper;
import com.educational.demo.model.RegistrationType;
import com.educational.demo.service.RegistrationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-06 23:29
 */
@Service
public class RegistrationTypeServiceImpl implements RegistrationTypeService {

    @Autowired
    private RegistrationTypeMapper registrationTypeMapper;

    @Override
    public List<RegistrationType> listAll() {
        return registrationTypeMapper.listAll();
    }

    @Override
    public Double listPriceById(Integer id) {
        return registrationTypeMapper.listPriceById(id);
    }
}
