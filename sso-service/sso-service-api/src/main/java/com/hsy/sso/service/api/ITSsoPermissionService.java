package com.hsy.sso.service.api;

import com.hsy.bean.vo.PermissionBean;
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

    List<PermissionBean> getAllPermisstion() ;

    List<PermissionBean> getAllPermissionByUserId(Long userId) ;

    List<TSsoPermission> getAll(Integer offset,Integer limit) ;

    boolean insert(String authAddress,String authDescription,Long parentid,Long userId) ;

    boolean update(Long id,String authAddress,String authDescription,Long parentid,Long userId) ;

    boolean delete(Long id);
}
