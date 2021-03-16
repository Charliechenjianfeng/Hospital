package com.educational.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-13 13:56
 */
@ApiModel("项目")
@Data
@TableName("medicalProject")
public class Project {
    @TableId(type = IdType.AUTO)
    private Integer projectId;
    private String projectName;
    private Integer unitsId;
    private Double sellingPrice;
    private Boolean checkStatus;
    private Date createTime;
    private Date updateTime;
    @TableField(exist = false)
    private Units units;

    public interface Table {
        String PROJECTNAME = "projectName";
        String CREATE_TIME = "createTime";
        String UPDATE_TIME = "updateTime";
    }
}
