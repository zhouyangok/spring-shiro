package com.wangzhixuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wangzhixuan.mapper.ResourceMapper;
import com.wangzhixuan.model.Resource;
import com.wangzhixuan.model.User;
import com.wangzhixuan.service.ResourceService;
import com.wangzhixuan.utils.Config;
import com.wangzhixuan.vo.Tree;
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Tree> tree(User currentUser) {
        List<Tree> trees = Lists.newArrayList();
        List<Resource> resourceFather = null;
        if(currentUser.getLoginname().equals("admin")){
            resourceFather = resourceMapper.findResourceAllBytype(Config.resourceMenu);
        }else{
                
        }
        if(resourceFather != null){
            for (Resource resource : resourceFather) {
                Tree tree = new Tree();
                List<Resource> resourceSon = resourceMapper.findResourceAllBytypeAndPid(Config.resourceMenu, resource.getId());
                if(resourceSon != null){
                    
                }
            }
        }
        return null;
    }

}
