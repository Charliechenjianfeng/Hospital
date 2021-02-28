package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-17 20:30
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 后台分页查询角色
     * @param page 分页参数
     * @param queryWrapper 条件
     * @return 角色分页信息
     */
    Page<Role> listTableByPage(IPage<Role> page, @Param("ew") QueryWrapper<Role> queryWrapper);
}
