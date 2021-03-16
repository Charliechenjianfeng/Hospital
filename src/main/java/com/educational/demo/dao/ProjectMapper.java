package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Project;
import com.educational.demo.model.Registration;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-13 15:41
 */
@Repository
public interface ProjectMapper extends BaseMapper<Project> {
    /**
     * 列出所有的
     * @param page 分页参数
     * @param queryWrapper 查询条件
     * @return
     */
    Page<Project> listTableByPage(IPage<Project> page, @Param("ew") QueryWrapper<Project> queryWrapper);
}
