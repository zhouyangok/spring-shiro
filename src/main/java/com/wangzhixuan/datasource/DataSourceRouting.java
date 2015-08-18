package com.wangzhixuan.datasource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Description：自定义注解
 * @author：Wangzhixuan 
 * @date：2015年8月18日 下午3:52:23
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceRouting {
    String dbkey() default DbKey.MASTER;
}
