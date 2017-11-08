package com.hsy.sso.strong.exception;
import com.hsy.sso.strong.enums.DBEnum;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path java/com.hsy.java.exception.dao
 * @date 2017/10/27 08:50
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class DBHandleException extends RuntimeException{

    private String code ;
    private String msg ;
    public DBHandleException(DBEnum dbEnum) {
        this.code = dbEnum.getCode();
        this.msg = dbEnum.getMessage();
    }
    public DBHandleException(String message) {
        super(message);
    }

    public DBHandleException(String message, Throwable cause) {
        super(message, cause);
    }
    public DBHandleException(String code , String message) {
        super(message);
        this.code = code ;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
