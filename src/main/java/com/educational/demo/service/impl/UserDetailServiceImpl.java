package com.educational.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.educational.demo.dao.MenuMapper;
import com.educational.demo.dao.UserMapper;
import com.educational.demo.dto.LoginUser;
import com.educational.demo.model.Menu;
import com.educational.demo.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/31 21:07
 * Version 1.0
 * 用户登陆验证
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(User.Table.USERNAME, username);
        List<User> users = userMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (users.size() != 1) {
            return null;
        }
        User user = users.get(0);
        LoginUser loginUser = new LoginUser();
        //使用org.springframework.beans.BeanUtils.copyProperties方法进行对象之间属性的赋值，避免通过get、set方法一个一个属性的赋值
        BeanUtils.copyProperties(user, loginUser);
        //查询用户的权限
        List<Menu> permissions = menuMapper.listPermissionByUserId(loginUser.getId());
        //过滤空权限
        permissions = permissions.stream().filter(p -> (!StringUtils.isEmpty(p.getAuthority()))).collect(Collectors.toList());
        loginUser.setPermissions(permissions);
        return loginUser;
    }
}
