package com.educational.demo.controller;

import com.educational.demo.common.JsonResult;
import com.educational.demo.model.Department;
import com.educational.demo.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-05 21:25
 */
@Api(tags = "后台：科室管理")
@RestController
@RequestMapping("/admin/department")
public class AdminDepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("查询主科室")
    @PreAuthorize("hasAuthority('sys:department:query')")
    @GetMapping("/list")
    public JsonResult listAll() {
        return JsonResult.ok(departmentService.listAllPid());
    }

    @ApiOperation("查询分科室")
    @PreAuthorize("hasAuthority('sys:department:query')")
    @GetMapping("/list/{id}")
    public JsonResult listByPid(@NotNull @PathVariable("id") Integer id) {
        return JsonResult.ok(departmentService.listByPid(id));
    }

    @ApiOperation("查询科室")
    @PreAuthorize("hasAuthority('sys:department:query')")
    @GetMapping
    public JsonResult listAllDepartment(){
        return JsonResult.ok(departmentService.listAll());
    }

    @ApiOperation("添加科室")
    @PreAuthorize("hasAuthority('sys:department:add')")
    @PostMapping
    public JsonResult save(@Validated @RequestBody Department department){
        if (department.getDepartmentId()==null){
            department.setPid(0);
        }else {
            department.setPid(department.getDepartmentId());
            department.setDepartmentId(null);
        }
        departmentService.saveOrUpdate(department);
        return JsonResult.ok();
    }


    @ApiOperation("修改科室")
    @PreAuthorize("hasAuthority('sys:department:edit')")
    @PutMapping("/{id}")
    public JsonResult update(@Validated @RequestBody Department department,@NotNull @PathVariable("id") Integer id){
        department.setPid(id);
        departmentService.saveOrUpdate(department);
        return JsonResult.ok();
    }

    @ApiOperation("查询分科室")
    @PreAuthorize("hasAuthority('sys:department:query')")
    @DeleteMapping("/{id}")
    public JsonResult delete(@NotNull @PathVariable("id") Integer id){
        Department department = departmentService.selectById(id);
        //如果是主目录
        if (department.getPid()==0){
            //删除子目录
            departmentService.deleteByPid(id);
        }
        //删除该目录
        departmentService.deleteById(id);

        return JsonResult.ok();
    }
}
