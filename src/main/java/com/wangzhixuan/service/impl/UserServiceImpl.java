package com.wangzhixuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzhixuan.mapper.UserMapper;
import com.wangzhixuan.model.User;
import com.wangzhixuan.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByloginname(String username) {
        return userMapper.findUserByloginname(username);
    }

}
