package com.educational.demo.vo;

import com.educational.demo.model.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-26 17:34
 */
@Data
public class AnalysisVO {
    @ApiModelProperty("医生门诊收入统计")
    private List<ViewMoneyVO> inCome;
    @ApiModelProperty("每月收入统计")
    private List<MonthCountVO> monthCount;
    @ApiModelProperty("每个部门文章统计")
    private List<Category> articleCount;
}
