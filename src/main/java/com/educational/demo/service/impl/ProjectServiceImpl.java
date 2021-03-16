package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.TableConstant;
import com.educational.demo.dao.ProjectMapper;
import com.educational.demo.exception.EntityExistException;
import com.educational.demo.model.Project;
import com.educational.demo.model.Registration;
import com.educational.demo.query.ProjectQuery;
import com.educational.demo.service.ProjectService;
import com.educational.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-13 15:37
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Page<Project> listTableByPage(int current, int size, ProjectQuery projectQuery) {
        Page<Project> page = new Page<>(current,size);
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(projectQuery.getProjectName())){
            wrapper.like(Project.Table.PROJECTNAME,projectQuery.getProjectName());
        }
        if (!StringUtils.isEmpty(projectQuery.getStartDate()) && projectQuery.getEndDate()!=null){
            wrapper.between(TableConstant.PROJECT_ALIAS+Project.Table.CREATE_TIME,projectQuery.getStartDate(),projectQuery.getEndDate());
        }
        return projectMapper.listTableByPage(page,wrapper);
    }

    @Override
    public void deleteById(Integer id) {
        projectMapper.deleteById(id);
    }

    @Override
    public void deleteByIdList(List<Integer> idList) {
        projectMapper.deleteBatchIds(idList);
    }

    @Override
    public void saveOrUpdate(Project project) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        if (project.getProjectId() == null){
            wrapper.eq(Project.Table.PROJECTNAME, project.getProjectName());
            if (null !=projectMapper.selectOne(wrapper)){
                throw new EntityExistException("项目", "项目名", project.getProjectName());
            }
            projectMapper.insert(project);
        }else{
            wrapper.eq(Project.Table.PROJECTNAME, project.getProjectName());
            List<Project> projects = projectMapper.selectList(wrapper);
            projects = projects.stream().filter(r->!r.getProjectName().equals(project.getProjectName())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(projects)){
                throw new EntityExistException("项目", "项目名", project.getProjectName());
            }
            projectMapper.updateById(project);
        }
    }

    @Override
    public Project selectById(Integer id) {
        return projectMapper.selectById(id);
    }
}
