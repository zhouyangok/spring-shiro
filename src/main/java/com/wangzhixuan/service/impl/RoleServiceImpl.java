package com.wangzhixuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzhixuan.mapper.RoleMapper;
import com.wangzhixuan.service.RoleService;
import com.wangzhixuan.utils.PageInfo;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(roleMapper.findRolePageCondition(pageInfo));
        pageInfo.setTotal(roleMapper.findRolePageCount(pageInfo));
    }

}
