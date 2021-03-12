package com.educational.demo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-12 20:22
 */
@ApiModel("药品查询条件")
@Data
public class DrugQuery {
    @ApiModelProperty("药品名")
    private String drugName;
    @ApiModelProperty("开始创建日期")
    private String startDate;

    @ApiModelProperty("结束创建日期")
    private String endDate;
}
