package com.wangzhixuan.mapper;

import com.wangzhixuan.model.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {

    int insert(Resource resource);

    int updateResource(Resource resource);

    List<Resource> findResourceAllByTypeAndPid(@Param("resourceType") Integer resourceType, @Param("pid") Long pid);

    List<Resource> findResourceAll();

    List<Resource> findResourceAllByTypeAndPidNull(Integer resourceMenu);

    Resource findResourceById(Long id);

    int deleteResourceById(Long id);
}