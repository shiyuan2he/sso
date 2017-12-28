package com.hsy.sso.server.best.dao;

import com.hsy.java.bean.dto.ResponseBodyBean;
import com.hsy.java.bean.vo.UserInfoBean;
import com.hsy.sso.server.best.dao.impl.CrmInterfaceImplFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.server.best.service
 * @date 2017/12/25 17:23
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@FeignClient(name = "crm-server")
public interface CrmInterfaceInvoke {
    @GetMapping("/api/rest/crm/user/v1/query")
    ResponseBodyBean<UserInfoBean> queryUserInfo(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "String") Long mobile,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) ;
}
