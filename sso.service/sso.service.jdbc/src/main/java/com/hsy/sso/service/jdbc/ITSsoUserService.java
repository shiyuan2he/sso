package com.hsy.sso.service.jdbc;

import java.sql.SQLException;

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
     * @param username 用户名
     * @param password 用户密码
     * @return No such property: code for class: Script1
     * @author heshiyuan
     * @date 20/10/2017 1:19 PM
     * @email shiyuan4work@sina.com
     * @github https://github.com/shiyuan2he.git
     * Copyright (c) 2016 shiyuan4work@sina.com All rights reserved
     */
    boolean login(String username,String password);
}
