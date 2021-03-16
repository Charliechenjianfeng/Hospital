package com.educational.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-14 22:39
 */
@Data
public class Billingdetails implements Serializable {
    @ApiModelProperty("主键:detailsId")
    @TableId(type = IdType.AUTO)
    private Integer detailsId;
    private Integer registrationId;
    private String drugName;
    private Integer drugNumber;
    private Double perPrice;
    private Double totalPrice;
    //检查状态
    private Boolean checkStatus;
    //是否支付完
    private Boolean payStatus;
    private Date createTime;
    private Date updateTime;

    @TableField(exist = false)
    private Registration registration;
}
