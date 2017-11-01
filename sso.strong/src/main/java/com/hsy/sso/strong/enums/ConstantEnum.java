package com.hsy.sso.strong.enums;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path java/com.hsy.java.enums
 * @date 2017/10/27 09:41
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public enum ConstantEnum {
    ENCRYPTION_TYPE_BASE64("BASE64", "BASE64加密类型"),
    SESSION_KEY("sessionKey","session的key"),
    // 系统异常
    SESSION_IS_OUT_TIME("99980003", "会话超时"),
    RETURN_COMMON_FAILURE("NB9999","操作失败，请联系管理员!"),
    RETURN_COMMON_SUCCESS("NB0000","操作成功！"),
    ;
    private String code;
    private String message;

    ConstantEnum(String code, String message) {
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
