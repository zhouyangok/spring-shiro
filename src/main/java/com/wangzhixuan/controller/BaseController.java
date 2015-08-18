package com.wangzhixuan.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangzhixuan.model.User;
import com.wangzhixuan.service.UserService;
import com.wangzhixuan.shiro.ShiroUser;


public class BaseController {
	@Autowired
    private UserService userService;
	
    private User userInfo;
    
    public User getCurrentUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		userInfo = userService.findUserById(user.id);
    	return userInfo;
    }

    public Long getUserId() {
        return this.getCurrentUser().getId();
    }

    public String getStaffName() {
        return this.getCurrentUser().getName();
    }

}
