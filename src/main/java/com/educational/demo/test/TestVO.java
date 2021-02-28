package com.educational.demo.test;

import lombok.Data;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-01-31 20:12
 */
public class TestVO {
    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamRel() {
        return paramRel;
    }

    public void setParamRel(String paramRel) {
        this.paramRel = paramRel;
    }

    private String paramType;
    private String paramRel;
}
