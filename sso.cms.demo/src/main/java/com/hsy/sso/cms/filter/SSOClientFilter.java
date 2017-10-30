package com.hsy.sso.cms.filter;

import com.hsy.bean.vo.SessionBean;
import com.hsy.java.base.string.StringHelper;
import com.hsy.java.httpclient.utils.HttpClientUtils;
import com.hsy.sso.base.common.enums.SsoEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
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
    private static final Logger _logger = LoggerFactory.getLogger(SSOClientFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        _logger.info("【cms系统检票处】进入到blog系统拦截器");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String ticket = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            _logger.info("【cms系统检票处】获取到的cookie信息：name={},value={}", cookie.getName(), cookie.getValue());
            if (SsoEnum.SSO_KEY_TICKET_COOKIE.getCode().equals(cookie.getName())) {
                ticket = cookie.getValue();
            }
        }
        //String url = URLEncoder.encode(request.getRequestURL().toString(), "UTF-8");
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode()) ;
        if(null == sessionBean){
            // 判断是否有通票
            if (StringHelper.isNotNullOrEmpty(ticket)) {//获取通票
                _logger.info("【cms系统检票处】监测到请求中有通票");
                Map<String, String> paramMap = new HashMap<>();
                // 用通票获取用户信息
                _logger.info("【cms系统检票处】将用户手中通票跟sso服务器中的票进行验证。");
                String sessionInfo = HttpClientUtils.sendHttpGet("http://localhost:8080/sso/ticket?ticket="+ticket);
                if (sessionInfo.toLowerCase().contains("username")) {
                    _logger.info("【cms系统检票处】用户手中通票跟sso服务验票大厅中的票匹配，允许通过");
                    request.getSession().setAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode(),sessionInfo);
                    filterChain.doFilter(request, response);
                } else {
                    _logger.info("【cms系统检票处】用户手中通票跟sso服务验票大厅中的票不匹配，" +
                            "不允许通过，即将跳转到sso登陆页去购票");
                    response.sendRedirect("/WEB-INF/jsp/login.jsp");
                }
            } else {
                _logger.info("【cms系统检票处】监测到用户手中没有通票。即将跳转到sso服务登陆页面去进行登陆，买票");
                response.sendRedirect("/WEB-INF/jsp/login.jsp") ;
            }
        }else{
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
