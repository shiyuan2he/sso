package com.hsy.sso.web.better.exception;

import com.alibaba.fastjson.JSON;
import com.hsy.java.bean.dto.ResponseBodyBean;
import com.hsy.java.exception.service.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.web.better.exception
 * @date 2017/11/14 16:30
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Component
public class GlobalHandleException implements HandlerExceptionResolver{
    private final Logger _logger = LoggerFactory.getLogger(GlobalHandleException.class) ;
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //返回json格式的错误信息
        try {
            httpServletResponse.setContentType("text/html;charset=UTF-8");
            httpServletResponse.setCharacterEncoding("utf-8");
            PrintWriter writer = httpServletResponse.getWriter();
            String responseStr = JSON.toJSONString(new ResponseBodyBean<>(false,((BusinessException) e).getCode(),e.getMessage())) ;
            _logger.info("全局异常处理返回信息：{}",responseStr);
            writer.write(responseStr);
            writer.flush();
        } catch (Exception ex) {
            _logger.error("全局处理异常失败,message:{}:",ex.getMessage());
        }
        return null;
    }
}
