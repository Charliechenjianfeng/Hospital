package com.educational.demo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-02 15:03
 * 挂号页面查询
 */
@Data
public class RegistrationQuery {

    @ApiModelProperty("名称")
    private String patientName;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("开始创建日期")
    private String startDate;

    @ApiModelProperty("结束创建日期")
    private String endDate;
}
