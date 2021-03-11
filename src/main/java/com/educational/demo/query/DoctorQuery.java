package com.educational.demo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-11 21:48
 */
@ApiModel("医生查询条件")
@Data
public class DoctorQuery implements Serializable {
    @ApiModelProperty("医生名")
    private String doctorName;
    @ApiModelProperty("电话号码")
    private String phone;
    @ApiModelProperty("开始创建日期")
    private String startDate;

    @ApiModelProperty("结束创建日期")
    private String endDate;
}
