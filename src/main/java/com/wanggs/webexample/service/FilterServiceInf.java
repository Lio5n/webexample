package com.wanggs.webexample.service;

import com.wanggs.webexample.dao.pojo.UserInfo;

import java.util.List;

public interface FilterServiceInf {
    List<UserInfo> run(Integer userId);
}
