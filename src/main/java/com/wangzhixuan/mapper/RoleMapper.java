package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.Resource;
import com.wangzhixuan.model.Role;
import com.wangzhixuan.utils.PageInfo;

public interface RoleMapper {

    int insert(Role role);

    List findRolePageCondition(PageInfo pageInfo);

    int findRolePageCount(PageInfo pageInfo);

    List<Role> findRoleAll();

    Role findRoleById(Long id);

    int updateRole(Role role);

    int deleteRoleById(Long id);

    List<Long> findResourceIdListByRoleId(Long id);

    List<Long> findRoleResourceIdListByRoleId(Long id);

    List<Map<Long, String>> findRoleResourceListByRoleId(Long id);

    List<Resource> findResourceIdListByRoleIdAndType(Long i);

}