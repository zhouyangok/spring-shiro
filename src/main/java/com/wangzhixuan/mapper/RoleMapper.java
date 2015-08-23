package com.wangzhixuan.mapper;

import java.util.List;

import com.wangzhixuan.model.Role;
import com.wangzhixuan.utils.PageInfo;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List findRolePageCondition(PageInfo pageInfo);

    int findRolePageCount(PageInfo pageInfo);

    List<Role> findRoleAll();

    Role findRoleById(Long id);

    int updateRole(Role role);

    int deleteRoleById(Long id);
}