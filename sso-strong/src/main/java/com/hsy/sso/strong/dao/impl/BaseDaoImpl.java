package com.hsy.sso.strong.dao.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.dao.sso.impl
 * @date 20/10/2017 10:34 AM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class BaseDaoImpl{
    @Autowired
    protected JdbcTemplate jdbcTemplate ;
}
