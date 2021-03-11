package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Doctor;
import com.educational.demo.model.Role;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-07 19:49
 */
@Repository
public interface DoctorMapper extends BaseMapper<Doctor> {

   List<Doctor> ListByDptIdandTypeId(Integer departmentId, Integer typeId);

   /**
    * 查询医生
    * @param page 分页参数
    * @param queryWrapper 查询条件
    * @return 医生列表
    */
   Page<Doctor> listTableByPage(IPage<Doctor> page, @Param("ew") QueryWrapper<Doctor> queryWrapper);
}
