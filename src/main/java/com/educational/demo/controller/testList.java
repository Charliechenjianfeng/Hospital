package com.educational.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-01-14 21:58
 */
public class testList {
    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        test.add("rrrr");
        test.add("ttt");

        String c = "rrrr,ttt";
        String b = String.join(",", test);
        System.out.println(c.equals(b));
    }
}
