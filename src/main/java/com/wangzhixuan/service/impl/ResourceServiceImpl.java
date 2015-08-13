package com.wangzhixuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(currentUser.getLoginname().equals("admin")){
            List<Resource> resources= resourceMapper.findResourceAllBytype(Config.resourceMenu);
        }
        return null;
    }

}
