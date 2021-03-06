package com.hsy.sso.server.best.web;

import com.hsy.java.bean.dto.ResponseBodyBean;
import com.hsy.java.bean.vo.SessionBean;
import com.hsy.java.bean.web.BaseController;
import com.hsy.java.enums.CacheEnum;
import com.hsy.java.enums.CookieEnum;
import com.hsy.java.enums.SsoEnum;
import com.hsy.java.java.base.string.StringHelper;
import com.hsy.java.util.http.RequestHelper;
import com.hsy.sso.server.best.dao.CrmInterfaceInvoke;
import com.hsy.sso.server.best.dao.RedisInterfaceInvoke;
import com.hsy.sso.server.best.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "sso登陆接口",description = "sso登陆，注册，退出等服务")
@RestController
@RequestMapping("/api/rest/sso")
public class SsoController extends BaseController {
    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    IUserService userService ;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    CrmInterfaceInvoke crmInterfaceInvoke ;
    @Autowired
    RedisInterfaceInvoke redisInterfaceInvoke ;

    @ApiOperation(value = "登陆",notes = "sso登陆服务")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "mobile", value = "用户手机号", dataType = "Long"),
        @ApiImplicitParam(name = "userName", value = "用户名称", dataType = "String"),
        @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String"),
        @ApiImplicitParam(name = "imageCode", value = "图形验证码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/v1/login",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBodyBean<Object> login(@RequestParam(value = "mobile",required = false) long mobile,
                                          @RequestParam(value = "userName",required = false) String userName,
                                          @RequestParam(value = "password",required = false) String password,
                                          @RequestParam(value = "imageCode") String imageCode,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        _logger.info("【sso登陆-购票大厅】进入sso购票大厅。。。");

        // 1.校验图形验证码
        imageCode = imageCode.toUpperCase() ;
        ResponseBodyBean<String> data = redisInterfaceInvoke.getStringValue(CacheEnum.CACHE_KEY_IMAGE_CODE.getCode() + imageCode);
        String redisImageCode = data.getData() ;
        if(StringHelper.isNullOrEmpty(redisImageCode)){
            return failure("SSO9999","验证码不正确,或者60秒超时");
        }

        // 2.登陆
        SessionBean sessionBean = userService.login(mobile, userName,password) ;

        // 3.写入cookie
        if (null != sessionBean) {
            _logger.info("【sso登陆-购票大厅】{}登陆成功", mobile);
            // 将通票放到顶层域中
            Cookie cookie = new Cookie(SsoEnum.SSO_KEY_TICKET_COOKIE.getCode(), sessionBean.getTicket());
            cookie.setDomain(RequestHelper.getDomainFromRequest(request)==null?".sso.com":RequestHelper.getDomainFromRequest(request));
            cookie.setPath(CookieEnum.COOKIE_INFO.getPath());
            cookie.setMaxAge(CookieEnum.COOKIE_INFO.getExpire());
            cookie.setComment("登陆凭证");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            _logger.info("【sso登陆-购票大厅】购票成功");
            return success();
        } else {
            _logger.info("【sso登陆-购票大厅】登陆失败，请重新登陆");
            return failure();
        }
    }
    @RequestMapping(value = "/v1/logout",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
                cookie.setPath(CookieEnum.COOKIE_INFO.getPath());
                cookie.setDomain("");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        // 清除session
        if(null!=request.getSession().getAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode())){
            request.getSession().setAttribute(SsoEnum.SSO_KEY_USER_SESSION.getCode(),null);
        }

        // 清除redis缓存
        userService.logout(ticket);
        return success() ;
    }
}
