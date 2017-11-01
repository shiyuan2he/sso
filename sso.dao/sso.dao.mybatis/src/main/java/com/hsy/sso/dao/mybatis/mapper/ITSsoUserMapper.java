package com.hsy.sso.dao.mybatis.mapper;

import com.hsy.sso.base.entity.sso.TSsoUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.dao.mybatis.mapper
 * @date 2017/11/1 14:17
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public interface ITSsoUserMapper {
    TSsoUser selectUser(@Param(value="mobile") Long mobile,@Param(value = "password") String password) ;
}
