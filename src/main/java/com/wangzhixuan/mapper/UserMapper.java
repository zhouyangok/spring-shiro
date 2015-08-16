package com.wangzhixuan.mapper;

import java.util.List;

import com.wangzhixuan.model.User;
import com.wangzhixuan.utils.PageInfo;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findUserByLoginName(String username);

    User findUserById(Long id);

    List findUserPageCondition(PageInfo pageInfo);

    int findUserPageCount(PageInfo pageInfo);
}