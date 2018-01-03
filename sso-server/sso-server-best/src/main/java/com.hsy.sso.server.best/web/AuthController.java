package com.hsy.sso.server.best.web;
import com.hsy.java.bean.dto.ResponseBodyBean;
import com.hsy.java.bean.web.BaseController;
import com.hsy.java.enums.CacheEnum;
import com.hsy.sso.server.best.dao.RedisInterfaceInvoke;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.web.better.web
 * @date 2017/10/27 10:40
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Api(value = "sso校验服务",description = "提供校验授权令牌，通票，登陆状态等接口")
@RestController
@RequestMapping("/apr/rest/sso/auth")
public class AuthController extends BaseController {

    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    RedisInterfaceInvoke redisInterfaceInvoke ;

    @ApiOperation(value = "校验授权令牌，校验通票",notes = "sso校验服务")
    @ApiImplicitParam(name = "ticket", value = "授权令牌或者说通票",required = true,dataType = "Long",paramType = "path")
    @RequestMapping(value = "/v1/{ticket}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBodyBean<String> getTicket(@PathVariable(value = "ticket") Long ticket) throws IOException {
        _logger.info("【验票大厅】欢迎进入验票大厅");
        _logger.info("【验票大厅】验证通票{}是否有效",ticket);
        ResponseBodyBean<String> reponseBean = redisInterfaceInvoke.getStringValue(CacheEnum.CACHE_KEY_TICKET.getCode() + ticket) ;

        if(reponseBean.isSuccess()){
            _logger.info("【验票大厅】此通票{}的所有者{},通票有效",ticket,reponseBean.getData());
            return reponseBean;
        }else{
            return failure();
        }
    }
}
