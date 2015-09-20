package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.Role;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.Tree;

public interface RoleService {

    void findDataGrid(PageInfo pageInfo);

    List<Tree> findTree();

    void addRole(Role role);

    void deleteRole(Long id);

    Role findRoleById(Long id);

    void updateRole(Role role);

    List<Long> findResourceIdListByRoleId(Long id);

    void updateRoleResource(Long id, String resourceIds);

    List<Long> findRoleIdListByUserId(Long userId);
    
    List<Map<Long, String>> findRoleResourceListByRoleId(Long roleId);

}
