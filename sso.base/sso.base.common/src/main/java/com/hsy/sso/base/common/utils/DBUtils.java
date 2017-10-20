package com.hsy.sso.base.common.utils;

import com.hsy.java.util.jdbc.JdbcHelper;
import com.hsy.sso.base.common.constants.DataSourceContant;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.base.common.utils
 * @date 20/10/2017 10:45 AM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class DBUtils{
    private static JdbcHelper jdbc = null ;
    /**
     * @description <p>启动项目初始化JdbcHelper构造器</p>
     * @author heshiyuan
     * @date 20/10/2017 11:50 AM
     */
    static{
        jdbc = new JdbcHelper(DataSourceContant.getDriver(),DataSourceContant.getUrl(),DataSourceContant.getUsername(),DataSourceContant.getPassword()) ;
    }

    public static JdbcHelper getInstance() {
        return jdbc;
    }
}
