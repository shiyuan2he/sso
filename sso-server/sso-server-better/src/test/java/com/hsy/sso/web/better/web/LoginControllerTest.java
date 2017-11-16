package com.hsy.sso.web.better.web;

import com.hsy.sso.web.better.SpringJunitBase;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.web.better.web
 * @date 2017/11/14 20:04
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class LoginControllerTest extends SpringJunitBase {
    MultiValueMap<String,Object> paramsMap = new LinkedMultiValueMap<>();
    @Test
    public void getAllPermission() throws Exception {
        paramsMap.clear();
        this.getRequest("/api/sso/auth/v1.0/role/list",paramsMap);
    }

    @Test
    public void getAllPermissionByUserId() throws Exception {
    }
    @Test
    public void getAllRole() throws Exception{
        paramsMap.clear();
        this.getRequest("/api/sso/auth/v1.0/role/list",paramsMap);
    }
}