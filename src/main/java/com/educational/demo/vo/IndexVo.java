package com.educational.demo.vo;

import com.educational.demo.model.AccessLog;
import com.educational.demo.model.Article;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-10-08 20:05
 */
@Data
public class IndexVo {

    @ApiModelProperty("最近访问日志列表")
    private List<AccessLog> accessLogs;

    @ApiModelProperty("后台流量日期统计")
    private List<ViewDateVO> backViews;

    @ApiModelProperty("文章数量")
    private Long articleCount;

    @ApiModelProperty("分类数量")
    private Long categoryCount;

    @ApiModelProperty("标签数量")
    private Long tagCount;

    @ApiModelProperty("最近文章列表")
    private List<Article> articles;

    @ApiModelProperty("前台流量日期统计")
    private List<ViewDateVO> frontViews;

    @ApiModelProperty("距离上次访问主页新增浏览量")
    private Integer increasedViews;

    @ApiModelProperty("距离上次访问主页新增文章数量")
    private Integer increasedArticles;

    @ApiModelProperty("当天收入")
    private Double countOneDay;

    @ApiModelProperty("门诊总人数")
    private Integer registrationCount;

    @ApiModelProperty("当天剩余门诊人数")
    private Integer registrationCountByDay;

    @ApiModelProperty("在线人数")
    private Integer onLinePeople;

    @ApiModelProperty("文章阅读量")
    private Integer countArticleViews;


}
