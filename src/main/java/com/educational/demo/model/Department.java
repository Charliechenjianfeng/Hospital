package com.educational.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-02 16:16
 * 科室信息
 */
@Data
public class Department {
    private Integer departmentId;
    private String departmentName;
    private Integer pid;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;

    public interface Table {
        String ID = "id";
        String DEPARTMENTNAME = "departmentName";
        String PID = "pid";
        String CREATE_TIME = "createTime";
        String UPDATE_TIME = "updateTime";
    }


}
