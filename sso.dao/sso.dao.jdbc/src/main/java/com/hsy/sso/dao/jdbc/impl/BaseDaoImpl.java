package com.hsy.sso.dao.jdbc.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

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
    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;
    @Autowired
    protected JdbcTemplate jdbcTemplate ;
    /**
     * @description <p>查询list</p>
     * @param
     * @return No such property: code for class: Script1
     * @author heshiyuan
     * @date 20/10/2017 11:27 AM
     * @email shiyuan4work@sina.com
     * @github https://github.com/shiyuan2he.git
     * Copyright (c) 2016 shiyuan4work@sina.com All rights reserved
     */
    public List<Map<String,Object>> selectList(String sql, List<Map<String,Object>> params){
        return null ;
        /*try {
            return JdbcHelper.selectByParams(sql,params);
        } catch (SQLException e) {
            _logger.error("【查询t_sso_user表】执行{}发生异常。",sql);
            throw new DBHandleException(
                    ConstantsEnum.DB_SELECT_SQL_EXCEPTION.getCode(),
                    ConstantsEnum.DB_SELECT_SQL_EXCEPTION.getMsg()) ;
        }*/
    }
}
