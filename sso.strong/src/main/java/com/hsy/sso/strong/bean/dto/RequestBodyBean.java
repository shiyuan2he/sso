package com.hsy.sso.strong.bean.dto;
/**
 * @author heshiyuan
 * @description <p>请求统一bean</p>
 * @path framework/com.hsy.dto
 * @date 18/08/2017 11:56 AM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class RequestBodyBean<T> {
    private String key ;//秘钥
    private String requestTime ;// 请求时间
    private T param ;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
