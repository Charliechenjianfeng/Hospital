package com.educational.demo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-10 0:17
 */
@ApiModel("挂号类型查询")
@Data
public class RegistrationTypeQuery {
    @ApiModelProperty("类型名")
    private String type;

    @ApiModelProperty("开始创建日期")
    private String startDate;

    @ApiModelProperty("结束创建日期")
    private String endDate;
}
