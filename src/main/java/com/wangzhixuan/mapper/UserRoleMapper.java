package com.wangzhixuan.mapper;

import java.util.List;

import com.wangzhixuan.model.UserRole;

public interface UserRoleMapper {
    int deleteByUserId(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole> findUserRoleByUserId(Long userId);

    int deleteById(Long id);
}