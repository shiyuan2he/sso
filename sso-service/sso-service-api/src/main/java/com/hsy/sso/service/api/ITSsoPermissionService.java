package com.hsy.sso.service.api;

import com.hsy.java.bean.vo.PermissionBean;
import com.hsy.sso.base.entity.sso.TSsoPermission;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.service.api
 * @date 2017/11/14 12:56
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public interface ITSsoPermissionService {

    List<PermissionBean> getAllPermisstion() throws Exception;

    List<PermissionBean> getAllPermissionByUserId(Long userId) throws Exception;

    List<TSsoPermission> getAll(Integer offset,Integer limit) throws Exception;

    boolean insert(String authAddress,String authDescription,Long parentid,Long userId) throws Exception;

    boolean update(Long id,String authAddress,String authDescription,Long parentid,Long userId) throws Exception;

    boolean delete(Long id) throws Exception;
}
