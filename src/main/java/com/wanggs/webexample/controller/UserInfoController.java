package com.wanggs.webexample.controller;

import com.wanggs.webexample.dao.pojo.UserInfo;
import com.wanggs.webexample.service.FilterServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserInfoController {
    // http://localhost:8080/SSM/

    @Autowired
    @Qualifier("userInfoFilterServiceImpl")
    private FilterServiceInf filterServiceInf;

    @RequestMapping(value = "/readUserInfoById")
    public ModelAndView readUserInfoById(Integer userId) {
        List<UserInfo> userInfoList = filterServiceInf.run(userId);
        ModelAndView modelAndViewIndex = new ModelAndView();
        modelAndViewIndex.setViewName("errorPage");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userInfoList",userInfoList);
        modelAndView.setViewName("userInfoList");
        if(userInfoList.size()==0)
            return modelAndViewIndex;
        else
            return modelAndView;
    }
}
