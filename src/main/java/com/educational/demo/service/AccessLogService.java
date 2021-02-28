package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.AccessLog;
import com.educational.demo.query.LogQuery;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-19 14:45
 */
public interface AccessLogService {

    /**
     * 保存日志
     * @param username 用户
     * @param browser 浏览器类型
     * @param ip 请求IP
     * @param joinPoint /
     * @param log 日志
     */
    void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, AccessLog log);

    /**
     * 分页查询所有日志
     * @param current 当前页
     * @param size 页码大小
     * @param logQuery 条件
     * @return 日志分页表
     */
    Page<AccessLog> listTableByPage(Integer current, Integer size, LogQuery logQuery);


    /**
     * 根据Id删除日志
     * @param id 日志id
     */
    void removeById(Long id);

    /**
     * 批量删除日志
     * @param idList Id列表
     */
    void removeByIdList(List<Long> idList);


}
