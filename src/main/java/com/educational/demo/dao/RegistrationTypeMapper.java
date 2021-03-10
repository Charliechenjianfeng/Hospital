package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Registration;
import com.educational.demo.model.RegistrationType;
import com.educational.demo.query.RegistrationTypeQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-06 23:30
 */
@Repository
public interface RegistrationTypeMapper extends BaseMapper<RegistrationType> {
    /**
     * 列出所有的
     * @return
     */
    List<RegistrationType> listAll();


    /**
     * 根据id查找价格
     * @param id 类型id
     * @return Double类型的价格
     */
    Double listPriceById(@Param("id") Integer id);

    /**
     *
     * @param page 页面参数
     * @param queryWrapper 查询调价
     * @return 分页后的数据
     */
    Page<RegistrationType> listTableByPage(IPage<RegistrationType> page, @Param("ew") QueryWrapper<RegistrationType> queryWrapper);
}
