package com.hsy.sso.cms.filter;

import com.hsy.java.httpclient.utils.HttpClientUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.blog.filter
 * @date 19/10/2017 9:39 AM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@WebFilter("/*")
public class SSOClientFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String ticket = request.getParameter("ticket");
        String url = URLEncoder.encode(request.getRequestURL().toString(), "UTF-8");

        if (null == username) {
            if (null != ticket && !"".equals(ticket)) {
                Map<String,String> paramMap = new HashMap<>() ;
                paramMap.put("ticket", ticket) ;
                username = HttpClientUtils.sendHttpPost("http://localhost:8080/sso/ticket",paramMap) ;
                if (null != username && !"".equals(username)) {
                    session.setAttribute("username", username);
                    filterChain.doFilter(request, response);
                } else {
                    response.sendRedirect("http://localhost:8080/sso/login.jsp?serviceUrl=" + url);
                }
            } else {
                response.sendRedirect("http://localhost:8080/sso/login.jsp?serviceUrl=" + url);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
