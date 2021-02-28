package com.educational.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.Constant;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.User;
import com.educational.demo.query.UserQuery;
import com.educational.demo.service.UserService;
import com.educational.demo.vo.UserInfoVO;
import com.educational.demo.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-18 19:58
 */
@Api(tags = "后台：用户管理")
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @ApiOperation("查询用户")
    @PreAuthorize("hasAuthority('sys:user:query')")
    @GetMapping
    public TableResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  UserQuery userQuery) {
        Page<User> pageInfo = userService.listTableByPage(page, limit, userQuery);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }

    @ApiOperation("新增用户")
    @PreAuthorize("hasAuthority('sys:user:add')")
    @PostMapping
    public JsonResult save(@Validated @RequestBody User user) {
        if (user.getStatus() == null) {
            user.setStatus(0);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(Constant.DEFAULT_PASSWORD));
        user.setAvatar(Constant.DEFAULT_AVATAR);
        user.setCreateTime(new Date());
        user.setUpdateTime(user.getCreateTime());
        userService.saveOfUpdate(user);
        return JsonResult.ok();
    }

    @ApiOperation("更新用户")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @PutMapping
    public JsonResult update(@Validated @RequestBody User user) {
        if (user.getStatus() == null) {
            user.setStatus(0);
        }
        user.setUpdateTime(new Date());
        userService.saveOfUpdate(user);
        return JsonResult.ok();
    }

    @ApiOperation("更新用户状态")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @PutMapping("/status")
    public JsonResult changeStatus(@RequestBody User user) {
        userService.changeStatus(user);
        return JsonResult.ok();
    }

    @ApiOperation("修改密码")
    @PutMapping("/password")
    public JsonResult changePassword(@Validated @RequestBody UserLoginVO passwordVO) {
        userService.changePassword(passwordVO);
        return JsonResult.ok();
    }

    @ApiOperation("删除用户")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @DeleteMapping("/{id}")
    public JsonResult remove(@NotNull @PathVariable("id") Long id) {
        userService.removeById(id);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除用户")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @DeleteMapping
    public JsonResult removeBatch(@NotEmpty @RequestBody List<Long> idList) {
        userService.removeByIdList(idList);
        return JsonResult.ok();
    }

    @ApiOperation("查询个人信息")
    @GetMapping("/{id}/info")
    public JsonResult getInfo(@PathVariable("id") Long id) {
        return JsonResult.ok(userService.getById(id));
    }

    @ApiOperation("更新个人信息")
    @PutMapping("/info")
    public JsonResult updateInfo(@Validated @RequestBody UserInfoVO userInfoVO) {
        userService.updateInfo(userInfoVO);
        return JsonResult.ok();
    }
}
