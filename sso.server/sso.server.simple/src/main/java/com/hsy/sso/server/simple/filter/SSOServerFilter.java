package com.hsy.sso.server.simple.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.server.simple.filter
 * @date 18/10/2017 3:16 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@WebFilter("/*")
public class SSOServerFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String ticket = request.getParameter("ticket") ;
        String requestUrl = request.getParameter("requestUrl") ;
        Cookie[] cookies = request.getCookies() ;
        String username = "" ;
        for(Cookie cookie : cookies){
            if(StringUtils.isNotBlank(cookie.getName())&&StringUtils.equals("sso",cookie.getName())){
                username = cookie.getValue() ;
                break ;
            }
        }
        if(null == requestUrl && null != ticket){
            filterChain.doFilter(servletRequest,servletResponse);
            return ;
        }

    }

    @Override
    public void destroy() {}
}
