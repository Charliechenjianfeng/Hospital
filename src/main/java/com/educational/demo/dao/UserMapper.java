package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-17 15:07
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 后台分页查询所有用户
     * @param page 分页参数
     * @param queryWrapper 条件
     * @return 用户分页信息
     */
    Page<User> listTableByPage(IPage<User> page, @Param("ew") QueryWrapper<User> queryWrapper);

}
