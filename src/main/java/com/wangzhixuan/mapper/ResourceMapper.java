package com.wangzhixuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangzhixuan.model.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> findResourceAllBytypeAndPid(@Param("resourcemenu")Integer resourcemenu, @Param("pid")Long pid);

    List<Resource> findResourceAll();

    List<Resource> findResourceAllBytypeAndPidNull(Integer resourceMenu);
}