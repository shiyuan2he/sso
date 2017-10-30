package com.hsy.sso.web.better.web;
import com.hsy.bean.dto.ResponseBodyBean;
import com.hsy.bean.vo.SessionBean;
import com.hsy.bean.web.BaseController;
import com.hsy.java.base.string.StringHelper;
import com.hsy.java.util.cache.jvm.TicketCache;
import com.hsy.sso.base.common.enums.SsoEnum;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import com.hsy.java.util.validation.ParamValidation;
import com.hsy.sso.service.common.ITSsoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Controller
@RequestMapping("/sso")
public class LoginController extends BaseController{

    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    ITSsoUserService itSsoUserService ;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBodyBean<Object> login(@RequestParam(value = "username") String username,
                      @RequestParam(value = "password") String password,
                      @RequestParam(value = "callback") String callback,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        _logger.info("【sso登陆-购票大厅】进入sso购票大厅。。。");

        ParamValidation.notNullValid(username, password); // 参数非空校验

        SessionBean sessionBean = itSsoUserService.login(username, password) ;
        //将用户信息放进session中
        request.getSession().setAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode(),sessionBean);

        if (null != sessionBean) {
            _logger.info("【sso登陆-购票大厅】{}登陆成功，满足购票条件", username);
            // 将通票放到顶层域中
            Cookie cookie = new Cookie(SsoEnum.SSO_KEY_TICKET_COOKIE.getCode(), sessionBean.getTicket());
            cookie.setPath("/");
            response.addCookie(cookie);

            /**
             * @description <p>非jsonp返回</p>
             * @author heshiyuan
             * @date 2017/10/29 13:53
             */
            if(StringHelper.isNullOrEmpty(callback)){
                _logger.info("【sso登陆-购票大厅】方式登陆，购票成功");
                return success();
            }
            _logger.info("【sso登陆-购票大厅】jsonp方式登陆，购票成功");
            // jsonp返回
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(success()) ;
            mappingJacksonValue.setJsonpFunction(callback);
            return success(mappingJacksonValue) ;
        } else {
            _logger.info("【sso登陆-购票大厅】登陆失败，请重新登陆");
            return failure();
        }
    }
}
