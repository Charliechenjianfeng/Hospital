package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Units;
import com.educational.demo.query.UnitsQuery;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-08 22:43
 */
public interface UnitsService {

    /**
     * 分页查询所有单位
     *
     * @param current   当前页码
     * @param size      页面大小
     * @return 单位列表
     */
    Page<Units> listTableByPage(int current, int size, UnitsQuery unitsQuery);

    /**
     * 根据id删除
     * @param id 单位id
     */
    void removeById(Long id);

    /**
     * 根据idList批量删除
     * @param idList id列表
     */
    void removeByIdList(List<Long> idList);

    /**
     * 保存或更新单位
     * @param units 单位
     */
    void saveOrUpdate(Units units);

    /**
     * 获取单位
     * @param id 单位id
     * @return 单位对象
     */
    Units getById(Long id);

    /**
     * 列出所有的单位
     * @return
     */
    List<Units> listAll();
}
