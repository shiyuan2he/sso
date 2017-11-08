package com.hsy.sso.strong.exception;

import com.hsy.sso.strong.enums.WebEnum;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path java/com.hsy.java.exception.web
 * @date 2017/10/27 14:17
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class WebException extends RuntimeException{
    private String code ;
    private String msg ;

    public WebException(WebEnum webEnum) {
        super(webEnum.getMessage());
        this.code = webEnum.getCode() ;
    }
    public WebException(String message) {
        super(message);
    }

    public WebException(String code, String message) {
        super(message);
        this.code = code ;
    }
    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
