package com.wanggs.webexample.service;

import com.wanggs.webexample.dao.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
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

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "classpath:spring/applicationContext-service.xml",
                "classpath:spring/applicationContext-dao.xml"
        });
        context.start();

        UserInfoFilterServiceImpl userInfoFilterService = (UserInfoFilterServiceImpl) context.getBean("userInfoFilterServiceImpl");
        List<UserInfo> userInfos = userInfoFilterService.run(null);
        System.out.println(userInfos.get(0).getName());
        System.out.println(userInfos.get(1).getName());
    }
}
