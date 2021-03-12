package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Registration;
import com.educational.demo.model.Units;
import com.educational.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-08 22:54
 */
@Repository
public interface UnitsMapper extends BaseMapper<Units> {

    /**
     * 分页查询分页
     * @param page 分页参数
     * @return 单位类列表
     */
    Page<Units> listTableByPage(IPage<Units> page,@Param("ew") QueryWrapper<Units> queryWrapper);

}
