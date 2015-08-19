package com.wangzhixuan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @description：将数据源的键存入ThreadLocal中
 * @author：Wangzhixuan
 * @date：2015年8月19日 下午10:57:41
 */
public class DataSourceKeyHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    private static Logger logger = LoggerFactory.getLogger(DataSourceKeyHolder.class);

    public static void setDataSourceKey(String dataSourceKey) {
        contextHolder.set(dataSourceKey);
    }

    public static String getDataSourceKey() {
        String key = contextHolder.get();
        logger.info("Thread:"+Thread.currentThread().getName()+"dataSource key is "+ key);
        return key;
    }

    public static void clearDataSourceKey() {
        contextHolder.remove();
    }
}
