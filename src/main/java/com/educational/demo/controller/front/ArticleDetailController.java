package com.educational.demo.controller.front;

import com.educational.demo.anntation.AccessLog;
import com.educational.demo.model.Article;
import com.educational.demo.service.ArticleService;
import com.educational.demo.vo.ArticleDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/4/18 10:52
 * Version 1.0
 **/
@Api(tags = "前台：文章详情页面")
@Controller
public class ArticleDetailController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("文章详情页面")
    @GetMapping("/article/{id}")
    public String articleDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", articleService.getDetailById(id));
        model.addAttribute("prevPreview", articleService.getPrevPreviewById(id));
        model.addAttribute("nextPreview", articleService.getNextPreviewById(id));
        return "front/article";
    }

    @ApiOperation("点赞文章")
    @ResponseBody
    @PutMapping("/article/{id}/likes")
    public ResponseEntity<Object> likes(@NotNull @PathVariable("id") Long id) {
        articleService.increaseLikes(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("查询文章详情")
    @AccessLog("查询文章详情")
    @ResponseBody
    @GetMapping("/article")
    public ResponseEntity<Object> article(@RequestParam("id") Long id) {
        Article article = articleService.getDetailById(id);
        Article prevPreview = articleService.getPrevPreviewById(id);
        Article nextPreview = articleService.getNextPreviewById(id);
        ArticleDetailVO articleDetailVo = new ArticleDetailVO();
        articleDetailVo.setArticle(article);
        articleDetailVo.setPrevPreview(prevPreview);
        articleDetailVo.setNextPreview(nextPreview);
        return new ResponseEntity<>(articleDetailVo, HttpStatus.OK);
    }

}
