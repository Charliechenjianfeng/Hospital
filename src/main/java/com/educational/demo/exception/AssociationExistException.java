package com.educational.demo.exception;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-17 21:49
 */
public class AssociationExistException extends RuntimeException{
    public AssociationExistException(String message){
        super(message);
    }
}
