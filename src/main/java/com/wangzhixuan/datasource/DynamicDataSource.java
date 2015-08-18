package com.wangzhixuan.datasource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description：
 * @author：Wangzhixuan 
 * @date：2015年8月18日 下午3:53:26
 */
public class DynamicDataSource extends AbstractRoutingDataSource implements InitializingBean {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSouce();
    }
}
