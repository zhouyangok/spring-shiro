package com.wangzhixuan.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wangzhixuan.datasource.DbKey;

/**
 * @Description：注解式数据源，用来进行数据源切换
 * @author：Wangzhixuan 
 * @date：2015年8月19日 下午5:14:11
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceRouting {
    String dbkey() default DbKey.MASTER;
}