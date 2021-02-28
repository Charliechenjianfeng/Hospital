package com.educational.demo.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-13 19:33
 */
public class BadRequestException extends RuntimeException{
    private Integer status = BAD_REQUEST.value();

    public BadRequestException (String msg){
        super(msg);
    }
    public BadRequestException(HttpStatus status, String msg){
        super(msg);
        this.status= status.value();
    }
}
