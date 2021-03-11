package com.educational.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.function.UnaryOperator;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-02 16:07
 */

@Data
public class Doctor implements Serializable {
    @ApiModelProperty("主键:ID")
    @TableId(type = IdType.AUTO)
    private Integer doctorId;
    private String doctorName;
    private Integer age;
    private String phone;
    private Integer sex;
    private Integer departmentId;
    private Integer typeId;
    private Integer outpatient;
    private Date createTime;
    private Date updateTime;
    @TableField(exist = false)
    private Department department;
    @TableField(exist = false)
    private RegistrationType registrationType;

    public interface Table {
        String DOCTORID = "doctorId";
        String DOCTORNAME = "doctorName";
        String AGE = "age";
        String PHONE = "phone";
        String SEX = "sex";
        String CREATE_TIME = "createTime";
        String UPDATE_TIME = "updateTime";
    }

}
