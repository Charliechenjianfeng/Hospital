package com.educational.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-08 22:32
 */

@ApiModel("单位")
@Data
@TableName("units")
public class Units implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long unitsId;
    private String unitsName;
    private Date createTime;
    private Date updateTime;

    public interface Table {
        String UNITSID = "unitsId";
        String UNITSNAME = "unitsName";
        String CREATETIME = "createTime";
        String UPDATETIEM = "updateTime";
    }
}
