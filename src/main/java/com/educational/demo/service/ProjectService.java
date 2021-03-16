package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Drugdictionary;
import com.educational.demo.model.Project;
import com.educational.demo.query.DrugQuery;
import com.educational.demo.query.ProjectQuery;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-13 15:32
 */
public interface ProjectService {

    /**
     * 列出所有项目
     * @param current 当前页
     * @param size 大小
     * @param projectQuery 查询条件
     * @return
     */
    Page<Project> listTableByPage(int current, int size, ProjectQuery projectQuery);


    /**
     * 删除id
     * @param id
     */
    void deleteById(Integer id);


    /**
     * 批量删除
     * @param idList
     */
    void deleteByIdList(List<Integer> idList);

    /**
     * 更新或者保存
     * @param project
     */
    void saveOrUpdate(Project project);

    /**
     * 修改使用
     * @param id
     * @return
     */
    Project selectById(Integer id);
}
