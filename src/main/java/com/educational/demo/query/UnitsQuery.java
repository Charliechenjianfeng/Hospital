package com.educational.demo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-09 17:17
 */
@ApiModel("单位查询条件")
@Data
public class UnitsQuery {
    @ApiModelProperty("单位名")
    private String unitsName;

    @ApiModelProperty("开始创建日期")
    private String startDate;

    @ApiModelProperty("结束创建日期")
    private String endDate;
}
