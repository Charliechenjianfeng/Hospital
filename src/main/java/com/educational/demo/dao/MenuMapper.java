package com.educational.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.educational.demo.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-17 17:23
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户Id查询菜单
     * @param userId 用户的ID
     * @return 菜单列表
     */
    List<Menu> listMenuByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询相应权限
     * @param userId 用户Id
     * @return 菜单
     */
    List<Menu> listPermissionByUserId(@Param("userId") Long userId);
}
