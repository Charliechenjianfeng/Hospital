package com.educational.demo.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Intro;
import com.educational.demo.query.IntorQuery;
import com.educational.demo.service.IntroService;
import com.educational.demo.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-24 20:12
 */
@Api(tags = "后台：简介管理")
@RestController
@RequestMapping("/admin/Intro")
public class AdminIntroController {

    @Autowired
    private IntroService introService;

    @Autowired
    private PhotoService photoService;

    @ApiOperation("查询简介")
    @PreAuthorize("hasAuthority('doctor:Intro:query')")
    @GetMapping
    public TableResult listTableByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                       IntorQuery intorQuery) {
        Page<Intro> pageInfo = introService.listTableByPage(page, limit,intorQuery);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }


    @ApiOperation("新增简介")
    @PreAuthorize("hasAuthority('doctor:Intro:add')")
    @PostMapping
    public JsonResult save(@RequestParam("uploadFile") MultipartFile file,
                           @RequestParam("doctorName") String doctorName,
                           @RequestParam("summary") String summary,
                           @RequestParam("content") String content,
                           @RequestParam("textContent") String textContent,
                           @RequestParam("categoryId") Long categoryId) {
        Intro intro = new Intro();
        intro.setCover("/static/admin/img/"+file.getOriginalFilename());
        intro.setCreateTime(new Date());
        intro.setDoctorName(doctorName);
        intro.setSummary(summary);
        intro.setContent(content);
        intro.setTextContent(textContent);
        intro.setCategoryId(categoryId);
        intro.setUpdateTime(intro.getCreateTime());
        photoService.upload(file);
        introService.saveOrUpdate(intro);
        return JsonResult.ok();
    }



    @ApiOperation("更新简介")
    @PreAuthorize("hasAuthority('doctor:Intro:edit')")
    @PutMapping
    public JsonResult update(@Validated @RequestBody Intro intro) {
        intro.setCreateTime(new Date());
        intro.setUpdateTime(intro.getCreateTime());
        introService.saveOrUpdate(intro);
        return JsonResult.ok();
    }
}
