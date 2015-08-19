package com.wangzhixuan.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.wangzhixuan.utils.DataSourceKeyHolder;

/**
 * User:Amos.zhou
 * Date: 14-3-14
 * Time: 下午5:27
 */
public class CustomerDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceKeyHolder.getDataSourceKey();
    }
}
