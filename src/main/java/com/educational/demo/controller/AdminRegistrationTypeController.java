package com.educational.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.anntation.AccessLog;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.RegistrationType;
import com.educational.demo.query.RegistrationTypeQuery;
import com.educational.demo.service.RegistrationTypeService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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
    @AccessLog("访问挂号类型页面")
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

    @ApiOperation("查询挂号类型信息")
    @PreAuthorize("hasAuthority('sys:registrationType:query')")
    @GetMapping
    public JsonResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                 RegistrationTypeQuery registrationTypeQuery) {
        Page<RegistrationType>  pageInfo = registrationTypeService.listTableByPage(page, limit, registrationTypeQuery);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }

    @ApiOperation("删除挂号类型")
    @PreAuthorize("hasAuthority('sys:registrationType:delete')")
    @DeleteMapping("/{id}")
    public JsonResult remove(@NotNull @PathVariable("id") Integer id) {
        registrationTypeService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除挂号")
    @PreAuthorize("hasAuthority('sys:registrationType:delete')")
    @DeleteMapping
    public JsonResult removeBatch(@NotEmpty @RequestBody List<Integer> idList) {
        registrationTypeService.deleteByIdList(idList);
        return JsonResult.ok();
    }

    @ApiOperation("增加类型")
    @PreAuthorize("hasAuthority('sys:registrationType:add')")
    @PostMapping
    public JsonResult save(@Validated @RequestBody RegistrationType registrationType){
        registrationType.setCreateTime(new Date());
        registrationType.setUpdateTime(registrationType.getCreateTime());
        registrationTypeService.saveOrUpdate(registrationType);
        return JsonResult.ok();
    }

    @ApiOperation("更新类型")
    @PreAuthorize("hasAuthority('sys:registrationType:edit')")
    @PutMapping
    public JsonResult update(@Validated @RequestBody RegistrationType registrationType){
        registrationType.setUpdateTime(new Date());
        registrationTypeService.saveOrUpdate(registrationType);
        return JsonResult.ok();
    }

}
