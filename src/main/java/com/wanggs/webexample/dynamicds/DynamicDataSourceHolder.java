package com.wanggs.webexample.dynamicds;

public class DynamicDataSourceHolder {
    // 解决线程安全问题
    private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<>();

    public static void putDataSourceType(DataSourceType dataSourceType) {
        holder.set(dataSourceType);
    }

    public static DataSourceType getDataSourceType() {
        return holder.get();
    }
}
