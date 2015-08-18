package com.wangzhixuan.datasource;
/**
 * @Description：
 * @author：Wangzhixuan 
 * @date：2015年8月18日 下午3:53:37
 */
public class DynamicDataSourceHolder {

    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String name) {
        holder.set(name);
    }

    public static String getDataSouce() {
        return holder.get();
    }

    public static void clearDbType() {
        holder.remove();
    }
}
