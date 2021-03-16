package com.educational.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.anntation.AccessLog;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Project;
import com.educational.demo.query.ProjectQuery;
import com.educational.demo.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-13 15:26
 */
@Api(tags = "后台：项目管理")
@RestController
@RequestMapping("/admin/project")
public class AdminProjectController {

    @Autowired
    private ProjectService projectService;

    @ApiOperation("查询项目")
    @PreAuthorize("hasAuthority('sys:project:query')")
    @AccessLog("访问项目页面")
    @GetMapping
    public TableResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  ProjectQuery projectQuery) {
        Page<Project> pageInfo = projectService.listTableByPage(page, limit, projectQuery);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }


    @ApiOperation("删除项目")
    @PreAuthorize("hasAuthority('sys:project:delete')")
    @DeleteMapping("/{id}")
    public JsonResult deleteById(@NotNull @PathVariable("id") Integer id){
        projectService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除项目")
    @PreAuthorize("hasAuthority('sys:project:delete')")
    @DeleteMapping
    public JsonResult deleteByIdList(@NotEmpty @RequestBody List<Integer> idList){
        projectService.deleteByIdList(idList);
        return JsonResult.ok();
    }

    @ApiOperation("增加项目")
    @PreAuthorize("hasAuthority('sys:project:add')")
    @PostMapping
    public JsonResult save(@NotEmpty @RequestBody Project project){
        project.setCreateTime(new Date());
        project.setUpdateTime(project.getCreateTime());
        projectService.saveOrUpdate(project);
        return JsonResult.ok();
    }

    @ApiOperation("修改项目")
    @PreAuthorize("hasAuthority('sys:project:edit')")
    @PutMapping
    public JsonResult update(@NotEmpty @RequestBody Project project){
        project.setUpdateTime(new Date());
        projectService.saveOrUpdate(project);
        return JsonResult.ok();
    }
}
