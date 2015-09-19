package com.wangzhixuan.service;

import java.util.List;

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

    List<Long> findResourceByRoleId(Long id);

}
