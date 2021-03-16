package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.Constant;
import com.educational.demo.dao.AccessLogMapper;
import com.educational.demo.model.AccessLog;
import com.educational.demo.query.LogQuery;
import com.educational.demo.service.AccessLogService;
import com.educational.demo.util.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-10-11 22:58
 */
@Service
public class AccessLogServiceImpl implements AccessLogService {

    @Autowired
    private AccessLogMapper accessLogMapper;


    @Override
    public void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, AccessLog log) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.educational.demo.anntation.AccessLog  aopLog = method.getAnnotation(com.educational.demo.anntation.AccessLog.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }
        assert log != null;
        log.setRequestIp(ip);

        String loginPath = "login";
        if (loginPath.equals(signature.getName())) {
            try {
                assert argValues != null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.setAddress(StringUtils.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        log.setBrowser(browser);
        log.setCreateTime(new Date());
        accessLogMapper.insert(log);
    }

    @Override
    public Page<AccessLog> listTableByPage(Integer current, Integer size, LogQuery logQuery) {
        Page<AccessLog> page = new Page<>(current, size);
        QueryWrapper<AccessLog> wrapper = new QueryWrapper<>();
        wrapper.select(AccessLog.Table.ID, AccessLog.Table.REQUEST_IP, AccessLog.Table.ADDRESS, AccessLog.Table.DESCRIPTION, AccessLog.Table.BROWSER, AccessLog.Table.TIME, AccessLog.Table.CREATE_TIME, AccessLog.Table.STATUS,AccessLog.Table.EXCEPTION_DETAIL,AccessLog.Table.LOG_TYPE,AccessLog.Table.METHOD,AccessLog.Table.PARAMS,AccessLog.Table.USERNAME)
                .orderByDesc(AccessLog.Table.CREATE_TIME);
        if (!StringUtils.isEmpty(logQuery.getRequestIp())) {
            wrapper.like(AccessLog.Table.REQUEST_IP, logQuery.getRequestIp());
        }
        if (!StringUtils.isEmpty(logQuery.getDescription())) {
            wrapper.like(AccessLog.Table.DESCRIPTION, logQuery.getDescription());
        }
        if (!StringUtils.isEmpty(logQuery.getBrowser())) {
            wrapper.like(AccessLog.Table.BROWSER, logQuery.getBrowser());
        }
        if (!StringUtils.isEmpty(logQuery.getAddress())) {
            wrapper.like(AccessLog.Table.ADDRESS, logQuery.getAddress());
        }
        if (logQuery.getStartDate() != null && logQuery.getEndDate() != null) {
            wrapper.between(AccessLog.Table.CREATE_TIME, logQuery.getStartDate(), logQuery.getEndDate());
        }
        if (logQuery.getTimeRank() != null) {
            if (Objects.equals(logQuery.getTimeRank(), Constant.LOW_REQUEST_TIME_RANK)) {
                wrapper.lt(AccessLog.Table.TIME, Constant.LOW_REQUEST_TIME);
            }
            if (Objects.equals(logQuery.getTimeRank(), Constant.MID_REQUEST_TIME_RANK)) {
                wrapper.between(AccessLog.Table.TIME, Constant.LOW_REQUEST_TIME, Constant.HIGH_REQUEST_TIME);
            }
            if (Objects.equals(logQuery.getTimeRank(), Constant.HIGH_REQUEST_TIME_RANK)) {
                wrapper.gt(AccessLog.Table.TIME, Constant.HIGH_REQUEST_TIME);
            }
        }
        return accessLogMapper.selectPage(page, wrapper);
    }

    @Override
    public void removeById(Long id) {
        accessLogMapper.deleteById(id);
    }

    @Override
    public void removeByIdList(List<Long> idList) {
        accessLogMapper.deleteBatchIds(idList);
    }
}
