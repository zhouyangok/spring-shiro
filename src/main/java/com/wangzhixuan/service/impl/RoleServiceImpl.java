package com.wangzhixuan.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wangzhixuan.code.BaseResponseEnum;
import com.wangzhixuan.exception.BusinessException;
import com.wangzhixuan.mapper.RoleMapper;
import com.wangzhixuan.model.Role;
import com.wangzhixuan.service.RoleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.Tree;
@Service
public class RoleServiceImpl implements RoleService {
    
    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

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
        int insert = roleMapper.insert(role);
        if(insert != 1){
            logger.error("insert role error : {}", role.toString());
            throw new BusinessException(BaseResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public void deleteRole(Long id) {
        int update = roleMapper.deleteRoleById(id);
        if(update != 1){
            throw new BusinessException(BaseResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public Role findRoleById(Long id) {
        return roleMapper.findRoleById(id);
    }

    @Override
    public void updateRole(Role role) {
        int update = roleMapper.updateRole(role);
        if(update != 1){
            throw new BusinessException(BaseResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public List<Long> findResourceByRoleId(Long id) {
        return roleMapper.findResourceByRoleId(id);
    }

}
