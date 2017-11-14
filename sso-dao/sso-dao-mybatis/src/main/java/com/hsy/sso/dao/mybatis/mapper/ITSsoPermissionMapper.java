package com.hsy.sso.dao.mybatis.mapper;

import com.hsy.sso.base.entity.sso.TSsoPermission;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.dao.mybatis.mapper
 * @date 2017/11/14 10:23
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public interface ITSsoPermissionMapper {
    /**
     * @description <p>查询所有权限</p>
     * @return 所有权限
     * @author heshiyuan
     * @date 2017/11/14 10:50
     */
    List<TSsoPermission> getAll();
    /**
     * @description <p>查询某个用户的所有权限</p>
     * @param userId
     * @return 用户的所有权限
     * @author heshiyuan
     * @date 2017/11/14 10:50
     */
    List<TSsoPermission> getAllOfUser(long userId);
}
