package com.hsy.sso.strong.exception;

import com.hsy.sso.strong.enums.WebEnum;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path java/com.hsy.java.exception.web
 * @date 2017/10/27 14:18
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class ParamValidationException extends WebException{
    public ParamValidationException(WebEnum webEnum) {
        super(webEnum);
    }

    public ParamValidationException(String message) {
        super(message);
    }

    public ParamValidationException(String code, String message) {
        super(code, message);
    }

    public ParamValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
