package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.dao.BillingDetailsMapper;
import com.educational.demo.model.Billingdetails;
import com.educational.demo.model.Doctor;
import com.educational.demo.service.BillingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-14 22:59
 */
@Service
public class BillingDetailsServiceImpl implements BillingDetailsService {

    @Autowired
    private BillingDetailsMapper billingDetailsMapper;

    @Override
    public void insertDrugOrProject(Billingdetails billingdetails) {
        billingDetailsMapper.insert(billingdetails);
    }

    @Override
    public Page<Billingdetails> listTableByRegistrationId(int current, int size, Long registrationId) {
        Page<Billingdetails> page = new Page<>(current,size);
        return billingDetailsMapper.listTableByRegistrationId(page,registrationId);
    }

    @Override
    public Double getTotalPrice(Integer registrationId) {
        return billingDetailsMapper.getTotalPrice(registrationId);
    }

    @Override
    public void payment(Integer registrationId) {
        billingDetailsMapper.payment(registrationId);
    }
}
