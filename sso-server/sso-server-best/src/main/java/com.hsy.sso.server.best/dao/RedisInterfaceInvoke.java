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
@FeignClient(name = "base-service-redis")
public interface RedisInterfaceInvoke {

    @PostMapping("/api/rest/redis/v1/set")
    ResponseBodyBean<Boolean> setStringValue(
            @RequestParam(value = "key") String key,
            @RequestParam(value = "value") String value);

    @GetMapping("/api/rest/redis/v1/get")
    ResponseBodyBean<Object> getStringValue(
            @RequestParam(value = "key") String key);
}
