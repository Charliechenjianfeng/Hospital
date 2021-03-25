package com.educational.demo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-24 16:50
 */

@Data
public class IntorQuery implements Serializable {
    @ApiModelProperty("姓名")
    private String doctorName;

    @ApiModelProperty("开始创建日期")
    private String startDate;

    @ApiModelProperty("结束创建日期")
    private String endDate;
        }
