package com.hsy.sso.client.config;

import com.hsy.java.java.base.utils.PropertiesFileUtil;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.client.filter.config
 * @date 2018/1/3 13:55
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class SsoClientConfig {
    private static PropertiesFileUtil instance ;
    static{
        instance = PropertiesFileUtil.getInstance("config") ;
    }
    /**
     * @description <p>获取sso登陆接口地址</p>
     * @author heshiyuan
     * @date 2018/1/3 14:08
     */
    public static String getSsoServer(){
        return instance.get("api.sso.server") ;
    }
    /**
     * @description <p>获取sso登陆接口地址</p>
     * @author heshiyuan
     * @date 2018/1/3 14:08
     */
    public static String getSsoServerApiLogin(){
        return getSsoServer() + instance.get("api.sso.server.login") ;
    }
    /**
     * @description <p>获取sso校验令牌接口地址</p>
     * @author heshiyuan
     * @date 2018/1/3 14:08
     */
    public static String getSsoServerApiTicket(){
        return getSsoServer() + instance.get("api.sso.server.ticket");
    }
    /**
     * @description <p>获取sso注销接口地址</p>
     * @author heshiyuan
     * @date 2018/1/3 14:08
     */
    public static String getSsoServerApiLogout(){
        return getSsoServer() + instance.get("api.sso.server.logout") + "/";
    }
}
