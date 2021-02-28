package com.educational.demo.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-10-08 20:05
 */
public class IndexVo {

    /*@ApiModelProperty("最近访问日志列表")
    private List<AccessLog> accessLogs;

    @ApiModelProperty("最近操作日志列表")
    private List<OperationLog> operationLogs;*/

    @ApiModelProperty("后台流量日期统计")
    private List<ViewDateVO> backViews;
}
