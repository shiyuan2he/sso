package com.hsy.sso.strong.util;

import com.hsy.sso.strong.exception.ParamValidationException;
import org.hibernate.validator.internal.util.StringHelper;

/**
 * @author heshiyuan
 * @description <p>参数校验公共方法</p>
 * @path java/com.hsy.java.base.validation
 * @date 2017/10/27 14:05
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class ParamValidation {

    /**
     * @description <p>非空校验</p>
     * @param   args 数组参数
     * @return  校验结果
     * @author heshiyuan
     * @date 2017/10/27 14:11
     */
    public static void notNullValid(Object ... args){
        for(Object arg : args){
            if(arg instanceof String){
                if(null==arg){
                    throw new ParamValidationException("参数：【"+ arg + "】不能为空！");
                }
            }
        }
    }
}
