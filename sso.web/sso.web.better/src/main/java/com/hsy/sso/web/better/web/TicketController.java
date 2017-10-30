package com.hsy.sso.web.better.web;

import com.hsy.bean.dto.ResponseBodyBean;
import com.hsy.bean.vo.SessionBean;
import com.hsy.bean.web.BaseController;
import com.hsy.java.util.cache.jvm.TicketCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Controller
@RequestMapping("/ticket")
public class TicketController extends BaseController{

    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;

    @RequestMapping(value = "/{ticket}",produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBodyBean<Object> getTicket(@PathVariable(value = "ticket") String ticket) throws IOException {
        _logger.info("【验票大厅】欢迎进入验票大厅");
        _logger.info("【验票大厅】验证通票{}是否有效",ticket);
        SessionBean sessionBean = TicketCache.get(ticket) ;
        _logger.info("【验票大厅】此通票{}的所有者{},通票有效",ticket,sessionBean.getUserName());
        return success(sessionBean);
    }
}
