package com.wanggs.webexample.dynamicds;

public enum DataSourceType {

    DEFAULT("dataSource_webexample"),
    WEBEXAMPLE("dataSource_webexample"),
    ERROR("dataSource_error");

    private String name;

    DataSourceType(String dataSourceName) {
        this.name=dataSourceName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }
}
