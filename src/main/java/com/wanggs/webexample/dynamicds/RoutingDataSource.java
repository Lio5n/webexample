package com.wanggs.webexample.dynamicds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey(){
        DataSourceType dataSourceName = DynamicDataSourceHolder.getDataSourceType();
        if(dataSourceName==null)
            dataSourceName = DataSourceType.DEFAULT;

        return dataSourceName.getName();
    }
}
