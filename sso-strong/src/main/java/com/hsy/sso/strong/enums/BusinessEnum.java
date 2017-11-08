package com.hsy.sso.strong.enums;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path java/com.hsy.java.enums
 * @date 2017/10/27 13:38
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public enum BusinessEnum {

    BUSINESS_HANDLE_EXCEPTION("2000","业务处理异常"),
    LOGIN_EXCEPTION("2001","登陆出现异常"),
    ;

    private String code ;
    private String message ;

    BusinessEnum(String code, String message) {
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
