package com.educational.demo.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Billingdetails;
import com.educational.demo.model.Doctor;
import com.educational.demo.query.ProjectQuery;
import com.educational.demo.service.BillingDetailsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-14 23:05
 */

@Api(tags = "后台：账单管理")
@RestController
@RequestMapping("/admin/billing")
public class AdminBillingController {

    @Autowired
    private BillingDetailsService billingDetailsService;

    @PutMapping("/drugAdd")
    public JsonResult insertDrug(@RequestBody Billingdetails billingdetails){
        billingdetails.setCreateTime(new Date());
        billingdetails.setUpdateTime(new Date());
        billingdetails.setPayStatus(false);
        billingDetailsService.insertDrugOrProject(billingdetails);
        return JsonResult.ok();
    }

    @PutMapping("/projectAdd")
    public JsonResult insertProject(@RequestBody Billingdetails billingdetails){
        billingdetails.setCreateTime(new Date());
        billingdetails.setUpdateTime(new Date());
        billingdetails.setPayStatus(false);
        billingDetailsService.insertDrugOrProject(billingdetails);
        return JsonResult.ok();
    }


    @GetMapping("/registration/{registrationId}")
    public TableResult listByRegistrationId(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                            @NotNull @PathVariable("registrationId") Long registrationId) {

        Page<Billingdetails> pageInfo =billingDetailsService.listTableByRegistrationId(page,limit,registrationId);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }


    @GetMapping("/getPrice")
    public JsonResult getTotalPrice(@NotNull @RequestParam Integer registrationId){
        return JsonResult.ok(billingDetailsService.getTotalPrice(registrationId));
    }

    @PostMapping("/received/{registrationId}")
    public JsonResult payment(@NotNull @PathVariable("registrationId") Integer registrationId){
        billingDetailsService.payment(registrationId);
        return JsonResult.ok();
    }
}
