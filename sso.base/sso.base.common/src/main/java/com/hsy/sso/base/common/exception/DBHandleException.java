package com.hsy.sso.base.common.exception;

import com.hsy.java.base.string.StringHelper;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.base.common.exception
 * @date 20/10/2017 2:07 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class DBHandleException extends RuntimeException {

    /**
     * 特殊意义的编码
     */
    private String code;
    /**
     * 消息
     */
    private String msg;

    public DBHandleException(String msg){
        super(msg);
        this.msg = msg ;
    }

    public DBHandleException(String code,String msg){
        this.code = code ;
        this.msg = msg ;
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
