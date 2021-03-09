package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educational.demo.model.Doctor;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-03-07 19:49
 */
@Repository
public interface DoctorMapper extends BaseMapper<Doctor> {

   public List<Doctor> ListByDptIdandTypeId(Integer departmentId, Integer typeId);
}
