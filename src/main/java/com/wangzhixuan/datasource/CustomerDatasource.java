package com.wangzhixuan.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.wangzhixuan.utils.DataSourceKeyHolder;

/**
 * @description：
 * @author：Wangzhixuan
 * @date：2015年8月19日 下午10:57:07
 */
public class CustomerDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceKeyHolder.getDataSourceKey();
    }
}
