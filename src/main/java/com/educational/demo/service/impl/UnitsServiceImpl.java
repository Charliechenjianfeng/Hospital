package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.TableConstant;
import com.educational.demo.dao.UnitsMapper;
import com.educational.demo.exception.EntityExistException;
import com.educational.demo.model.Registration;
import com.educational.demo.model.Units;
import com.educational.demo.query.UnitsQuery;
import com.educational.demo.service.UnitsService;
import com.educational.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-08 22:45
 */
@Service
public class UnitsServiceImpl implements UnitsService {

    @Autowired
    private UnitsMapper unitsMapper;

    @Override
    public Page<Units> listTableByPage(int current, int size, UnitsQuery unitsQuery) {
        Page<Units> page = new Page<>(current,size);
        QueryWrapper<Units> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(unitsQuery.getUnitsName())){
            wrapper.like(Units.Table.UNITSNAME, unitsQuery.getUnitsName());
        }
        if (unitsQuery.getStartDate()!=null&& unitsQuery.getEndDate()!=null){
            wrapper.between(TableConstant.UNITS_ALIAS+Registration.Table.CREATE_TIME,unitsQuery.getStartDate(),unitsQuery.getEndDate());
        }

        return unitsMapper.listTableByPage(page, wrapper);
    }

    @Override
    public void removeById(Long id) {
        unitsMapper.deleteById(id);
    }

    @Override
    public void removeByIdList(List<Long> idList) {
        unitsMapper.deleteBatchIds(idList);
    }

    @Override
    public void saveOrUpdate(Units units) {
        QueryWrapper<Units> wrapper = new QueryWrapper<>();
        wrapper.eq(Units.Table.UNITSNAME, units.getUnitsName());
        if (null != unitsMapper.selectOne(wrapper)){
            throw new EntityExistException("单位", "名称重复", units.getUnitsName());
        }

        if (units.getUnitsId()==null){
            unitsMapper.insert(units);
        } else {
            unitsMapper.updateById(units);
        }
    }

    @Override
    public Units getById(Long id) {
      return unitsMapper.selectById(id);
    }
}
