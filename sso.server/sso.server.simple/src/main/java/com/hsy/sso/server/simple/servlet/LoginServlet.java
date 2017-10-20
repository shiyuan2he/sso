package com.hsy.sso.server.simple.servlet;

import com.hsy.sso.base.common.cache.JVMCache;
import com.hsy.sso.service.jdbc.ITSsoUserService;
import com.hsy.sso.service.jdbc.impl.TSsoUserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.server.simple.servlet
 * @date 18/10/2017 5:11 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@WebServlet(urlPatterns = "/login.do",description = "sso登陆")
public class LoginServlet extends HttpServlet{

    ITSsoUserService itSsoUserService = new TSsoUserServiceImpl() ;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String serviceUrl = request.getParameter("serviceUrl");

        if (itSsoUserService.login(username,password)) {
            Cookie cookie = new Cookie("sso", username);
            cookie.setPath("/");
            response.addCookie(cookie);

            long time = System.currentTimeMillis();
            String timeString = username + time;
            // 此处username放到redis中
            JVMCache.TICKET_AND_NAME.put(timeString, username);

            if (null != serviceUrl) {
                StringBuilder url = new StringBuilder();
                url.append(serviceUrl);
                if (0 <= serviceUrl.indexOf("?")) {
                    url.append("&");
                } else {
                    url.append("?");
                }
                url.append("ticket=").append(timeString);
                response.sendRedirect(url.toString());
            } else {
                response.sendRedirect("/sso/login.jsp");
            }
        } else {
            response.sendRedirect("/sso/login.jsp?serviceUrl=" + serviceUrl);
        }
    }
}
