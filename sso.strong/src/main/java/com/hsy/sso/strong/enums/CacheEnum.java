package com.hsy.sso.strong.enums;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path java/com.hsy.java.enums
 * @date 2017/10/27 14:38
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public enum CacheEnum {

    CACHE__EXCEPTION("4000","用户信息缓存过期，请重新登陆"),
    CACHE_LOGIN_EXPIRE_EXCEPTION("4001","用户信息缓存过期，请重新登陆"),
    ;
    private String code ;
    private String message ;

    CacheEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
