package com.educational.demo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-10 21:44
 */
@Data
public class DepartmentVO {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("父级菜单ID")
    private Long pid;

    @ApiModelProperty("标题")
    private String departmentName;
}
