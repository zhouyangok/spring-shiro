package com.wangzhixuan.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.wangzhixuan.commons.result.Tree;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.Resource;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IResourceService extends ISuperService<Resource> {

    List<Resource> selectAll();

    List<Tree> selectAllTree();

    List<Tree> selectAllTrees();

    List<Tree> selectTree(ShiroUser shiroUser);

}