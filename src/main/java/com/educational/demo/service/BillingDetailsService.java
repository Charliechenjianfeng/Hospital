package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Billingdetails;
import com.educational.demo.model.Doctor;
import com.educational.demo.query.DoctorQuery;
import com.educational.demo.vo.MonthCountVO;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-14 22:51
 */
public interface BillingDetailsService {

    /**
     * 当医生问诊的时候加入
     * @param billingdetails
     */
    void insertDrugOrProject(Billingdetails billingdetails);

    /**
     * 根据id查询账单
     * @param current
     * @param size
     * @param registrationId
     * @return
     */
    Page<Billingdetails> listTableByRegistrationId(int current, int size, Long registrationId);

    /**
     * 获取总价
     * @param registration
     * @return
     */
    Double getTotalPrice(Integer registrationId);

    /**
     * 用户支付功能
     * @param registrationId
     */
    void payment(Integer registrationId);

    /**
     * 计算当天收入
     * @return
     */
    Double countOneDay();


    /**
     * 根据月份统计
     * @return
     */
    List<MonthCountVO> monthCount();

}
