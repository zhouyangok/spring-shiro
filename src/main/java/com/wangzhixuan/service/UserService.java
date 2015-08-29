package com.wangzhixuan.service;

import com.wangzhixuan.model.User;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.UserVo;

public interface UserService {

    User findUserByLoginName(String username);

    User findUserById(Long id);

    void findDataGrid(PageInfo pageInfo);

    void addUser(UserVo userVo);

    void updateUserPwdById(Long userId, String pwd);

    UserVo findUserVoById(Long id);

}
