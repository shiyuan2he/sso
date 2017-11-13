package com.hsy.sso.service.common.impl;

import com.hsy.sso.service.api.ITSsoUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.service.jdbc.impl
 * @date 2017/10/27 09:54
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TSsoUserServiceImplTest {
    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    ITSsoUserService ssoUserService ;

    @Test
    public void login() throws Exception {
        _logger.info("login={}",ssoUserService.login(11111l,"123456"));
    }

    @Test
    public void reg() throws Exception {
        _logger.info("reg={}",ssoUserService.reg(11111l,"123456"));
    }

}