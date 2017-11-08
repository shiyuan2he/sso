package com.hsy.sso.strong.web;

import com.hsy.sso.strong.bean.dto.ResponseBodyBean;
import com.hsy.sso.strong.bean.dto.SessionBean;
import com.hsy.sso.strong.enums.SsoEnum;
import com.hsy.sso.strong.service.ITSsoUserService;
import com.hsy.sso.strong.util.ParamValidation;
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
public class LoginController extends BaseController{
    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;
    @Autowired
    ITSsoUserService itSsoUserService ;

    @RequestMapping(value = "/login",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseBodyBean<Object> login(@RequestParam(value = "mobile") long mobile,
                                          @RequestParam(value = "password") String password,
                                          @RequestParam(value = "callback",required = false) String callback,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        _logger.info("【sso登陆-购票大厅】进入sso购票大厅。。。");

        ParamValidation.notNullValid(mobile, password); // 参数非空校验

        SessionBean sessionBean = itSsoUserService.login(mobile, password) ;
        //将用户信息放进session中
        request.getSession().setAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode(),sessionBean);

        if (null != sessionBean) {
            _logger.info("【sso登陆-购票大厅】{}登陆成功，满足购票条件", mobile);
            // 将通票放到顶层域中
            Cookie cookie = new Cookie(SsoEnum.SSO_KEY_TICKET_COOKIE.getCode(), sessionBean.getTicket());
            cookie.setPath("/");
            response.addCookie(cookie);

            _logger.info("【sso登陆-购票大厅】方式登陆，购票成功");
            return success();
        } else {
            _logger.info("【sso登陆-购票大厅】登陆失败，请重新登陆");
            return failure();
        }
    }
}
