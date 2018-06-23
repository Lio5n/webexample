package com.wanggs.webexample;

import com.wanggs.webexample.dao.pojo.UserInfo;
import com.wanggs.webexample.service.UserInfoFilterServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ReadUserInfoTest {
    @Test
    public void readUserInfoTest() {
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
