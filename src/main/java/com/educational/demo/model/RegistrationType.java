package com.educational.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-02 21:04
 */
@Data
public class RegistrationType {
    private Integer typeId;
    private String type;
    private Double price;
    private Date createTime;
    private Date updateTime;

    public interface Table {
    String TYPEID = "typeId";
    String TYPE = "type";
    String PRICE= "price";
    String CREATE_TIME = "create_time";
    String UPDATE_TIME = "update_time";
    }

}
