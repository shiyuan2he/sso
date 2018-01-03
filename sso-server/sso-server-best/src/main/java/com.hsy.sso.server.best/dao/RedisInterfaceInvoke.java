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

    @PostMapping("/api/rest/redis/string/v1/set")
    ResponseBodyBean<Boolean> setStringValue(
            @RequestParam(value = "key") String key,
            @RequestParam(value = "value") String value,
            @RequestParam(value = "expire") Long expire
    );

    @GetMapping("/api/rest/redis/string/v1/get")
    ResponseBodyBean<String> getStringValue(@RequestParam(value = "key") String key);

    @GetMapping("/api/rest/redis/v1/delete")
    ResponseBodyBean<Object> delete(@RequestParam(value = "key") String key);
}
