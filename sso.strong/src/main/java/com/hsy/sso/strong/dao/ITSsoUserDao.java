package com.hsy.sso.strong.dao;


import com.hsy.sso.strong.bean.entity.TSsoUser;

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
     * @param mobile 手机号
     * @param password 用户密码
     * @return 用户信息对象
     * @author heshiyuan 
     * @date 2017/10/26 11:27 
     */
    TSsoUser selectUser(Long mobile, String password) ;
    /**
     * @description <p></p>
     * @param user 用户信息
     * @return 插入条数
     * @author heshiyuan 
     * @date 2017/10/26 13:10
     */
    int insertUser(TSsoUser user);
}
