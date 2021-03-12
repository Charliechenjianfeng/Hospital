package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.TableConstant;
import com.educational.demo.dao.DrugMapper;
import com.educational.demo.exception.EntityExistException;
import com.educational.demo.model.Doctor;
import com.educational.demo.model.Drugdictionary;
import com.educational.demo.query.DrugQuery;
import com.educational.demo.service.DrugService;
import com.educational.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-12 20:31
 */
@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugMapper drugMapper;

    @Override
    public Page<Drugdictionary> listTableByPage(int current, int size, DrugQuery drugQuery) {
        Page<Drugdictionary> page = new Page<>(current,size);
        QueryWrapper<Drugdictionary> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(drugQuery.getDrugName())){
            wrapper.like(Drugdictionary.Table.DRUGNAEM,drugQuery.getDrugName());
        }
        if (drugQuery.getEndDate()!=null && drugQuery.getEndDate()!=null){
            wrapper.between(TableConstant.DRUG_ALIAS+Drugdictionary.Table.CREATE_TIME, drugQuery.getStartDate(),drugQuery.getEndDate());
        }
        return drugMapper.listTableByPage(page,wrapper);
    }

    @Override
    public void deleteById(Integer id) {
        drugMapper.deleteById(id);
    }

    @Override
    public void deleteByIdList(List<Integer> idList) {
        drugMapper.deleteBatchIds(idList);
    }

    @Override
    public void saveOrUpdate(Drugdictionary drugdictionary) {
        QueryWrapper<Drugdictionary> wrapper = new QueryWrapper<>();
        if (drugdictionary.getDrugId()==null){
            wrapper.eq(Drugdictionary.Table.DRUGNAEM,drugdictionary.getDrugName());
            if (null != drugMapper.selectOne(wrapper)){
                throw new EntityExistException("药品名称：" + drugdictionary.getDrugName() + "已存在");
            }
            drugMapper.insert(drugdictionary);
        }else {
                wrapper.eq(Drugdictionary.Table.DRUGNAEM, drugdictionary.getDrugName());
                List<Drugdictionary> drugdictionaries = drugMapper.selectList(wrapper);
                drugdictionaries =  drugdictionaries.stream().filter(r -> !r.getDrugName().equals(drugdictionary.getDrugName())).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(drugdictionaries)){
                    throw new EntityExistException("药品名称：" + drugdictionary.getDrugName() + "已存在");
                }
                drugMapper.updateById(drugdictionary);
        }
    }

    @Override
    public Drugdictionary selectById(Integer id) {
        return drugMapper.selectById(id);
    }
}
