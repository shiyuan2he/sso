package com.hsy.sso.web.better.web;

import com.hsy.bean.dto.ResponseBodyBean;
import com.hsy.bean.web.BaseController;
import com.hsy.sso.service.api.ITSsoPermissionService;
import com.hsy.sso.service.api.ITSsoRoleService;
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
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
@RequestMapping(value = "/api/sso/auth")
public class AuthController extends BaseController{

    @Autowired private ITSsoPermissionService ssoPermissionService ;
    @Autowired private ITSsoRoleService ssoRoleService ;

    @RequestMapping(value = {"/v1.0/permissions/list"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> getAllPermission() throws Exception{
        return success(ssoPermissionService.getAllPermisstion()) ;
    }

    @RequestMapping(value = {"/v2.0/permissions/list","/v2.0/permission/list/{offset}/{limit}"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> getAllPermission(@PathVariable(value = "offset",required = false) Integer offset,
                                                     @PathVariable(value = "limit",required = false) Integer limit) throws Exception{
        return success(ssoPermissionService.getAll(offset,limit)) ;
    }

    @RequestMapping(value = "/v1.0/permission/list/{userId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> getAllPermissionByUserId(@PathVariable(value = "userId") Long userId) throws Exception{
        return success(ssoPermissionService.getAllPermissionByUserId(userId));
    }
    @RequestMapping(value = "/v1.0/permission/add",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> addPermission(String authAddress,String authDescription,Long parentId,Long userId) throws Exception{
        return success(ssoPermissionService.insert(authAddress,authDescription,parentId,userId)) ;
    }
    @RequestMapping(value = "/v1.0/permission/update/{id}",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> updatePermission(@PathVariable(value = "id") Long id,
                                                     String authAddress,
                                                     String authDescription,
                                                     Long parentId,
                                                     Long userId) throws Exception{
        return success(ssoPermissionService.update(id,authAddress,authDescription,parentId,userId));
    }
    @RequestMapping(value = "/v1.0/permission/delete/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> deletePermission(@PathVariable(value = "id") Long id) throws Exception{
        return success(ssoPermissionService.delete(id));
    }

    @RequestMapping(value = {"/v1.0/role/list","/v1.0/role/list/{offset}/{limit}"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> getAllRole(
            @PathVariable(value = "offset",required = false) Integer offset,
            @PathVariable(value = "limit",required = false) Integer limit){
        return success(ssoRoleService.getAll(offset,limit)) ;
    }
    @RequestMapping(value = {"/v1.0/role/add"},
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> addRole(String roleName,String roleDescription,Long userId){
        return success(ssoRoleService.insert(roleName,roleDescription,userId));
    }
    @RequestMapping(value = {"/v1.0/role/update/{id}"},
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> updateRole(@PathVariable Long id,String roleName,String roleDescription,Long userId){
        return success(ssoRoleService.update(id,roleName,roleDescription,userId));
    }
    @RequestMapping(value = {"/v1.0/role/delete/{id}"},
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBodyBean<Object> deleteRole(@PathVariable Long id){
        return success(ssoRoleService.delete(id));
    }

}
