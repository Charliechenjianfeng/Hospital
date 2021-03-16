package com.educational.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Photo;
import com.educational.demo.query.PhotoQuery;
import com.educational.demo.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Api(tags = "后台：相册管理")
@RestController
@RequestMapping("/admin/photo")
public class PhotoController {


    @Autowired
    private PhotoService photoService;

    @ApiOperation("查询照片")
    @PreAuthorize("hasAuthority('blog:photo:query')")
    @GetMapping
    public TableResult listTableByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                       PhotoQuery photoQuery) {
        Page<Photo> pageInfo = photoService.listTableByPage(page, limit, photoQuery);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }

    @ApiOperation("删除照片")
    @PreAuthorize("hasAuthority('blog:photo:delete')")
    @DeleteMapping("/{id}")
    public JsonResult remove(@NotNull @PathVariable("id") Long id) {
       Photo photo = photoService.getById(id);
       String imgPath = photo.getUrl();
        String temp = imgPath.replace("/admin/img/","");
        photoService.removeById(id, temp);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除照片")
    @PreAuthorize("hasAuthority('blog:photo:delete')")
    @DeleteMapping
    public JsonResult batchRemove(@NotEmpty @RequestBody List<Long> idList) {
        photoService.removeByIdList(idList);
        return JsonResult.ok();
    }

    @ApiOperation("新增照片")
    @PreAuthorize("hasAuthority('blog:photo:add')")
    @PostMapping
    public JsonResult save(@RequestParam("uploadFile") MultipartFile file,
                           @RequestParam("url") String url,
                           @RequestParam("description") String description,
                           @RequestParam("sort") Integer sort,
                           @RequestParam("display")Boolean display) {
        Photo photo = new Photo();
        photo.setUrl("/admin/img/"+file.getOriginalFilename());
        photo.setDescription(description);
        photo.setSort(sort);
        photo.setDisplay(display);
        photo.setCreateTime(new Date());
        photo.setUpdateTime(photo.getCreateTime());
        photoService.saveOfUpdate(photo);
        photoService.upload(file);
        return JsonResult.ok();
    }

    @ApiOperation("更新照片")
    @PreAuthorize("hasAuthority('blog:photo:edit')")
    @PutMapping
    public JsonResult update(@Validated @RequestBody Photo photo) {
        photo.setUpdateTime(new Date());
        photoService.saveOfUpdate(photo);
        return JsonResult.ok();
    }

}
