package com.educational.demo.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Category;
import com.educational.demo.query.CategoryQuery;
import com.educational.demo.service.CategoryService;
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
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/4/7 19:41
 * Version 1.0
 **/
@Api(tags = "后台：分类管理")
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public JsonResult listAll() {
        return JsonResult.ok(categoryService.listAll());
    }

    @ApiOperation("查询分类")
    @PreAuthorize("hasAuthority('blog:category:query')")
    @GetMapping("/list")
    public TableResult listByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  CategoryQuery categoryQuery) {
        Page<Category> categoryPage = categoryService.listTableByPage(page, limit, categoryQuery);
        return TableResult.tableOk(categoryPage.getRecords(), categoryPage.getTotal());
    }

    @ApiOperation("新增分类")
    @PreAuthorize("hasAuthority('blog:category:add')")
    @PostMapping
    public JsonResult save(@Validated @RequestBody Category category) {
        category.setCreateTime(new Date());
        category.setUpdateTime(category.getCreateTime());
        categoryService.saveOfUpdate(category);
        return JsonResult.ok();
    }

    @ApiOperation("更新分类")
    @PreAuthorize("hasAuthority('blog:category:edit')")
    @PutMapping
    public JsonResult update(@Validated @RequestBody Category category) {
        category.setUpdateTime(new Date());
        categoryService.saveOfUpdate(category);
        return JsonResult.ok();
    }

    @ApiOperation("删除分类")
    @PreAuthorize("hasAuthority('blog:category:delete')")
    @DeleteMapping("/{id}")
    public JsonResult batchRemove(@NotNull @PathVariable("id") Long id) {
        categoryService.removeById(id);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除分类")
    @PreAuthorize("hasAuthority('blog:category:delete')")
    @DeleteMapping
    public JsonResult batchRemove(@NotEmpty @RequestBody List<Long> idList) {
        categoryService.removeByIdList(idList);
        return JsonResult.ok();
    }

    @ApiOperation("查询分类颜色")
    @GetMapping("/colors")
    public JsonResult getColors() {
        return JsonResult.ok(categoryService.listColor());
    }
}
