package com.educational.demo.controller;

import com.educational.demo.common.JsonResult;
import com.educational.demo.service.RegistrationTypeService;
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
 * @create 2021-03-06 23:24
 */
@Api(tags = "后台：挂号类型管理")
@RestController
@RequestMapping("/admin/registrationType")
public class AdminRegistrationTypeController {

    @Autowired
    private RegistrationTypeService registrationTypeService;

    @ApiOperation("查询挂号类型")
    @PreAuthorize("hasAuthority('sys:registrationType:query')")
    @GetMapping("/list")
    public JsonResult listAll() {
        return JsonResult.ok(registrationTypeService.listAll());
    }

    @ApiOperation("查询价格")
    @PreAuthorize("hasAuthority('sys:registrationType:query')")
    @GetMapping("/list/price/{id}")
    public JsonResult listPrice(@NotNull @PathVariable("id") Integer id){
        return JsonResult.ok(registrationTypeService.listPriceById(id));
    }
}
