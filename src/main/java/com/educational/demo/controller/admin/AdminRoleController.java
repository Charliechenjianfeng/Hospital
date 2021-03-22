package com.educational.demo.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.anntation.AccessLog;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Role;
import com.educational.demo.query.RoleQuery;
import com.educational.demo.service.RoleService;
import com.educational.demo.util.StringUtils;
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
 * @create 2020-08-18 20:33
 */
@Api(tags = "后台：角色管理")
@RestController
@RequestMapping("/admin/role")
public class AdminRoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("查询所有角色")
    @PreAuthorize("hasAuthority('sys:role:query')")
    @GetMapping("/list")
    public JsonResult listAll() {
        return JsonResult.ok(roleService.listAll());
    }

    @ApiOperation("查询角色")
    @AccessLog("访问角色页面")
    @PreAuthorize("hasAuthority('sys:role:query')")
    @GetMapping
    public JsonResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                 RoleQuery roleQuery) {
        Page<Role> pageInfo = roleService.listTableByPage(page, limit, roleQuery);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }

    @ApiOperation("新增角色")
    @PreAuthorize("hasAuthority('sys:role:add')")
    @PostMapping
    public JsonResult save(@Validated @RequestBody Role role) {
        if (role.getStatus() == null) {
            role.setStatus(0);
        }
        if (StringUtils.isEmpty(role.getDescription())) {
            role.setDescription("-");
        }
        role.setCreateTime(new Date());
        role.setUpdateTime(role.getCreateTime());
        roleService.saveOfUpdate(role);
        return JsonResult.ok();
    }

    @ApiOperation("更新角色")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @PutMapping
    public JsonResult update(@Validated @RequestBody Role role) {
        if (role.getStatus() == null) {
            role.setStatus(0);
        }
        if (StringUtils.isEmpty(role.getDescription())) {
            role.setDescription("-");
        }
        role.setUpdateTime(new Date());
        roleService.saveOfUpdate(role);
        return JsonResult.ok();
    }

    @ApiOperation("删除角色")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @DeleteMapping("/{id}")
    public JsonResult remove(@NotNull @PathVariable("id") Long id) {
        roleService.removeById(id);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除角色")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @DeleteMapping
    public JsonResult removeBatch(@NotEmpty @RequestBody List<Long> idList) {
        roleService.removeByIdList(idList);
        return JsonResult.ok();
    }

    @ApiOperation("更新角色状态")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @PutMapping("/status")
    public JsonResult changeStatus(@RequestBody Role role) {
        roleService.changeStatus(role);
        return JsonResult.ok();
    }

}
