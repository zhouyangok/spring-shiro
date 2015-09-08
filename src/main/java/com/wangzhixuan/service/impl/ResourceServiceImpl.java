package com.wangzhixuan.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wangzhixuan.code.BaseResponseEnum;
import com.wangzhixuan.exception.BusinessException;
import com.wangzhixuan.mapper.ResourceMapper;
import com.wangzhixuan.model.Resource;
import com.wangzhixuan.model.User;
import com.wangzhixuan.service.ResourceService;
import com.wangzhixuan.utils.Config;
import com.wangzhixuan.vo.Tree;
@Service
public class ResourceServiceImpl implements ResourceService {

    private static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Tree> findTree(User currentUser) {
        List<Tree> trees = Lists.newArrayList();

        List<Resource> resourceFather = null;

        if(currentUser.getLoginname().equals("admin")) {
            resourceFather = resourceMapper.findResourceAllBytypeAndPidNull(Config.RESOURCE_MENU);
        }else{
            
        }

        if(resourceFather != null) {
            for (Resource resourceOne : resourceFather) {
                Tree treeOne = new Tree();

                treeOne.setId(resourceOne.getId().toString());
                treeOne.setText(resourceOne.getName());
                treeOne.setIconCls(resourceOne.getIcon());
                treeOne.setAttributes(resourceOne.getUrl());
                List<Resource> resourceSon = resourceMapper.findResourceAllBytypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());

                if(resourceSon != null) {
                    List<Tree> tree = Lists.newArrayList();
                    for (Resource resourceTwo : resourceSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(resourceTwo.getId().toString());
                        treeTwo.setText(resourceTwo.getName());
                        treeTwo.setIconCls(resourceTwo.getIcon());
                        treeTwo.setAttributes(resourceTwo.getUrl());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                }else{
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
        }
        return trees;
    }

    @Override
    public List<Resource> findTreeGrid() {
        return resourceMapper.findResourceAll();
    }

    @Override
    public void addResource(Resource resource) {
        resourceMapper.insert(resource);
    }

    @Override
    public List<Tree> findAllTree() {
        List<Tree> trees = Lists.newArrayList();
        List<Resource> resources = resourceMapper.findResourceAllBytypeAndPidNull(Config.RESOURCE_MENU);
        if(resources != null) {
            for (Resource resourceOne : resources) {
                Tree treeOne = new Tree();

                treeOne.setId(resourceOne.getId().toString());
                treeOne.setText(resourceOne.getName());
                treeOne.setIconCls(resourceOne.getIcon());
                treeOne.setAttributes(resourceOne.getUrl());
                List<Resource> resourceSon = resourceMapper.findResourceAllBytypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());

                if(resourceSon != null) {
                    List<Tree> tree = Lists.newArrayList();
                    for (Resource resourceTwo : resourceSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(resourceTwo.getId().toString());
                        treeTwo.setText(resourceTwo.getName());
                        treeTwo.setIconCls(resourceTwo.getIcon());
                        treeTwo.setAttributes(resourceTwo.getUrl());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                }else{
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
        }
        return trees;
    }

    @Override
    public void updateResource(Resource resource) {
        int update = resourceMapper.updateResource(resource);
        if(update != 1){
            throw new BusinessException(BaseResponseEnum.INNER_ERROR);
        }
    }

    @Override
    public Resource findResourceById(Long id) {
        return resourceMapper.findResourceById(id);
    }

    @Override
    public void deleteResourceById(Long id) {
        int delete = resourceMapper.deleteResourceById(id);
        if(delete != 1){
            throw new BusinessException(BaseResponseEnum.INNER_ERROR);
        }
    }

}
