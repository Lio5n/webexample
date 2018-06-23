package com.wanggs.webexample.controller;

import com.wanggs.webexample.dao.pojo.UserInfo;
import com.wanggs.webexample.service.FilterServiceInf;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Api(value = "userInfo",tags = "userInfo接口")
public class UserInfoController {
    // http://localhost:8080/SSM/

    @Autowired
    @Qualifier("userInfoFilterServiceImpl")
    private FilterServiceInf filterServiceInf;

    //@ApiOperation(value = "测试API",notes = "测试API",httpMethod = ("GET"),response = UserInfo.class)
    @RequestMapping(value = "/readUserInfoById")
    //@ResponseBody
    public ModelAndView readUserInfoById(Integer userId) {
        List<UserInfo> userInfoList = filterServiceInf.run(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfoList",userInfoList);
        modelAndView.setViewName("userInfoList");
        if(userInfoList.size()==0) {
            ModelAndView modelAndViewIndex = new ModelAndView();
            modelAndViewIndex.setViewName("errorPage");
            return modelAndViewIndex;
        }
        else
            return modelAndView;
    }
}
