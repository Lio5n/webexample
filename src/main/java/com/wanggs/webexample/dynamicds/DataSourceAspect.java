package com.wanggs.webexample.dynamicds;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect implements Ordered {

    /**
     * 切面设置要选择的数据源
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(ChooseDataSource) || @within(ChooseDataSource))")
    public Object permission(ProceedingJoinPoint joinPoint) throws Throwable {
        DataSourceType old = DynamicDataSourceHolder.getDataSourceType();
        ChooseDataSource targetDataSource = getDataSourceFromTarget(joinPoint);
        if(targetDataSource!=null) {
            DataSourceType type = targetDataSource.value();
            DynamicDataSourceHolder.putDataSourceType(type);
        }

        Object obj = joinPoint.proceed();

        DynamicDataSourceHolder.putDataSourceType(old);     // 还原
        return obj;
    }

    private ChooseDataSource getDataSourceFromTarget(ProceedingJoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        if(method!=null) {
            ChooseDataSource dataSource = method.getAnnotation(ChooseDataSource.class);
            if(dataSource==null) {
                Class clazz = joinPoint.getTarget().getClass();
                dataSource = (ChooseDataSource)clazz.getAnnotation(ChooseDataSource.class);
            }
            return dataSource;
        }
        return null;
    }

    /**
     * 获取切面方法
     *
     * @param joinPoint
     * @return
     * @throws NoSuchMethodException
     */
    private Method getMethod(ProceedingJoinPoint joinPoint) {
        Object object = joinPoint.getTarget();      // 目标实例
        Class targetClass = joinPoint.getTarget().getClass();
        String methdoName = joinPoint.getSignature().getName(); // 被代理的方法名
        Object[] args = joinPoint.getArgs();    // 被代理方法的参数
        Class[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();   // 被代理方法的参数类型

        Method method = null;
        try {
            method = targetClass.getMethod(methdoName,parameterTypes);  // 通过反射获得拦截的method
            if(method.isBridge()) {     // 如果是桥，则要获得实际拦截的method
                for(int i=0;i<args.length;i++) {
                    Class genClazz = targetClass.getGenericSuperclass().getClass();     // 获得泛型类型
                    if(args[i].getClass().isAssignableFrom(genClazz))   // 根据实际参数类型替换parameterType中的类型
                        parameterTypes[i] = genClazz;
                }
                method = targetClass.getMethod(methdoName,parameterTypes);
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        return method;
    }

    @Override
    public int getOrder() {
        // 指定切面执行顺序。在事务配置处指定事务的order，并且order的值要大于此处的值，实现事务开启前切换数据库
        // <tx:annotation-driven transaction-manager="transactionManager" order="2"/>
        return 0;
    }
}
