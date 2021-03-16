package com.educational.demo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-13 15:19
 */
@Data
public class ProjectQuery implements Serializable {
    @ApiModelProperty("项目名称")
    private String projectName;
    @ApiModelProperty("开始创建日期")
    private String startDate;
    @ApiModelProperty("结束创建日期")
    private String endDate;
}
