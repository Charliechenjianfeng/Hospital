package com.educational.demo.model;

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
    private Department department;

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
