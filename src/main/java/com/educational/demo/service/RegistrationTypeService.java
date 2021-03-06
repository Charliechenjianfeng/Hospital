package com.educational.demo.service;

import com.educational.demo.model.RegistrationType;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-06 23:28
 */
public interface RegistrationTypeService {

    /**
     * 供下拉框使用
     * @return 挂号类型的所有数据
     */
    List<RegistrationType> listAll();


    /**
     * 根据id获取该价格
     * @param id 类型id
     * @return 价格
     */
    Double listPriceById(Integer id);
}
