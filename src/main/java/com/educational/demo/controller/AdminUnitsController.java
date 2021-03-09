package com.educational.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Units;
import com.educational.demo.query.UnitsQuery;
import com.educational.demo.service.UnitsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.velocity.util.introspection.Uberspect;
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
 * @create 2021-03-08 22:41
 */
@Api(tags = "后台：单位管理")
@RestController
@RequestMapping("/admin/units")
public class AdminUnitsController {

    @Autowired
    private UnitsService unitsService;

    @ApiOperation("查询单位")
    @PreAuthorize("hasAuthority('sys:units:query')")
    @GetMapping
    public TableResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  UnitsQuery unitsQuery){
        Page<Units> pageInfo = unitsService.listTableByPage(page,limit,unitsQuery);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }

    @ApiOperation("删除单位")
    @PreAuthorize("hasAuthority('sys:units:delete')")
    @DeleteMapping("/{id}")
    public JsonResult remove(@NotNull @PathVariable("id") Long id) {
        unitsService.removeById(id);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除单位")
    @PreAuthorize("hasAuthority('sys:units:delete')")
    @DeleteMapping
    public JsonResult removeBatch(@NotEmpty @RequestBody List<Long> idList) {
        unitsService.removeByIdList(idList);
        return JsonResult.ok();
    }


    @ApiOperation("添加挂号")
    @PreAuthorize("hasAuthority('sys:units:add')")
    @PostMapping
    public JsonResult save(@Validated @RequestBody Units units){
        units.setCreateTime(new Date());
        units.setUpdateTime(units.getCreateTime());
        unitsService.saveOrUpdate(units);
        return JsonResult.ok();
    }


    @ApiOperation("编辑挂号")
    @PreAuthorize("hasAuthority('sys:units:edit')")
    @PutMapping
    public JsonResult update(@Validated @RequestBody Units units){
        units.setUpdateTime(new Date());
        unitsService.saveOrUpdate(units);
        return JsonResult.ok();

    }
}
