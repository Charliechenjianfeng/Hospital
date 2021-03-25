package com.educational.demo.controller.front;

import com.educational.demo.service.IntroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-25 14:45
 */
@Api(tags = "前台：医生简介页面")
@Controller
public class DoctorDetailController {

    @Autowired
    private IntroService introService;

    @ApiOperation("简介页面")
    @GetMapping("/intro/{id}")
    public String articleDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("intro", introService.selectById(id));
        return "front/doctorDetails";
    }
}
