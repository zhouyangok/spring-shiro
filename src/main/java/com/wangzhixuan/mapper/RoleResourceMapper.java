package com.wangzhixuan.mapper;

import java.util.List;

import com.wangzhixuan.model.RoleResource;

public interface RoleResourceMapper {

    int insert(RoleResource roleResource);

    RoleResource selectByPrimaryKey(Long id);

    List<RoleResource> findRoleResourceIdListByRoleId(Long id);

    int updateByPrimaryKeySelective(RoleResource roleResource);

    int deleteById(Long roleResourceId);
}