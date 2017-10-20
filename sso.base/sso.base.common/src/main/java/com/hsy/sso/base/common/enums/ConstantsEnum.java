package com.hsy.sso.base.common.enums;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path framework/com.hsy.enums
 * @date 23/08/2017 11:06 AM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public enum ConstantsEnum {
    SESSION_KEY("sessionKey","session的key"),
    // 系统异常
    SESSION_IS_OUT_TIME("99980003", "会话超时"),
    RETURN_COMMON_FAILURE("NB9999","操作失败，请联系管理员!"),
    RETURN_COMMON_SUCCESS("NB0000","操作成功！"),
    // 数据库操作异常
    DB_SELECT_IS_NULL("NB2000","db select return null"),
    DB_SELECT_SQL_EXCEPTION("SSO1000","sql execute exception"),
    DB_INSERT_RESULT_ERROR("NB2001", "db insert error"),
    DB_UPDATE_RESULT_ERROR("NB2002", "db update error"),

    // 用户相关异常
    INVALID_USER("1001001", "无效用户");

    private String code ;
    private String msg ;

    ConstantsEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
