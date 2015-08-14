package com.wangzhixuan.service;

import java.util.List;

import com.wangzhixuan.model.Resource;
import com.wangzhixuan.model.User;
import com.wangzhixuan.vo.Tree;

public interface ResourceService {

    List<Tree> tree(User currentUser);

    List<Resource> treeGrid();

}
