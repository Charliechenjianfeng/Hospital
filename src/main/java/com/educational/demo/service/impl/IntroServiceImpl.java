package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.TableConstant;
import com.educational.demo.dao.IntroMapper;
import com.educational.demo.model.Article;
import com.educational.demo.model.Intro;
import com.educational.demo.query.IntorQuery;
import com.educational.demo.service.IntroService;
import com.educational.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-24 18:51
 */
@Service
public class IntroServiceImpl implements IntroService {

    @Autowired
    private IntroMapper introMapper;

    @Override
    public void saveOrUpdate(Intro intro) {
        if (intro.getId() == null){
            introMapper.insert(intro);
        }else{
            introMapper.updateById(intro);
        }
    }

    @Override
    public Page<Intro> listTableByPage(Integer current, Integer size, IntorQuery intorQuery) {

        Page<Intro> page = new Page<>(current, size);
        QueryWrapper<Intro> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(intorQuery.getDoctorName())){
            wrapper.like(Intro.Table.NAME, intorQuery.getDoctorName());
        }
        if (intorQuery.getStartDate() != null && intorQuery.getEndDate() != null) {
            wrapper.between(TableConstant.INTRO_ALIAS + Article.Table.CREATE_TIME, intorQuery.getStartDate(), intorQuery.getEndDate());
        }
        return introMapper.listTableByPage(page,wrapper);
    }

    @Override
    public void deleteById(Long id) {
            introMapper.deleteById(id);
    }

    @Override
    public void deleteByIdList(List<Long> idList) {
        introMapper.deleteBatchIds(idList);
    }

    @Override
    public Intro getById(Long id) {
        return introMapper.selectById(id);
    }

    @Override
    public Page<Intro> listIntroPageByCategoryId(Integer current, Integer size, Long categoryId) {
        Page<Intro> page = new Page<>(current, size);
        return introMapper.listPreviewByPage(page,categoryId);
    }

    @Override
    public Intro selectById(Long id) {
        return introMapper.selectById(id);
    }


}
