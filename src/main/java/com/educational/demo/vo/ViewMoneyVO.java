package com.educational.demo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-26 16:20
 */
@ApiModel("医生门诊收入统计")
@Data
public class ViewMoneyVO {
    /**
     * 医生名
     */
    private String doctorName;
    /**
     * 门诊收入
     */
    private Double total;
}
