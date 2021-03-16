package com.educational.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.anntation.AccessLog;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Doctor;
import com.educational.demo.model.Drugdictionary;
import com.educational.demo.query.DoctorQuery;
import com.educational.demo.query.DrugQuery;
import com.educational.demo.service.DrugService;
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
 * 药品管理
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-12 18:32
 */
@Api(tags = "后台：药品管理")
@RestController
@RequestMapping("/admin/drug")
public class AdminDrugController {

    @Autowired
    private  DrugService drugService;

    @ApiOperation("查询所有药品")
    @PreAuthorize("hasAuthority('sys:drug:query')")
    @AccessLog("访问药品页面")
    @GetMapping
    public JsonResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                 DrugQuery drugQuery) {
        Page<Drugdictionary> pageInfo = drugService.listTableByPage(page, limit, drugQuery);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }

    @ApiOperation("删除药品")
    @PreAuthorize("hasAuthority('sys:drug:delete')")
    @DeleteMapping("/{id}")
    public JsonResult deleteById(@NotNull @PathVariable("id") Integer id){
        drugService.deleteById(id);
        return TableResult.ok();
    }

    @ApiOperation("删除药品")
    @PreAuthorize("hasAuthority('sys:drug:delete')")
    @DeleteMapping
    public JsonResult deleteByIdList(@NotEmpty @RequestBody List<Integer> idList){
        drugService.deleteByIdList(idList);
        return TableResult.ok();
    }

    @ApiOperation("增加药品")
    @PreAuthorize("hasAuthority('sys:drug:add')")
    @PostMapping
    public JsonResult save(@NotEmpty @RequestBody Drugdictionary drugdictionary){
        drugdictionary.setCreateTime(new Date());
        drugdictionary.setUpdateTime(drugdictionary.getCreateTime());
        drugService.saveOrUpdate(drugdictionary);
        return TableResult.ok();
    }


    @ApiOperation("修改药品")
    @PreAuthorize("hasAuthority('sys:drug:edit')")
    @PutMapping
    public JsonResult update(@NotEmpty @RequestBody Drugdictionary drugdictionary){
        drugdictionary.setUpdateTime(new Date());
        drugService.saveOrUpdate(drugdictionary);
        return TableResult.ok();
    }

}
