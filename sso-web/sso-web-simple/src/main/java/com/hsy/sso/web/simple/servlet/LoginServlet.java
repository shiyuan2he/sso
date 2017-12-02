package com.hsy.sso.web.simple.servlet;
import com.hsy.java.enums.ConstantEnum;
import com.hsy.java.java.base.utils.RandomHelper;
import com.hsy.sso.service.api.ITSsoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author heshiyuan
 * @description <p>sso登陆-购票大厅</p>
 * @path sso/com.hsy.sso.server.simple.servlet
 * @date 18/10/2017 5:11 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@WebServlet(urlPatterns = "/login.do",description = "sso登陆")
public class LoginServlet extends HttpServlet{
    private Logger _logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ITSsoUserService itSsoUserService ;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        _logger.info("【sso登陆-购票大厅】进入sso购票大厅。。。");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String backUrl = request.getParameter("backUrl");
        itSsoUserService.login(Long.parseLong(mobile),password) ;
        if (true) {
            _logger.info("【sso登陆-购票大厅】{}登陆成功，满足购票条件",mobile);
            Cookie cookie = new Cookie(ConstantEnum.SESSION_KEY.getCode(), mobile);
            cookie.setPath("/");
            response.addCookie(cookie);

            // 也叫tokenId
            String ticket = mobile + RandomHelper.IDGenerateValue(System.currentTimeMillis());
            _logger.info("【sso登陆-购票大厅】{}购票成功,通票是{}",mobile,ticket);
            // 此处username放到redis中
            //JVMCache.TICKET_AND_NAME.put(ticket, username);
            _logger.info("【sso登陆-购票大厅】将key={},value={}存在缓存当中",mobile);

            if (null != backUrl) {// 实现设置好回调地址
                StringBuilder url = new StringBuilder();
                if (backUrl.endsWith("/")) backUrl = backUrl.substring(0,backUrl.length()-1);
                url.append(backUrl);
                if (0 <= backUrl.indexOf("?")) {
                    url.append("&");
                } else {
                    url.append("?");
                }
                url.append("ticket=").append(ticket);
                _logger.info("【sso登陆-购票大厅】登陆，购票成功，即将重定向到毁掉地址：{}",backUrl);
                // 重定向到回调地址
                response.sendRedirect(url.toString());
            } else {
                //request.getContextPath()
                _logger.info("【sso登陆-购票大厅】登陆，购票成功,但没有回到地址，即将重定向到登陆页面：{}",backUrl);
                response.sendRedirect("/sso/login.jsp");
            }
        } else {
            _logger.info("【sso登陆-购票大厅】登陆失败，返回登陆页面重新登陆");
            response.sendRedirect("/sso/login.jsp?backUrl=" + backUrl);
        }
    }
}
