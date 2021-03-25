package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Intro;
import com.educational.demo.query.IntorQuery;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-24 16:47
 */
public interface IntroService {

    /**
     * 增加或者保存
     * @param intro 介绍对象
     */
    void saveOrUpdate(Intro intro);

    /**
     * 分页查询所有的介绍
     * @param current
     * @param size
     * @param intorQuery
     * @return
     */
    Page<Intro> listTableByPage(Integer current, Integer size, IntorQuery intorQuery);

    /**
     * 根据id删除
     * @param id 介绍id
     */
    void deleteById(Long id);

    /**
     * 批量删除
     * @param idList idList
     */
    void deleteByIdList(List<Long> idList);


    /**
     * 通过id获取简介
     * @param id
     * @return
     */
    Intro getById(Long id);

    /**
     * 根据分类id获取分类简介
     * @param current
     * @param size
     * @param categoryId
     * @return
     */
    Page<Intro> listIntroPageByCategoryId(Integer current, Integer size, Long categoryId);

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    Intro selectById(Long id);


}
