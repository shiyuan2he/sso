package com.hsy.sso.web.better.web;

import com.hsy.bean.dto.ResponseBodyBean;
import com.hsy.bean.vo.SessionBean;
import com.hsy.bean.web.BaseController;
import com.hsy.java.enums.SsoEnum;
import com.hsy.java.util.validation.ParamValidation;
import com.hsy.sso.service.better.ITSsoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.web.better.web
 * @date 2017/10/27 10:39
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@RestController
@RequestMapping("/sso")
public class LoginController extends BaseController {
    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;
    @Autowired
    ITSsoUserService ssoUserService ;

    @RequestMapping(value = "/login",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseBodyBean<Object> login(@RequestParam(value = "mobile") long mobile,
                                          @RequestParam(value = "password") String password,
                                          @RequestParam(value = "callback",required = false) String callback,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        _logger.info("【sso登陆-购票大厅】进入sso购票大厅。。。");

        ParamValidation.notNullValid(mobile, password); // 参数非空校验

        SessionBean sessionBean = ssoUserService.login(mobile, password) ;
        //将用户信息放进session中,暂无用处
        request.getSession().setAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode(),sessionBean);

        if (null != sessionBean) {
            _logger.info("【sso登陆-购票大厅】{}登陆成功，满足购票条件", mobile);
            // 将通票放到顶层域中
            Cookie cookie = new Cookie(SsoEnum.SSO_KEY_TICKET_COOKIE.getCode(), sessionBean.getTicket());
            cookie.setPath("/");
            cookie.setMaxAge(120); // 两分钟
            response.addCookie(cookie);

            _logger.info("【sso登陆-购票大厅】方式登陆，购票成功");
            return success();
        } else {
            _logger.info("【sso登陆-购票大厅】登陆失败，请重新登陆");
            return failure();
        }
    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseBodyBean<Object> logout(HttpServletRequest request, HttpServletResponse response){
        // 清除cookie
        Cookie[] cookies = request.getCookies() ;
        String ticket = "" ;
        for(Cookie cookie : cookies){
            if(SsoEnum.SSO_KEY_TICKET_COOKIE.getCode().equals(cookie.getName())){
                ticket = cookie.getValue() ;
                // 删除cookie
                cookie = new Cookie(SsoEnum.SSO_KEY_TICKET_COOKIE.getCode(),null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        // 清除session
        if(null!=request.getSession().getAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode())){
            request.getSession().setAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode(),null);
        }

        // 清除redis缓存
        ssoUserService.logout(ticket);
        return success() ;
    }

    @RequestMapping(value = "/reg",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseBodyBean<Object> reg(@RequestParam(value = "mobile") long mobile,
                                        @RequestParam(value = "password") String password,
                                        @RequestParam(value = "callback",required = false) String callback){

        ParamValidation.notNullValid(mobile, password); // 参数非空校验
        if(ssoUserService.reg(mobile,password)){
            return success() ;
        }else{
            return failure() ;
        }
    }
}
