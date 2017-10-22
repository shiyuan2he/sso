package com.hsy.sso.service.jdbc.impl;

import com.hsy.java.base.string.StringHelper;
import com.hsy.sso.dao.jdbc.ITSsoUserDao;
import com.hsy.sso.dao.jdbc.impl.BaseDaoImpl;
import com.hsy.sso.dao.jdbc.impl.TSsoUserDaoImpl;
import com.hsy.sso.service.jdbc.ITSsoUserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.service.jdbc
 * @date 20/10/2017 1:17 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class TSsoUserServiceImpl implements ITSsoUserService{
    BaseDaoImpl baseDao = new BaseDaoImpl();
    @Override
    public boolean login(String username, String password){
        if(StringHelper.isNotNullOrEmpty(username)&&StringHelper.isNotNullOrEmpty(password)){
            Map<String,Object> paramMap = new HashMap<>() ;
            paramMap.put("user_name",username) ;
            paramMap.put("password",password) ;
            List<Map<String,Object>> list = new ArrayList<>() ;
            list.add(paramMap) ;
            String sql = "select * from t_sso_user " ;
            List<Map<String,Object>> returnList = baseDao.selectList(sql,list) ;
            if(returnList.size()==1){
                return true ;
            }
        }
        return false;
    }
}
