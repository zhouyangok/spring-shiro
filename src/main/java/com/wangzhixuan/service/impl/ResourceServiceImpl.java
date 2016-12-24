package com.wangzhixuan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.mapper.ResourceMapper;
import com.wangzhixuan.mapper.RoleMapper;
import com.wangzhixuan.mapper.UserRoleMapper;
import com.wangzhixuan.model.Resource;
import com.wangzhixuan.service.IResourceService;

/**
 *
 * Resource 表数据服务层接口实现类
 *
 */
@Service
public class ResourceServiceImpl extends SuperServiceImpl<ResourceMapper, Resource> implements IResourceService {

    private static final int RESOURCE_MENU = 0; // 菜单
    private static final int RESOURCE_BUTTON = 1; // 按钮

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public List<Resource> selectAll() {
        EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
        wrapper.orderBy("seq");
        return resourceMapper.selectList(wrapper);
    }
    
    public List<Resource> selectByPidAndType(Integer pid, Integer type) {
        EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
        Resource resource = new Resource();
        wrapper.setEntity(resource);
        if (pid == null) {
            wrapper.isNull("pid");
        } else {
            wrapper.addFilter("pid = {0}", pid);
        }
        wrapper.addFilter("resource_type = {0}", type);
        wrapper.orderBy("seq");
        return resourceMapper.selectList(wrapper);
    }
    
    public List<Resource> selectByType(Integer type) {
        EntityWrapper<Resource> wrapper = new EntityWrapper<Resource>();
        Resource resource = new Resource();
        wrapper.setEntity(resource);
        wrapper.addFilter("resource_type = {0}", type);
        wrapper.orderBy("seq");
        return resourceMapper.selectList(wrapper);
    }
    
    @Override
    public List<Tree> selectAllTree() {
        List<Tree> trees = new ArrayList<Tree>();
        // 查询所有的一级树
        List<Resource> resources = resourceMapper.selectAllByTypeAndPIdNull(RESOURCE_MENU);
        if (resources == null) {
            return null;
        }
        for (Resource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());
            // 查询所有一级树下的菜单
            List<Resource> resourceSon = resourceMapper.selectAllByTypeAndPId(RESOURCE_MENU, resourceOne.getId());

            if (resourceSon != null) {
                List<Tree> tree = new ArrayList<Tree>();
                for (Resource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();
                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());
                    tree.add(treeTwo);
                }
                treeOne.setChildren(tree);
            } else {
                treeOne.setState("closed");
            }
            trees.add(treeOne);
        }
        return trees;
    }

    @Override
    public List<Tree> selectAllTrees() {
        List<Tree> treeOneList = new ArrayList<Tree>();

        // 查询所有的一级树
        List<Resource> resources = resourceMapper.selectAllByTypeAndPIdNull(RESOURCE_MENU);
        if (resources == null) {
            return null;
        }

        for (Resource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setPid(resourceOne.getPid());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());

            List<Resource> resourceSon = resourceMapper.selectAllByTypeAndPId(RESOURCE_MENU, resourceOne.getId());

            if (resourceSon == null) {
                treeOne.setState("closed");
            } else {
                List<Tree> treeTwoList = new ArrayList<Tree>();

                for (Resource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();

                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());

                    /***************************************************/
                    List<Resource> resourceSons = resourceMapper.selectAllByTypeAndPId(RESOURCE_BUTTON, resourceTwo.getId());

                    if (resourceSons == null) {
                        treeTwo.setState("closed");
                    } else {
                        List<Tree> treeThreeList = new ArrayList<Tree>();

                        for (Resource resourceThree : resourceSons) {
                            Tree treeThree = new Tree();

                            treeThree.setId(resourceThree.getId());
                            treeThree.setText(resourceThree.getName());
                            treeThree.setIconCls(resourceThree.getIcon());
                            treeThree.setAttributes(resourceThree.getUrl());

                            treeThreeList.add(treeThree);
                        }
                        treeTwo.setChildren(treeThreeList);
                    }
                    /***************************************************/

                    treeTwoList.add(treeTwo);
                }
                treeOne.setChildren(treeTwoList);
            }

            treeOneList.add(treeOne);
        }
        return treeOneList;
    }

    @Override
    public List<Tree> selectTree(ShiroUser shiroUser) {
        List<Tree> trees = new ArrayList<Tree>();
        // shiro中缓存的用户角色
        Set<String> roles = shiroUser.getRoles();
        if (roles == null) {
            return trees;
        }
        // 如果有超级管理员权限
        if (roles.contains("admin")) {
            List<Resource> resourceList = this.selectByType(RESOURCE_MENU);
            if (resourceList == null) {
                return trees;
            }
            for (Resource resourceOne : resourceList) {
                Tree treeOne = new Tree();
                treeOne.setId(resourceOne.getId());
                treeOne.setPid(resourceOne.getPid());
                treeOne.setText(resourceOne.getName());
                treeOne.setIconCls(resourceOne.getIcon());
                treeOne.setAttributes(resourceOne.getUrl());
                trees.add(treeOne);
            }
            return trees;
        }
        // 普通用户
        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
        if (roleIdList == null) {
            return trees;
        }
        List<Resource> resourceLists = roleMapper.selectResourceListByRoleIdList(roleIdList);
        if (resourceLists == null) {
            return trees;
        }
        for (Resource resource : resourceLists) {
            Tree treeOne = new Tree();
            treeOne.setId(resource.getId());
            treeOne.setPid(resource.getPid());
            treeOne.setText(resource.getName());
            treeOne.setIconCls(resource.getIcon());
            treeOne.setAttributes(resource.getUrl());
            trees.add(treeOne);
        }
        return trees;
    }

    
}