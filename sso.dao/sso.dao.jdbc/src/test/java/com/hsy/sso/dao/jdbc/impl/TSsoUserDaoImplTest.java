package com.hsy.sso.dao.jdbc.impl;

import com.hsy.sso.base.entity.sso.TSsoUser;
import com.hsy.sso.dao.jdbc.ITSsoUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Calendar;
/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.dao.jdbc.impl
 * @date 2017/10/26 16:41
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TSsoUserDaoImplTest {
    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;
    @Autowired
    ITSsoUserDao ssoUserDao ;

    @Test
    public void insertUser() throws Exception {
        TSsoUser user = new TSsoUser() ;
        user.setId(24234234124443l);
        user.setInserter(24234234124443l);
        user.setInsertTime(Calendar.getInstance().getTime());
        user.setUserName("aa1234");
        user.setPassword("123456");
        user.setPasswordEncryptionType("BASE64");
        _logger.info("insertUser={}",ssoUserDao.insertUser(user)) ;

    }

    @Test
    public void selectUser() throws Exception {
        _logger.info("selectUser={}",ssoUserDao.selectUser("aa1234","123456").toString()) ;
    }

}