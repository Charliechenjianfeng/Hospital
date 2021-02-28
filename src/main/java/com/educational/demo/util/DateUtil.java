package com.educational.demo.util;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-14 15:33
 */
public class DateUtil {
    public static String formatDate(Integer year, Integer month, Integer day) {
        if (day != null) {
            return String.format("%4d-%02d-%02d", year, month, day);
        } else {
            return String.format("%4d-%02d", year, month);
        }
    }
}
