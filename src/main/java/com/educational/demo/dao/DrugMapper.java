package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Doctor;
import com.educational.demo.model.Drugdictionary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-12 20:31
 */
@Repository
public interface DrugMapper extends BaseMapper<Drugdictionary> {

    Page<Drugdictionary> listTableByPage(IPage<Drugdictionary> page, @Param("ew") QueryWrapper<Drugdictionary> queryWrapper);
}
