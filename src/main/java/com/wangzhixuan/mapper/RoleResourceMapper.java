package com.wangzhixuan.mapper;

import com.wangzhixuan.model.RoleResource;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleResource roleResource);

    int insertSelective(RoleResource roleResource);

    RoleResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleResource roleResource);

    int updateByPrimaryKey(RoleResource roleResource);
}