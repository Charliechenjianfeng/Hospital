package com.educational.demo.util;


import com.educational.demo.common.Constant;
import com.educational.demo.exception.BadRequestException;
import com.educational.demo.model.User;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-18 14:49
 */
public class UserInfoUtil {
    public static String getUsername() {
        Object o = RequestHolder.getHttpServletRequest().getSession().getAttribute(Constant.USER);
        String username = "";
        if (o != null) {
            User user = (User) o;
            username = user.getUsername();
        } else {
            throw new BadRequestException("用户未登录");
        }
        return username;
    }

    public static Long getId() {
        Object o = RequestHolder.getHttpServletRequest().getSession().getAttribute(Constant.USER);
        Long id = null;
        if (o != null) {
            User user = (User) o;
            id = user.getId();
        } else {
            throw new BadRequestException("用户未登录");
        }
        return id;
    }
}
