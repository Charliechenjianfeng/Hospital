package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Billingdetails;
import com.educational.demo.model.Doctor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-14 23:02
 */
@Repository
public interface BillingDetailsMapper extends BaseMapper<Billingdetails> {

    /**
     * 根据挂号id查询当天的账单
     * @param page
     * @param registrationId
     * @return
     */
    Page<Billingdetails> listTableByRegistrationId(IPage<Billingdetails> page, @Param("registrationId") Long registrationId);


    /**
     * 获取总价
     * @param registrationId
     * @return
     */
    Double getTotalPrice(@Param("registrationId") Integer registrationId);

    /**
     * 更新支付状态
     * @param registrationId
     */
    void payment(@Param("registrationId") Integer registrationId);
}
