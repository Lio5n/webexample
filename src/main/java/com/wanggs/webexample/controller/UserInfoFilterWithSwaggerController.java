package com.wanggs.webexample.controller;

import com.wanggs.webexample.dao.pojo.UserInfo;
import com.wanggs.webexample.service.FilterServiceInf;
import com.wanggs.webexample.service.UserInfoFilterServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(value = "userInfoSwagger",tags = "userInfoSwagger接口")
public class UserInfoFilterWithSwaggerController {
    // http://localhost:8080/SSM/swagger-ui.html

    @Autowired
    @Qualifier("userInfoFilterServiceImpl")
    private FilterServiceInf filterServiceInf;

    @ApiOperation(value = "测试API",notes = "测试API",httpMethod = ("GET"),response = UserInfo.class)
    @RequestMapping("/readUserInfoByIdWithSwagger/{userId}")
    @ResponseBody
    public UserInfo readUserInfoById(@PathVariable Integer userId) {
        List<UserInfo> userInfoList = filterServiceInf.run(userId);
        if(userInfoList.size()==0)
            return null;
        else
            return userInfoList.get(0);
    }
}
