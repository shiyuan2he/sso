package com.hsy.sso.strong.dao.impl;
import com.hsy.sso.strong.bean.entity.TSsoUser;
import com.hsy.sso.strong.dao.ITSsoUserDao;
import com.hsy.sso.strong.enums.DBEnum;
import com.hsy.sso.strong.exception.DBHandleException;
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
public class TSsoUserDaoImpl extends BaseDaoImpl implements ITSsoUserDao {


    @Override
    public TSsoUser selectUser(Long mobile, String password) {
        List<TSsoUser> userList = this.jdbcTemplate.query("select * from t_sso_user where mobile = ? and password = ?",
            new Object[]{mobile,password},new BeanPropertyRowMapper(TSsoUser.class));
        if(null==userList||userList.size()<0) throw new DBHandleException(DBEnum.DB_SELECT_IS_NULL);
        return userList.get(0) ;
    }

    @Override
    public Integer insertUser(TSsoUser user) {
        Integer insertCount = 0 ;
        try{
            insertCount = this.jdbcTemplate.update("insert ignore into t_sso_user(id,mobile,password,password_encryption_type) " +
                            "values(?,?,?,?)",
                    new Object[]{user.getId(),user.getMobile(),user.getPassword(),user.getPasswordEncryptionType()});
        }catch (Exception e){
            throw new DBHandleException(DBEnum.DB_INSERT_RESULT_ERROR) ;
        }
        return insertCount ;
    }
}
