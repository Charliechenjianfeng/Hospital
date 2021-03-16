package com.educational.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Registration;
import com.educational.demo.query.RegistrationQuery;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-01 23:33
 * 挂号管理
 */
public interface RegistrationService {

    /**
     * 分页查询所有挂号
     *
     * @param current   当前页码
     * @param size      页面大小
     * @param registrationQuery 条件
     * @return 挂号列表
     */
    Page<Registration> listTableByPage(int current, int size, RegistrationQuery registrationQuery);

    /**
     * 提供给医生使用
     * @param current 当前叶
     * @param size 大小
     * @param registrationQuery 查询条件
     * @param id 医生id
     * @return 分页数据
     */
    Page<Registration> listTableById(int current, int size, RegistrationQuery registrationQuery, Integer id);

    /**
     * 更新或者保存
     * @param registration 挂号信息
     */
    void saveOfUpdate(Registration registration);

    /**
     * 根据id删除
     * @param id 挂号id
     */
    void removeByRegistrationId(Long id);

    /**
     * 批量删除
     * @param idList id列表
     */
    void removeByIdList(List<Long> idList);

    /**
     * 修改挂号状态
     * @param registration 挂号
     */
    void changeStatus(Registration registration);

    /**
     * 提供编辑使用
     * @param id 挂号id
     * @return 该id的对象
     */
    Registration selectById(Long id);

    /**
     * 结束问诊
     * @param finishId
     */
    void finish(Long finishId);

    /**
     * 添加查询病情
     * @param registrationId
     * @param description
     */
    void addDescribe(Long registrationId , String description);
}
