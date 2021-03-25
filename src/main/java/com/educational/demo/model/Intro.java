package com.educational.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-24 16:31
 */
@Data
@TableName("t_intro")
@JsonIgnoreProperties("handler")
public class Intro implements Serializable {

    @ApiModelProperty("主键:ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("姓名")
    @NotBlank(message = "医生姓名不能为空")
    @Length(max = 5, message = "医生名长度不能超过5")
    private String doctorName;

    @ApiModelProperty("摘要")
    private String summary;

    @ApiModelProperty("HTML内容")
    @NotBlank(message = "简介内容不能为空")
    private String content;

    @ApiModelProperty("markdown内容")
    private String textContent;

    @ApiModelProperty("封面URL")
    private String cover;


    @ApiModelProperty("外键:分类ID")
    @NotNull(message = "请选择一个分类")
    private Long categoryId;


    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("分类")
    @TableField(exist = false)
    private Category category;


    public interface Table {
        String ID = "id";
        String NAME = "doctorName";
        String SUMMARY = "summary";
        String CONTENT = "content";
        String TEXT_CONTENT = "text_content";
        String COVER = "cover";
        String CATEGORY_ID = "category_id";
        String CREATE_TIME = "createTime";
        String UPDATE_TIME = "updateTime";
    }
}
