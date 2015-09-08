package com.wangzhixuan.mapper;

import java.util.List;

import com.wangzhixuan.model.UserRole;

public interface UserRoleMapper {
    int deleteByUserId(Long id);

    int insert(UserRole userRole);

    int insertSelective(UserRole userRole);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole userRole);

    int updateByPrimaryKey(UserRole userRole);

    List<UserRole> findUserRoleByUserId(Long userId);

    int deleteById(Long id);
}