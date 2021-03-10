package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Registration;
import com.educational.demo.model.RegistrationType;
import com.educational.demo.query.RegistrationQuery;
import com.educational.demo.query.RegistrationTypeQuery;

import java.util.List;
import java.util.function.LongFunction;

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

    /**
     * 分页查询所有挂号类型
     *
     * @param current   当前页码
     * @param size      页面大小
     * @param registrationTypeQuery 条件
     * @return 挂号列表
     */
    Page<RegistrationType> listTableByPage(int current, int size, RegistrationTypeQuery registrationTypeQuery);

    /**
     * @param id 类型Id
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     * @param idList id 列表
      */
    void deleteByIdList(List<Integer> idList);

    /**
     * 保存或者更新
     * @param registrationType 类型对象
     */
    void saveOrUpdate(RegistrationType registrationType);

    /**
     * 编辑页面使用
     * @param id 类型id
     * @return 类型对象
     */
    RegistrationType getById(Integer id);
}
