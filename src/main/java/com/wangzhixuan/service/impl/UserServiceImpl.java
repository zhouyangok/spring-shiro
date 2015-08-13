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
    public User findUserByLoginname(String username) {
        return userMapper.findUserByLoginname(username);
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

}
