package com.educational.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-02 21:04
 */
@Data
@TableName("registrationtype")
public class RegistrationType {
    @TableId(type = IdType.AUTO)
    private Integer typeId;
    private String type;
    private Double price;
    private Date createTime;
    private Date updateTime;

    public interface Table {
    String TYPEID = "typeId";
    String TYPE = "type";
    String PRICE= "price";
    String CREATE_TIME = "createTime";
    String UPDATE_TIME = "updateTime";
    }

}
