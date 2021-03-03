package com.educational.demo.model;

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
    private Long registrationId;
    private String patientName;
    private Integer sex;
    private Integer age;
    private Integer departmentId;
    private Integer doctorId;
    private Integer typeId;
    private Double price;
    private String user;
    private Integer status;
    private String phone;
    private String idCard;
    private String describe;
    private Date createTime;
    private Date updateTime;
    private Doctor doctor;
    private Department department;
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
        String CREATE_TIME = "create_time";
        String UPDATE_TIME = "update_time";
    }


}
