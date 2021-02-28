package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.User;
import com.educational.demo.query.UserQuery;
import com.educational.demo.vo.UserInfoVO;
import com.educational.demo.vo.UserLoginVO;

import java.util.List;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2020-08-17 14:51
 */
public interface UserService {
    /**
     * 分页查询所有用户
     *
     * @param current   当前页码
     * @param size      页面大小
     * @param userQuery 条件
     * @return 用户列表
     */
    Page<User> listTableByPage(int current, int size, UserQuery userQuery);

    /**
     * 检验用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    User checkUser(String username, String password);

    /**
     * 保存或更新用户
     *
     * @param user 用户
     */
    void saveOfUpdate(User user);

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 用户
     */
    User getById(Long id);

    /**
     * 改变用户状态
     *
     * @param user 用户
     */
    void changeStatus(User user);

    /**
     * 根据id删除用户
     *
     * @param id 用户ID
     */
    void removeById(Long id);

    /**
     * 根据id列表批量删除用户
     *
     * @param idList 用户ID列表
     */
    void removeByIdList(List<Long> idList);

    /**
     * 修改用户密码
     *
     * @param passwordVO 用户新旧密码
     */
    void changePassword(UserLoginVO passwordVO);

    /**
     * 修改个人信息
     *
     * @param userInfoVO 个人信息
     */
    void updateInfo(UserInfoVO userInfoVO);

    /**
     * 统计用户总数
     *
     * @return 用户总数
     */
    Integer countAll();
}
