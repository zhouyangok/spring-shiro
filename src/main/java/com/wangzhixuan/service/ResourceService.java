package com.wangzhixuan.service;

import java.util.List;

import com.wangzhixuan.model.User;
import com.wangzhixuan.vo.ResourceVo;
import com.wangzhixuan.vo.Tree;

public interface ResourceService {

    List<Tree> findTree(User currentUser);

    List<ResourceVo> findTreeGrid();

}
