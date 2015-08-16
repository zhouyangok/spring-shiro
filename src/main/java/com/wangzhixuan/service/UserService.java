package com.wangzhixuan.service;

import com.wangzhixuan.model.User;
import com.wangzhixuan.utils.PageInfo;

public interface UserService {

    User findUserByLoginName(String username);

    User findUserById(Long id);

    void findDataGrid(PageInfo pageInfo);

}
