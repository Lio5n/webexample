package com.wanggs.webexample.service;

import com.wanggs.webexample.dao.mapper.UserInfoMapper;
import com.wanggs.webexample.dao.pojo.UserInfo;
import com.wanggs.webexample.dao.pojo.UserInfoExample;
import com.wanggs.webexample.dynamicds.ChooseDataSource;
import com.wanggs.webexample.dynamicds.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ChooseDataSource(DataSourceType.DEFAULT)
public class ReadUserInfo {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> selectUserInfoById(Integer userId) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        if(userId!=null)
            criteria.andUserIdEqualTo(userId);
        else
            criteria.addConditionSql("1=1");

        List<UserInfo> list = userInfoMapper.selectByExample(example);

        return list;
    }
}
