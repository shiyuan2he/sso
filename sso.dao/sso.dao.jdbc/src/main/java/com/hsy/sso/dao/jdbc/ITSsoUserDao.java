package com.hsy.sso.dao.jdbc;

import com.hsy.sso.base.entity.sso.TSsoUser;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.dao.sso
 * @date 20/10/2017 10:33 AM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public interface ITSsoUserDao {
    /**
     * @description <p>根据用户名，密码查询用户信息</p>
     * @param username 用户名
     * @param password 用户密码
     * @return 用户信息对象
     * @author heshiyuan 
     * @date 2017/10/26 11:27 
     */
    TSsoUser selectUser(String username,String password) ;
    /**
     * @description <p></p>
     * @param user 用户信息
     * @return 插入条数
     * @author heshiyuan 
     * @date 2017/10/26 13:10
     */
    int insertUser(TSsoUser user);
}
