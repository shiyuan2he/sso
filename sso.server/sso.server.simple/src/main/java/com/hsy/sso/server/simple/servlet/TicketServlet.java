package com.hsy.sso.server.simple.servlet;

import com.hsy.sso.common.cache.JVMCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet(urlPatterns = "/ticket",description = "sso验票大厅")
public class TicketServlet extends HttpServlet{
    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ticket = request.getParameter("ticket");
        _logger.info("【验票大厅】验证通票{}是否有效",ticket);
        String username = JVMCache.TICKET_AND_NAME.get(ticket);
        _logger.info("【验票大厅】此通票{}的所有者{}",ticket,username);
        //  此处可设置通票时间
        //JVMCache.TICKET_AND_NAME.remove(ticket);
        PrintWriter writer = response.getWriter();
        writer.write(username);
    }
}
