package com.hsy.sso.strong.service.impl;
import com.hsy.sso.strong.bean.dto.SessionBean;
import com.hsy.sso.strong.bean.entity.TSsoUser;
import com.hsy.sso.strong.dao.ITSsoUserDao;
import com.hsy.sso.strong.enums.BusinessEnum;
import com.hsy.sso.strong.enums.ConstantEnum;
import com.hsy.sso.strong.exception.BusinessException;
import com.hsy.sso.strong.service.ITSsoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * @author heshiyuan
 * @description <p>sso登陆业务处理</p>
 * @path sso/com.hsy.sso.service.jdbc
 * @date 20/10/2017 1:17 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Service("ssoUserService")
public class TSsoUserServiceImpl implements ITSsoUserService {
    private Logger _logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ITSsoUserDao ssoUserDao ;

    //@Autowired
    //SpringRedisTemplateCache springRedisTemplate ;

    @Transactional
    @Override
    public SessionBean login(Long mobile, String password){
        String ticket = "";
        try{
            TSsoUser ssoUser = ssoUserDao.selectUser(mobile,password);
            if(null!=ssoUser){
                // 也叫tokenId 生成一张通票，并将这张票保存在缓存当中
                ticket = mobile + System.currentTimeMillis() + "";
                SessionBean sessionBean = new SessionBean() ;
                sessionBean.setMobile(mobile);
                sessionBean.setTicket(ticket);
                _logger.info("【sso登陆-购票大厅】{}购票成功,通票是{}",mobile,ticket);
                // 此处username跟通票映射放进jvm缓存,过期时间30分钟
                //springRedisTemplate.putCacheWithExpireTime(ticket,sessionBean,1000 * 60) ;
                _logger.info("【sso登陆-购票大厅】将key={},value={}存在缓存当中",mobile);
                return sessionBean ;
            }
        }catch(Exception e){
            // 登陆失败，清除此人票务信息
            if(null!=ticket&&!"".equals(ticket)){
                //springRedisTemplate.deleteCacheByKey(ticket);
            }
            throw new BusinessException(BusinessEnum.LOGIN_EXCEPTION) ;
        }
        return null ;
    }

    @Override
    public boolean reg(Long mobile, String password) {
        TSsoUser ssoUser = new TSsoUser() ;
        String id = System.currentTimeMillis()+"" ;
        ssoUser.setId(Long.parseLong(id));
        ssoUser.setMobile(mobile);
        ssoUser.setPassword(password);
        ssoUser.setPasswordEncryptionType(ConstantEnum.ENCRYPTION_TYPE_BASE64.getCode());
        ssoUser.setInsertTime(Calendar.getInstance().getTime());
        ssoUser.setInserter(Long.parseLong(id));
        if(ssoUserDao.insertUser(ssoUser)==1){
            return true ;
        }
        return false;
    }
}
