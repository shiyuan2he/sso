package com.hsy.sso.web.simple.filter;
import com.hsy.java.base.string.StringHelper;
import com.hsy.java.util.cache.jvm.TicketCache;
import com.hsy.sso.base.common.enums.SsoEnum;

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
@WebFilter("/")
public class SSOServerFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookies = request.getCookies();
        String username = "";
        String ticket = request.getParameter("ticket");
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (null != TicketCache.get(cookie.getName())) { // 一定路
                    username = cookie.getValue();
                    break;
                }
            }
        }

        String serviceUrl = request.getParameter("backUrl");
        if (null == serviceUrl && null != ticket) {//有通票但没有回调地址，放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (StringHelper.isNotNullOrEmpty(username)) { //已登陆

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
}
