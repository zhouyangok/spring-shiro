package com.wangzhixuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangzhixuan.model.User;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.UserVo;

public interface UserMapper {
    int deleteById(Long id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User user);

    int updateUser(User user);

    User findUserByLoginName(String username);

    User findUserById(Long id);

    List findUserPageCondition(PageInfo pageInfo);

    int findUserPageCount(PageInfo pageInfo);

    void updateUserPwdById(@Param("userId") Long userId, @Param("pwd") String pwd);

    UserVo findUserVoById(Long id);
}