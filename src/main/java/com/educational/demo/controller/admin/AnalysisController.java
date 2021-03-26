package com.educational.demo.controller.admin;

import com.educational.demo.service.BillingDetailsService;
import com.educational.demo.service.CategoryService;
import com.educational.demo.service.DoctorService;
import com.educational.demo.vo.AnalysisVO;
import com.educational.demo.vo.MonthCountVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-26 17:36
 */
@Api(tags = "后台：数据分析")
@Controller
@RequestMapping("/admin/analysis")
public class AnalysisController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private BillingDetailsService billingDetailsService;

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @GetMapping("/analysisData")
    public ResponseEntity<Object> analysis() {
        List<Integer> temp= new ArrayList<>();;
        AnalysisVO analysisVO = new AnalysisVO();
        analysisVO.setInCome(doctorService.doctorIncome());
        List<MonthCountVO> monthCountVOList = billingDetailsService.monthCount();
            for (MonthCountVO vo : monthCountVOList) {
                if (vo.getMonths().charAt(0) == '0') {
                    vo.setMonths(String.valueOf(vo.getMonths().charAt(1)));
                }
                temp.add(Integer.valueOf(vo.getMonths()));
            }

            //补齐12个月份数据
            for (int i=0;i<13;i++){
                MonthCountVO monthCountVO = new MonthCountVO();
                if (!temp.contains(i)){
                    monthCountVO.setMonths(String.valueOf(i));
                    monthCountVO.setTotal((double) 0);
                    monthCountVOList.add(monthCountVO);
                }
            }

            Collections.sort( monthCountVOList);
            analysisVO.setMonthCount(monthCountVOList);
            analysisVO.setArticleCount(categoryService.listByArticleCount());

        return new ResponseEntity<>(analysisVO, HttpStatus.OK);
    }


}
