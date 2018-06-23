package com.wanggs.webexample.dynamicds;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChooseDataSource {

    /**
     * 要切换的数据库。默认值为(@code DataSourceType.DEFAULT)
     *
     * @return 数据库枚举值
     */
    DataSourceType value() default DataSourceType.DEFAULT;
}
