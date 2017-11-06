package com.hsy.sso.server.better.intercepter;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path paac/com.hsy.paac.interceptor
 * @date 01/09/2017 9:57 AM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
        filterName = "CharacterEncodingFilter",
        urlPatterns = "/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8")
        }
)
public class CharacterEncodingFilter extends org.springframework.web.filter.CharacterEncodingFilter {

}
