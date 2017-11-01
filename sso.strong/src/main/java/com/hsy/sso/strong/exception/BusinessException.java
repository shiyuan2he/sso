package com.hsy.sso.strong.exception;

import com.hsy.sso.strong.enums.BusinessEnum;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path java/com.hsy.java.exception.service
 * @date 2017/10/27 13:37
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class BusinessException extends RuntimeException {
    private String code ;
    private String msg ;
    public BusinessException(BusinessEnum businessEnum) {
        this.code = businessEnum.getCode();
        this.msg = businessEnum.getMessage();
    }
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    public BusinessException(String code , String message) {
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
