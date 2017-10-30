package com.hsy.sso.dao.jdbc.impl;
import com.hsy.java.enums.DBEnum;
import com.hsy.java.exception.dao.DBExecuteException;
import com.hsy.sso.base.entity.sso.TSsoUser;
import com.hsy.sso.dao.jdbc.ITSsoUserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
@Repository(value = "ssoUserDao")
public class TSsoUserDaoImpl extends BaseDaoImpl implements ITSsoUserDao{


    @Override
    public TSsoUser selectUser(String username, String password) {
        List<TSsoUser> userList = this.jdbcTemplate.query("select * from t_sso_user where user_name = ? and password = ?",
            new Object[]{username,password},new BeanPropertyRowMapper(TSsoUser.class));
        if(null==userList||userList.size()<0) throw new DBExecuteException(DBEnum.DB_SELECT_IS_NULL);
        return userList.get(0) ;
    }

    @Override
    public int insertUser(TSsoUser user) {
        int insertCount = 0 ;
        try{
            insertCount = this.jdbcTemplate.update("insert ignore into t_sso_user(id,user_name,password,password_encryption_type) " +
                            "values(?,?,?,?)",
                    new Object[]{user.getId(),user.getUserName(),user.getPassword(),user.getPasswordEncryptionType()});
        }catch (Exception e){
            throw new DBExecuteException(DBEnum.DB_INSERT_RESULT_ERROR) ;
        }
        return insertCount ;
    }
}
