package com.educational.demo.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.anntation.AccessLog;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Doctor;
import com.educational.demo.query.DoctorQuery;
import com.educational.demo.service.DoctorService;
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

    @ApiOperation("查询所有医生")
    @PreAuthorize("hasAuthority('sys:doctor:query')")
    @AccessLog("访问医生页面")
    @GetMapping
    public JsonResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                 DoctorQuery doctorQuery) {
        Page<Doctor> pageInfo = doctorService.listTableByPage(page, limit, doctorQuery);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }

    @ApiOperation("删除医生")
    @PreAuthorize("hasAuthority('sys:doctor:delete')")
    @DeleteMapping("/{id}")
    public JsonResult remove(@NotNull @PathVariable("id") Integer id) {
       doctorService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除挂号")
    @PreAuthorize("hasAuthority('sys:doctor:delete')")
    @DeleteMapping
    public JsonResult removeBatch(@NotEmpty @RequestBody List<Integer> idList) {
        doctorService.deleteByIdList(idList);
        return JsonResult.ok();
    }

    @ApiOperation("添加医生")
    @PreAuthorize("hasAuthority('sys:doctor:add')")
    @PostMapping
    public JsonResult save(@Validated @RequestBody Doctor doctor){
        doctor.setCreateTime(new Date());
        doctor.setUpdateTime(doctor.getCreateTime());
        doctorService.saveOrUpdate(doctor);
        return JsonResult.ok();
    }

    @ApiOperation("修改医生")
    @PreAuthorize("hasAuthority('sys:doctor:edit')")
    @PutMapping
    public JsonResult update(@Validated @RequestBody Doctor doctor){
        doctor.setUpdateTime(new Date());
        doctorService.saveOrUpdate(doctor);
        return JsonResult.ok();
    }

}
