package com.hsy.sso.strong.exception;
import com.hsy.sso.strong.enums.CacheEnum;

/**
 * @author heshiyuan
 * @description <p>缓存抛出时异常</p>
 * @path java/com.hsy.java.base.exception
 * @date 25/09/2017 3:15 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class CacheException extends RuntimeException{
    private String code ;
    private String msg ;
    public CacheException(String msg) {
        super(msg);
    }
    public CacheException(CacheEnum cacheEnum) {
        super(cacheEnum.getMessage());
        this.code = cacheEnum.getCode() ;
    }
    public CacheException(String code , String msg) {
        super(msg);
        this.code = code ;
    }

    public CacheException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
