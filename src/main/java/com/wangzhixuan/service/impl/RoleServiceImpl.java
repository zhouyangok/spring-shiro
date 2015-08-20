package com.wangzhixuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wangzhixuan.mapper.RoleMapper;
import com.wangzhixuan.model.Role;
import com.wangzhixuan.service.RoleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.Tree;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(roleMapper.findRolePageCondition(pageInfo));
        pageInfo.setTotal(roleMapper.findRolePageCount(pageInfo));
    }

    @Override
    public List<Tree> findTree() {
        List<Tree> trees = Lists.newArrayList();
        List<Role> roles = roleMapper.findRoleAll();
        for (Role role : roles) {
            Tree tree = new Tree();
            tree.setId(role.getId().toString());
            tree.setText(role.getName());

            trees.add(tree);
        }
        return trees;
    }

    @Override
    public void addRole(Role role) {
        
    }

}
