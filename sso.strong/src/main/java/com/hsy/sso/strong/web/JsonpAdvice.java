package com.hsy.sso.strong.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.strong.advice
 * @date 2017/11/1 09:11
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
//@ControllerAdvice(basePackages = "com.hsy.sso.strong.controller")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public JsonpAdvice(){
        super("callback") ;
    }
}
