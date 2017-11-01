package com.hsy.sso.service.common;

import com.hsy.bean.vo.SessionBean;
/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.service.jdbc
 * @date 20/10/2017 1:18 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public interface ITSsoUserService {
    /**
     * @description <p>登陆</p>
     * @param mobile 手机号
     * @param password 用户密码
     * @return 是否登陆成功
     * @author heshiyuan
     * @date 20/10/2017 1:19 PM
     */
    SessionBean login(Long mobile, String password);
    /**
     * @description <p>注册</p>
     * @param mobile 手机号
     * @param password 用户密码
     * @return 是否注册成功
     * @author heshiyuan 
     * @date 2017/10/27 09:49
     */
    boolean reg(Long mobile,String password) ;
}
