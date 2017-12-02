package com.hsy.sso.web.better.web;
import com.hsy.java.bean.dto.ResponseBodyBean;
import com.hsy.java.bean.vo.SessionBean;
import com.hsy.java.bean.web.BaseController;
import com.hsy.sso.base.common.constants.CommonConstant;
import com.hsy.sso.dao.redis.cache.SpringRedisTemplateCache;
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
@RestController
@RequestMapping("/sso/ticket")
public class TicketController extends BaseController {

    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    SpringRedisTemplateCache springRedisTemplateCache ;
    @RequestMapping(value = "/{ticket}",produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBodyBean<Object> getTicket(@PathVariable(value = "ticket") long ticket) throws IOException {
        _logger.info("【验票大厅】欢迎进入验票大厅");
        _logger.info("【验票大厅】验证通票{}是否有效",ticket);
        SessionBean sessionBean = (SessionBean) springRedisTemplateCache.getCache(CommonConstant.TICKET_CACHE_KEY+ticket,SessionBean.class);
        if(null!=sessionBean){
            _logger.info("【验票大厅】此通票{}的所有者{},通票有效",ticket,sessionBean.getUserName());
            return success(sessionBean);
        }else{
            return failure();
        }
    }
}
