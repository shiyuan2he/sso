package com.hsy.sso.client.filter;

import com.hsy.java.bean.dto.ResponseBodyBean;
import com.hsy.java.bean.vo.SessionBean;
import com.hsy.java.enums.SsoEnum;
import com.hsy.java.httpclient.utils.HttpClientUtils;
import com.hsy.java.java.base.string.StringHelper;
import com.hsy.java.util.json.JsonHelper;
import com.hsy.java.util.json.JsonToBeanUtil;
import com.hsy.sso.client.config.SsoClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
@WebFilter(urlPatterns = "/*",description = "sso客户端拦截器")
public class SSOClientFilter implements Filter {
    private static final Logger _logger = LoggerFactory.getLogger(SSOClientFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    /**
     * @description <p>
     *     1、静态资源文件放行
     *     2、从本服务session中获取用户信息
     *     3、从cookie中获取通票
     *     4、用验证通票跟此人匹配关系
     * </p>
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
         _logger.info("【检票处】进入到sso-client系统拦截器");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1、静态资源文件,视图请求跳转放行
        if(request.getRequestURL().toString().contains("resources")||request.getRequestURL().toString().contains("view")){
            _logger.info("【检票处】此地址放行：{}",request.getRequestURL().toString());
            filterChain.doFilter(request, response);
        }else{
            _logger.info("【检票处】此地址拦截：{}",request.getRequestURL().toString());
            // 2、从本服务session中获取用户信息==单票（局部会话）
            SessionBean sessionBean = (SessionBean) request.getSession().getAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode()) ;

            // 客户端验证未登录
            if(null==sessionBean){
                // 获取授权令牌（ticket），验证服务端是否登陆
                Cookie[] cookies = request.getCookies();
                String ticket = "";
                if(null==cookies||cookies.length<=0){
                    for (Cookie cookie : cookies) {
                        _logger.info("【检票处】cookie信息：name={},value={}", cookie.getName(), cookie.getValue());
                        if (SsoEnum.SSO_KEY_TICKET_COOKIE.getCode().equals(cookie.getName())) {
                            _logger.info("【检票处】获取到授权令牌：ticket={}", cookie.getName(), cookie.getValue());
                            ticket = cookie.getValue();
                            break ;
                        }
                    }
                }

                if(StringHelper.isNotNullOrEmpty(ticket)){
                    // 已登录，有授权令牌==》验证授权令牌
                    _logger.info("【检票处】此人有通票{}",ticket);
                    // 用通票获取用户信息
                    _logger.info("【检票处】将用户:{}手中通票:{}去sso服务中获取用户信息。",ticket);
                    String url = SsoClientConfig.getSsoServerApiTicket() + ticket ;
                    _logger.info("【检票处】http get url={}",url);
                    try{
                        String sessionInfo = HttpClientUtils.sendHttpGet(url);

                        sessionBean = JsonToBeanUtil.responseJsonToBeanEnhance(sessionInfo,SessionBean.class);
                        // 服务端验证已登录
                        if(null!=sessionBean&&null!=sessionBean.getMobile()) {
                            _logger.info("【检票处】用户:{}手中通票{}跟sso服务验票大厅中的票匹配，允许通过",sessionBean.getMobile(), ticket);
                            request.getSession().setAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode(),sessionBean);
                            filterChain.doFilter(request, response);
                        }else{
                            // 服务端验证未登录
                            request.getRequestDispatcher("/WEB-INF/jsp/sso/login.jsp").forward(request,response);
                        }
                    }catch (Exception e){
                        _logger.error(e.getMessage(),e);
                    }
                }else{
                    // 未登录，无授权令牌==》重新登陆
                    request.getRequestDispatcher("/WEB-INF/jsp/sso/login.jsp").forward(request,response);
                }
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
