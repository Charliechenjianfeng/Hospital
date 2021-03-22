package com.educational.demo.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.Constant;
import com.educational.demo.common.JsonResult;
import com.educational.demo.common.TableResult;
import com.educational.demo.model.Article;
import com.educational.demo.query.ArticleQuery;
import com.educational.demo.service.ArticleService;
import com.educational.demo.util.UserInfoUtil;
import com.educational.demo.vo.ArticleVO;
import com.educational.demo.vo.AuditVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/4/7 23:02
 * Version 1.0
 **/
@Api(tags = "后台：文章管理")
@RestController
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("查询文章")
    @PreAuthorize("hasAuthority('blog:article:query')")
    @GetMapping
    public TableResult listTableByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                       ArticleQuery articleQuery) {
        Page<Article> pageInfo = articleService.listTableByPage(page, limit, articleQuery);
        return TableResult.tableOk(pageInfo.getRecords(), pageInfo.getTotal());
    }

    @ApiOperation("新增文章")
    @PreAuthorize("hasAuthority('blog:article:add')")
    @PostMapping
    public JsonResult save(@Validated @RequestBody Article article) {
        article.setViews(0);
        article.setLikes(0);
        article.setComments(0);
        article.setAppreciable(article.getAppreciable() != null);
        article.setCommentable(article.getCommentable() != null);
        article.setTop(article.getTop() != null);
        article.setRecommend(article.getRecommend() != null);
        article.setCreateTime(new Date());
        article.setUpdateTime(article.getCreateTime());
        article.setAuthorId(UserInfoUtil.getId());
        article.setStatus(Constant.AUDIT_WAIT);
        articleService.saveOrUpdate(article);
        return JsonResult.ok();
    }

    @ApiOperation("更新文章")
    @PreAuthorize("hasAuthority('blog:article:edit')")
    @PutMapping
    public JsonResult update(@Validated @RequestBody ArticleVO articleVo) {
        articleVo.setAppreciable(articleVo.getAppreciable() != null);
        articleVo.setCommentable(articleVo.getCommentable() != null);
        articleVo.setTop(articleVo.getTop() != null);
        articleVo.setRecommend(articleVo.getRecommend() != null);
        articleVo.setUpdateTime(new Date());
        articleVo.setStatus(Constant.AUDIT_WAIT);
        articleService.saveOrUpdate(articleVo);
        return JsonResult.ok();
    }

    @ApiOperation("删除文章")
    @PreAuthorize("hasAuthority('blog:article:delete')")
    @DeleteMapping("/{id}")
    public JsonResult remove(@NotNull @PathVariable("id") Long id) {
        articleService.removeById(id);
        return JsonResult.ok();
    }

    @ApiOperation("批量删除文章")
    @PreAuthorize("hasAuthority('blog:article:delete')")
    @DeleteMapping
    public JsonResult batchRemove(@RequestBody List<Long> idList) {
        articleService.removeByIdList(idList);
        return JsonResult.ok();
    }

    @ApiOperation("置顶文章是否到达限制")
    @GetMapping("/reachedMaxTop")
    public JsonResult reachedMaxTop() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("reached", articleService.reachedMaxTop());
        map.put("maxTop", Constant.MAX_TOP_ARTICLES);
        return JsonResult.ok(map);
    }

    @ApiOperation("推荐文章是否到达限制")
    @GetMapping("/reachedMaxRecommend")
    public JsonResult reachedMaxRecommend() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("reached", articleService.reachedMaxRecommend());
        map.put("maxRecommend", Constant.MAX_RECOMMEND_ARTICLES);
        return JsonResult.ok(map);
    }

    @ApiOperation("审核文章")
    @PreAuthorize("hasAuthority('blog:article:audit')")
    @PutMapping("/audit")
    public JsonResult audit(@Validated @RequestBody AuditVO auditVO) {
        articleService.audit(auditVO);
        return JsonResult.ok();
    }
}
