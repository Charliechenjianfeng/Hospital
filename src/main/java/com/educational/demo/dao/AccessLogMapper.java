package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.AccessLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-10-11 23:06
 */
@Repository
public interface AccessLogMapper extends BaseMapper<AccessLog> {


    /**
     * 分页查询所有数据
     * @param page 分页参数
     * @param queryWrapper 调剂
     * @return 分页数据
     */
    Page<AccessLog> listTableByPage(IPage<AccessLog> page, @Param("ew") QueryWrapper<AccessLog> queryWrapper);
}
