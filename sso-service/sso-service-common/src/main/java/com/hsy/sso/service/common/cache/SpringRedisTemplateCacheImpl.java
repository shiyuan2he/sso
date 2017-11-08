package com.hsy.sso.service.common.cache;
import com.hsy.java.util.cache.redis.impl.AbstractSpringRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.strong.cache
 * @date 2017/11/1 13:28
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Service("springRedisTemplateCache")
public class SpringRedisTemplateCacheImpl extends AbstractSpringRedisCache{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    @Override
    public RedisTemplate<String, ?> getRedisTemplate() {
        return redisTemplate;
    }
}
