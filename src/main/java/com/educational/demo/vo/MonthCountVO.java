package com.educational.demo.vo;

import lombok.Data;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-26 21:26
 */
@Data
public class MonthCountVO implements Comparable<MonthCountVO>{
    private String months;
    private Double total;


    @Override
    public int compareTo(MonthCountVO o) {
        if (Integer.parseInt(this.getMonths())==(Integer.parseInt(o.getMonths()))){
            return 0;
        }else if (Integer.parseInt(this.getMonths()) > Integer.parseInt(o.getMonths())){
            return 1;
        }else if (Integer.parseInt(this.getMonths()) < Integer.parseInt(o.getMonths())){
            return -1;
        }
        return Integer.parseInt(this.getMonths()) - Integer.parseInt(o.getMonths());
    }
}
