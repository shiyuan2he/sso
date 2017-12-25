package com.hsy.sso.server.best.dao;

import com.hsy.java.bean.dto.ResponseBodyBean;
import com.hsy.sso.server.best.dao.impl.RestfulInterfaceImplFallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

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
@Repository
@FeignClient(name = "crm-server",fallback = RestfulInterfaceImplFallback.class)
public interface RestfulInterfaceInvoke {

    @GetMapping("/api/rest/crm/user/v1/login")
    ResponseBodyBean<Boolean> login() ;
}
