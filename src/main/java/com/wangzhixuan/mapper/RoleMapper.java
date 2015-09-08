package com.wangzhixuan.mapper;

import java.util.List;

import com.wangzhixuan.model.Role;
import com.wangzhixuan.utils.PageInfo;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role role);

    int insertSelective(Role role);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role role);

    int updateByPrimaryKey(Role role);

    List findRolePageCondition(PageInfo pageInfo);

    int findRolePageCount(PageInfo pageInfo);

    List<Role> findRoleAll();

    Role findRoleById(Long id);

    int updateRole(Role role);

    int deleteRoleById(Long id);
}