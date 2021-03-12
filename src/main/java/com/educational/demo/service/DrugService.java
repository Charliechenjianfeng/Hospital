package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Doctor;
import com.educational.demo.model.Drugdictionary;
import com.educational.demo.query.DoctorQuery;
import com.educational.demo.query.DrugQuery;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-12 20:21
 */
public interface DrugService {

    /**
     * 分页查询
     * @param current
     * @param size
     * @param drugQuery
     * @return
     */
    Page<Drugdictionary> listTableByPage(int current, int size, DrugQuery drugQuery);


    /**
     * 单条删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 多条删除
     * @param idList
     */
    void deleteByIdList(List<Integer> idList);

    /**
     *
     * @param drugdictionary
     */
    void saveOrUpdate(Drugdictionary drugdictionary);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Drugdictionary selectById(Integer id);
}
