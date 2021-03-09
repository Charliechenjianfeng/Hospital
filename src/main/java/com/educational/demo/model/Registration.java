package com.educational.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-01 23:40
 */
@ApiModel("挂号")
@Data
@TableName("registration")
public class Registration implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long registrationId;
    private String patientName;
    private Boolean sex;
    private Integer age;
    private Integer departmentId;
    private Integer doctorId;
    private Integer typeId;
    private Double price;
    private String createMan;
    private Integer rstatus;
    private String phone;
    private String idCard;
    private String rDescribe;
    private Date createTime;
    private Date updateTime;

    @TableField(exist = false)
    private Doctor doctor;
    @TableField(exist = false)
    private Department department;
    @TableField(exist = false)
    private RegistrationType registrationType;

    public interface Table {
        String ID = "registrationId";
        String PATIENTNAME = "patientName";
        String SEX = "sex";
        String AGE = "age";
        String PRICE = "price";
        String EMAIL = "email";
        String PHONE = "phone";
        String IDCARD = "idCard";
        String CREATE_TIME = "createTime";
        String UPDATE_TIME = "updateTime";
    }


}
