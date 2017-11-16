package com.hsy.sso.dao.mybatis.mapper;

import com.hsy.sso.base.entity.sso.TSsoRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.dao.mybatis.mapper
 * @date 2017/11/14 10:16
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public interface ITSsoRoleMapper {

    List<TSsoRole> getAll(@Param(value = "offset") Integer offset, @Param(value = "limit") Integer limit) ;

    Integer insert(TSsoRole ssoRole) ;

    Integer update(TSsoRole ssoRole) ;

    Integer delete(Long id) ;
}
