package com.hsy.sso.web.better.web;

import com.hsy.bean.dto.ResponseBodyBean;
import com.hsy.bean.web.BaseController;
import com.hsy.sso.service.api.ITSsoPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.web.better.web
 * @date 2017/11/14 16:07
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController extends BaseController{

    @Autowired private ITSsoPermissionService ssoPermissionService ;
    @RequestMapping(value = "/permission/list",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> getAllPermission(){
        return success(ssoPermissionService.getAllPermisstion()) ;
    }
    @RequestMapping(value = "/permission/list/{userId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> getAllPermissionByUserId(@PathVariable(value = "userId") Long userId){
        return success(ssoPermissionService.getAllPermissionByUserId(userId));
    }
}
