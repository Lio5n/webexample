package com.wanggs.webexample.service;

import com.wanggs.webexample.dao.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoFilterServiceImpl implements FilterServiceInf{

    @Autowired
    private ReadUserInfo readUserInfo;

    @Override
    public List<UserInfo> run(Integer userId) {
        List<UserInfo> userInfos = readUserInfo.selectUserInfoById(userId);
        return userInfos;
    }

}
