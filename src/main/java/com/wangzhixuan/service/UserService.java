package com.wangzhixuan.service;

import com.wangzhixuan.model.User;

public interface UserService {

    User findUserByloginname(String username);

}
