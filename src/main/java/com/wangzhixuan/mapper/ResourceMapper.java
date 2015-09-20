package com.wangzhixuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangzhixuan.model.Resource;

public interface ResourceMapper {

    int insert(Resource resource);

    int updateResource(Resource resource);

    List<Resource> findResourceAllBytypeAndPid(@Param("resourcemenu")Integer resourcemenu, @Param("pid")Long pid);

    List<Resource> findResourceAll();

    List<Resource> findResourceAllBytypeAndPidNull(Integer resourceMenu);

    Resource findResourceById(Long id);

    int deleteResourceById(Long id);
}