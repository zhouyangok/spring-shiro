package com.wangzhixuan.mapper;

import com.wangzhixuan.datasource.DataSourceRouting;
import com.wangzhixuan.datasource.DbKey;

public interface SlaveMapper {

    @DataSourceRouting(dbkey = DbKey.SLAVE)
    Integer count();

}
