package com.educational.demo.controller.admin;

import com.educational.demo.anntation.AccessLog;
import com.educational.demo.aspect.AccessLogAspect;
import com.educational.demo.common.Constant;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Billingdetails;
import com.educational.demo.model.Registration;
import com.educational.demo.model.User;
import com.educational.demo.query.RegistrationQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.service.BillingDetailsService;
import com.educational.demo.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.val;
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
 * @create 2021-03-01 23:31
 */
@Api(tags = "后台：挂号管理")
@RestController
@RequestMapping("/admin/registration")
public class AdminRegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private BillingDetailsService billingDetailsService;


    @ApiOperation("查询挂号信息")
    @PreAuthorize("hasAuthority('sys:registration:query')")
    @AccessLog("访问挂号页面")
    @GetMapping
    public TableResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  RegistrationQuery registrationQuery) {
        AccessLogAspect accessLogAspect = new AccessLogAspect();
        String userName = accessLogAspect.getUsername();
        Page<Registration> pageInfo;
        if (userName.equals(Constant.ADMINID)){
            pageInfo = registrationService.listTableByPage(page, limit, registrationQuery);
        }else {
            pageInfo = registrationService.listTableById(page, limit, registrationQuery,Integer.parseInt(userName));
        }
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }

    @ApiOperation("删除挂号")
    @PreAuthorize("hasAuthority('sys:registration:delete')")
    @DeleteMapping("/{id}")
    public JsonResult remove(@NotNull @PathVariable("id") Long id) {
        registrationService.removeByRegistrationId(id);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除挂号")
    @PreAuthorize("hasAuthority('sys:registration:delete')")
    @DeleteMapping
    public JsonResult removeBatch(@NotEmpty @RequestBody List<Long> idList) {
        registrationService.removeByIdList(idList);
        return JsonResult.ok();
    }


    @ApiOperation("转院")
    @PreAuthorize("hasAuthority('sys:registration:transfer')")
    @PutMapping("/transfer")
    public JsonResult changeStatus(@RequestBody Registration registration){
        registration.setRstatus(4);
        registrationService.changeStatus(registration);
        return JsonResult.ok();
    }


    @ApiOperation("新增挂号")
    @PreAuthorize("hasAuthority('sys:registration:add')")
    @PostMapping
    public JsonResult save(@Validated @RequestBody Registration registration) {
        if (registration.getRstatus() == null){
            registration.setRstatus(1);
        }

        AccessLogAspect accessLogAspect = new AccessLogAspect();
        registration.setCreateMan(accessLogAspect.getNickname());
        registration.setCreateTime(new Date());
        registration.setUpdateTime(registration.getCreateTime());
        registrationService.saveOfUpdate(registration);

        return JsonResult.ok();
    }

    @ApiOperation("更新挂号")
    @PreAuthorize("hasAuthority('sys:registration:edit')")
    @PutMapping
    public JsonResult update(@Validated @RequestBody Registration registration){
        AccessLogAspect accessLogAspect = new AccessLogAspect();
        registration.setCreateMan(accessLogAspect.getNickname());
        registration.setUpdateTime(new Date());
        registrationService.saveOfUpdate(registration);
        return JsonResult.ok();
    }




    /*****************以下为医生问诊*************/
    @ApiOperation("结束问诊")
    @PreAuthorize("hasAuthority('sys:registration:query')")
    @PutMapping("/finish/{id}")
    public JsonResult finish(@NotNull @PathVariable("id") Long id){
        registrationService.finish(id);
        return JsonResult.ok();
    }


    @ApiOperation("填写病情")
    @PreAuthorize("hasAuthority('sys:registration:query')")
    @PostMapping("/addDescribe")
    public JsonResult addDescribe(@RequestBody Registration registration){
        Long registrationId = registration.getRegistrationId();
        String rdescribe = registration.getRdescribe();
         registrationService.addDescribe(registrationId,rdescribe);
        Billingdetails billingdetails = new Billingdetails();
        billingdetails.setRegistrationId(registrationId.intValue());
        billingdetails.setDrugName("挂号费");
        billingdetails.setDrugNumber(1);
        billingdetails.setPerPrice(registration.getPrice());
        billingdetails.setTotalPrice(registration.getPrice());
        billingdetails.setPayStatus(false);
        if (billingdetails.getCreateTime()==null){
            billingdetails.setCreateTime(new Date());
        }
        billingdetails.setUpdateTime(new Date());
        billingDetailsService.insertDrugOrProject(billingdetails);

        return JsonResult.ok();
    }





}
