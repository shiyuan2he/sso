package com.hsy.sso.server.best.service;

import com.hsy.java.bean.vo.SessionBean;
import com.hsy.java.bean.vo.UserInfoBean;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.server.best.service
 * @date 2017/12/25 16:39
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public interface IUserService {
    /**
     * @description <p>登陆</p>
     * @param mobile 手机号
     * @param password 用户密码
     * @return 是否登陆成功
     * @author heshiyuan
     * @date 20/10/2017 1:19 PM
     */
    SessionBean login(Long mobile, String username, String password);

    /**
     * @description <p>退出</p>
     * @param
     * @return
     * @author heshiyuan
     * @date 2017/11/2 14:33
     */
    boolean logout(String ticket);
}
