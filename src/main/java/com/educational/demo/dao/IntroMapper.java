package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Article;
import com.educational.demo.model.Intro;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-24 19:46
 */
@Repository
public interface IntroMapper extends BaseMapper<Intro> {

    /**
     * 查询医生详情
     * @param page
     * @param queryWrapper
     * @return
     */
    Page<Intro> listTableByPage(IPage<Intro> page, @Param("ew") QueryWrapper<Intro> queryWrapper);

    /**
     * 查询医生简介
     * @param page
     * @return
     */
    Page<Intro> listPreviewByPage(IPage<Intro> page, @Param("categoryId") Long categoryId);


    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    Intro selectById(Long id);
}
