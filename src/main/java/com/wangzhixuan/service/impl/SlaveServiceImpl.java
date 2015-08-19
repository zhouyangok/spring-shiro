package com.wangzhixuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangzhixuan.annotation.DataSourceRouting;
import com.wangzhixuan.datasource.DbKey;
import com.wangzhixuan.mapper.SlaveMapper;
import com.wangzhixuan.service.SlaveService;
@Service
public class SlaveServiceImpl implements SlaveService {

    @Autowired
    private SlaveMapper slaveMapper;

    @Override
    @DataSourceRouting(dbkey = DbKey.SLAVE)
    public Integer count() {
        return slaveMapper.count();
    }


}
