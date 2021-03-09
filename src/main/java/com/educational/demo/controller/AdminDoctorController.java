package com.educational.demo.controller;

import com.educational.demo.common.JsonResult;
import com.educational.demo.model.Doctor;
import com.educational.demo.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-06 12:06
 */
@Api(tags = "后台：医生管理")
@RestController
@RequestMapping("/admin/doctor")
public class AdminDoctorController {

    @Autowired
    private DoctorService doctorService;

    @ApiOperation("查询医生")
    @PreAuthorize("hasAuthority('sys:doctor:query')")
    @GetMapping("/list")
    public JsonResult listAll(@RequestParam(required = false) Integer  departmentId,
                              @RequestParam(required = false) Integer typeId) {
        return JsonResult.ok(doctorService.ListByDptIdandTypeId(departmentId,typeId));
    }

}
