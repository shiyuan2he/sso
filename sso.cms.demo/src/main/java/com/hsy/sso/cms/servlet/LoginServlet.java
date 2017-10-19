package com.hsy.sso.cms.servlet;

import com.hsy.java.httpclient.utils.HttpClientUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.blog.servlet
 * @date 19/10/2017 9:48 AM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@WebServlet(urlPatterns = "/login",description = "crm系统登陆")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String serviceUrl = request.getParameter("serviceUrl");


        if ("admin".equals(username) && "123".equals(password)) {
            Cookie cookie = new Cookie("sso", username);
            cookie.setPath("/");
            response.addCookie(cookie);

            long time = System.currentTimeMillis();
            String timeString = username + time;
            // 此处username放到redis中
            //JVMCache.TICKET_AND_NAME.put(timeString, username);

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
