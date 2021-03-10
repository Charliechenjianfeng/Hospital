package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.dao.RegistrationTypeMapper;
import com.educational.demo.exception.EntityExistException;
import com.educational.demo.model.Registration;
import com.educational.demo.model.RegistrationType;
import com.educational.demo.query.RegistrationTypeQuery;
import com.educational.demo.service.RegistrationTypeService;
import com.educational.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Page<RegistrationType> listTableByPage(int current, int size, RegistrationTypeQuery registrationTypeQuery) {
        Page<RegistrationType> page = new Page<>(current,size);
        QueryWrapper<RegistrationType> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(registrationTypeQuery.getType())){
            wrapper.like(RegistrationType.Table.TYPE,registrationTypeQuery.getType());
        }
        return registrationTypeMapper.listTableByPage(page,wrapper);
    }

    @Override
    public void deleteById(Integer id) {
        registrationTypeMapper.deleteById(id);
    }

    @Override
    public void deleteByIdList(List<Integer> idList) {
         registrationTypeMapper.deleteBatchIds(idList);
    }

    @Override
    public void saveOrUpdate(RegistrationType registrationType) {
        QueryWrapper<RegistrationType> wrapper = new QueryWrapper<>();
        if (registrationType.getTypeId()==null){
            wrapper.eq(RegistrationType.Table.TYPE,registrationType.getType());
            if (null != registrationTypeMapper.selectOne(wrapper)){
                throw new EntityExistException("类型：" + registrationType.getType() + "已存在");
            }
            registrationTypeMapper.insert(registrationType);
        }else{
            wrapper.eq(RegistrationType.Table.TYPE, registrationType.getType());
            List<RegistrationType> registrationTypes = registrationTypeMapper.selectList(wrapper);
            registrationTypes = registrationTypes.stream().filter(r -> !r.getType().equals(registrationType.getType())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(registrationTypes)) {
                throw new EntityExistException("类型名：" + registrationType.getType() + "已存在");
            }
            registrationTypeMapper.updateById(registrationType);
        }
    }

    @Override
    public RegistrationType getById(Integer id) {
        return registrationTypeMapper.selectById(id);
    }
}
