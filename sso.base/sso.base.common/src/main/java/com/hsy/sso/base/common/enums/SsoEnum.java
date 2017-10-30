package com.hsy.sso.base.common.enums;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.base.common.enums
 * @date 2017/10/27 15:11
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public enum SsoEnum {

    SSO_KEY_USER_SESSION("sso_key_user_session","session key值"),
    SSO_KEY_TICKET_COOKIE("sso_key_ticket_cookie","cookie中ticket的键"),
    ;

    private String code ;
    private String message ;

    SsoEnum(String code, String message) {
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
