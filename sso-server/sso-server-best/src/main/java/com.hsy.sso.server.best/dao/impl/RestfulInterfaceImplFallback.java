package com.hsy.sso.server.best.dao.impl;

import com.hsy.java.bean.dto.ResponseBodyBean;
import com.hsy.java.bean.vo.UserInfoBean;
import com.hsy.java.enums.SsoEnum;
import com.hsy.sso.server.best.dao.RestfulInterfaceInvoke;
import org.springframework.stereotype.Component;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.server.best.service.impl
 * @date 2017/12/25 17:15
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Component
public class RestfulInterfaceImplFallback implements RestfulInterfaceInvoke {
    @Override
    public ResponseBodyBean<UserInfoBean> queryUserInfo(Long id,Long mobile,String username,String password) {
        return new ResponseBodyBean<>(false,
                SsoEnum.SSO_EXCEPTION_QUERY_USERINFO.getCode(),
                SsoEnum.SSO_EXCEPTION_QUERY_USERINFO.getMessage());
    }

    @Override
    public ResponseBodyBean<Boolean> setStringValue(String key, String value) {
        return null;
    }

    @Override
    public ResponseBodyBean<Object> getStringValue(String key) {
        return null;
    }
}
