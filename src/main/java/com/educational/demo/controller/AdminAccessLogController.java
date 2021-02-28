package com.educational.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.aspect.AccessLogAspect;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.AccessLog;
import com.educational.demo.query.LogQuery;
import com.educational.demo.service.AccessLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-10-11 23:34
 */
@Api(tags = "后台：访问日志管理")
@RestController
@RequestMapping("/admin/access-log")
public class AdminAccessLogController {

    @Autowired
    private AccessLogService accessLogService;

    @ApiOperation("查询访问日志")
    @PreAuthorize("hasAuthority('sys:accesslog:query')")
    @GetMapping
    public TableResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  LogQuery logQuery) {
        System.out.println("日志被执行了");
        Page<AccessLog> pageInfo = accessLogService.listTableByPage(page, limit, logQuery);

        AccessLogAspect accessLogAspect = new AccessLogAspect();
        String name = accessLogAspect.getUsername();
        System.out.println("用户名是"+name);


        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());


    }

    @ApiOperation("删除访问日志")
    @PreAuthorize("hasAuthority('sys:accesslog:delete')")
    @DeleteMapping("/{id}")
    public JsonResult removeById(@NotNull @PathVariable("id") Long id) {
        accessLogService.removeById(id);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除访问日志")
    @PreAuthorize("hasAuthority('sys:accesslog:delete')")
    @DeleteMapping
    public JsonResult removeBatch(@NotEmpty @RequestBody List<Long> idList) {
        accessLogService.removeByIdList(idList);
        return JsonResult.ok();
    }
}
