package com.hsy.sso.strong.enums;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path java/com.hsy.java.enums
 * @date 2017/10/27 08:53
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public enum DBEnum {
    // 数据库操作异常
    DB_SELECT_IS_NULL("DB1001","db select return null"),
    DB_EXECUTE_EXCEPTION("DB1000","数据库执行异常"),
    DB_INSERT_RESULT_ERROR("DB1001", "db insert error"),
    DB_UPDATE_RESULT_ERROR("DB1001", "db update error"),
    ;


    private String code ;
    private String message ;

    DBEnum(String code, String message) {
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
