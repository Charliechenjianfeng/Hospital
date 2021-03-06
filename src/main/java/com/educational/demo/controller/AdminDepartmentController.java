package com.educational.demo.controller;

import com.educational.demo.common.JsonResult;
import com.educational.demo.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
