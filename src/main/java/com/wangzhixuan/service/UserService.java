package com.wangzhixuan.service;

import com.wangzhixuan.model.User;

public interface UserService {

    User findUserByLoginname(String username);

    User findUserById(Long id);

}
