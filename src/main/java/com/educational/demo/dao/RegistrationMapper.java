package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Registration;
import com.educational.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.springframework.stereotype.Repository;

import java.util.function.LongFunction;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-02 15:38
 */
@Repository
public interface RegistrationMapper extends BaseMapper<Registration> {
    /**
     * 根据条件列出所有的挂号
     * @param page 分页参数
     * @param queryWrapper 条件
     * @return 分页好的数据
     */
    Page<Registration> listTableByPage(IPage<Registration> page, @Param("ew") QueryWrapper<Registration> queryWrapper);

    /**
     * 根据id查询
     * 提供给医生使用
     * @param page 分页参数
     * @param queryWrapper 查询条件
     * @return 查询出来
     */
    Page<Registration> listTableById(IPage<Registration> page, @Param("ew") QueryWrapper<Registration> queryWrapper, Integer id);

    /**
     * 根据id删除
     * @param id 挂号id
     */
    void deleteByRegistrationId(Long id);


    /**
     * 转院操作
     * @param registrationId 挂号id
     * @param status 状态
     */
    void transfer(Long registrationId, Integer status);




}
