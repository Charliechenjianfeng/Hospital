package com.educational.demo.exception;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-13 15:24
 */
public class EntityExistException extends RuntimeException{

    public EntityExistException(String message){
        super(message);
    }

    public EntityExistException (String entity,String field,String val){
        super(EntityExistException.generateMessage(entity, field, val));
    }
    private static String generateMessage(String entity,String field,String val){
        return entity+"["+field+"]"+val+"已经存在";
    }
}
