package com.wangzhixuan.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Description：aop切面
 * @author：Wangzhixuan 
 * @date：2015年8月18日 下午3:52:54
 */
public class DataSourceAspect  {
    private static Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    public void before(JoinPoint point) throws Throwable {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();

        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            
            boolean annotationPresent = m.isAnnotationPresent(DataSourceRouting.class);
            
            if (m != null && m.isAnnotationPresent(DataSourceRouting.class)) {
                DataSourceRouting data = m.getAnnotation(DataSourceRouting.class);
                DynamicDataSourceHolder.putDataSource(data.dbkey());
                logger.info("主库从库={}", data.dbkey());
            } else {
                DynamicDataSourceHolder.putDataSource(DbKey.MASTER);
                logger.info("主库从库={}", DbKey.MASTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
