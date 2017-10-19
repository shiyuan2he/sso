package com.hsy.sso.blog.filter;

import com.hsy.java.httpclient.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.http.HttpClient;

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
    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        _logger.info("【SSOClientFilter】进入到blog系统拦截器");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String ticket = request.getParameter("ticket");
        String url = URLEncoder.encode(request.getRequestURL().toString(), "UTF-8");

        if (null == username) {//未登录
            _logger.info("【SSOClientFilter】检测到用户未登录blog系统");
            if (null != ticket && !"".equals(ticket)) {//获取通票
                _logger.info("【SSOClientFilter-检票】监测到用户手中有通票");
                Map<String,String> paramMap = new HashMap<>() ;
                // 用通票获取用户信息
                paramMap.put("ticket", ticket) ;
                _logger.info("【SSOClientFilter-验票】将用户手中通票跟sso服务器中的票进行验证。");
                username = HttpClientUtils.sendHttpPost("http://localhost:8080/sso/ticket",paramMap) ;
                if (null != username && !"".equals(username)) {
                    _logger.info("【SSOClientFilter-验票】用户手中通票跟sso服务验票大厅中的票匹配，允许通过");
                    session.setAttribute("username", username);
                    filterChain.doFilter(request, response);
                } else {
                    _logger.info("【SSOClientFilter-验票】用户手中通票跟sso服务验票大厅中的票不匹配，" +
                            "不允许通过，即将跳转到sso登陆页去购票");
                    response.sendRedirect("http://localhost:8080/sso/login.jsp?serviceUrl=" + url);
                }
            } else {
                _logger.info("【SSOClientFilter-检票】监测到用户手中没有通票。即将跳转到sso服务登陆页面去进行登陆，买票");
                response.sendRedirect("http://localhost:8080/sso/login.jsp?serviceUrl=" + url);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
