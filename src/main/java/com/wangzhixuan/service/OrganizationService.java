package com.wangzhixuan.service;

import java.util.List;

import com.wangzhixuan.model.Organization;
import com.wangzhixuan.vo.Tree;

public interface OrganizationService {

    List<Tree> findTree();

    List<Organization> findTreeGrid();

}
