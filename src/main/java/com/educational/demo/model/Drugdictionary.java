package com.educational.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-12 18:49
 */
@ApiModel("药品")
@Data
@TableName("drugdictionary")
public class Drugdictionary implements Serializable {
    @ApiModelProperty("主键:drugId")
    @TableId(type = IdType.AUTO)
    private Integer drugId;
    private String drugName;
    private Integer unitsId;
    private Double sellingPrice;
    private Date createTime;
    private Date updateTime;
    @TableField(exist = false)
    private Units units;

    public interface Table {
         String DRUGNAEM = "drugName";
        String CREATE_TIME = "createTime";
        String UPDATE_TIME = "updateTime";

    }

}
