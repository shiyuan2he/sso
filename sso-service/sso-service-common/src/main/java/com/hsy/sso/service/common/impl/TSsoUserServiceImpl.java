package com.hsy.sso.service.common.impl;
import com.hsy.bean.vo.SessionBean;
import com.hsy.java.base.string.StringHelper;
import com.hsy.java.enums.BusinessEnum;
import com.hsy.java.enums.ConstantEnum;
import com.hsy.java.exception.service.BusinessException;
import com.hsy.java.util.secure.Base64Helper;
import com.hsy.sso.base.common.constants.CommonConstant;
import com.hsy.sso.base.entity.sso.TSsoUser;
import com.hsy.sso.dao.mybatis.mapper.ITSsoUserMapper;
import com.hsy.sso.dao.redis.cache.SpringRedisTemplateCache;
import com.hsy.sso.service.api.ITSsoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ITSsoUserMapper itSsoUserMapper ;
    @Autowired
    SpringRedisTemplateCache springRedisTemplateCache ;


    @Override
    public SessionBean login(Long mobile, String password){
        String ticket = "";
        try{
            TSsoUser ssoUser = itSsoUserMapper.selectUser(mobile,Base64Helper.stringToBase64OfCc(password));
            if(null!=ssoUser){
                // 也叫tokenId 生成一张通票，并将这张票保存在缓存当中
                ticket = mobile + System.currentTimeMillis() + "";
                SessionBean sessionBean = new SessionBean() ;
                sessionBean.setMobile(mobile);
                sessionBean.setTicket(ticket);
                _logger.info("【sso登陆-购票大厅】{}购票成功,通票是{}",mobile,ticket);
                springRedisTemplateCache.putCacheWithExpireTime(CommonConstant.TICKET_CACHE_KEY+ticket,sessionBean,2 * 60) ;
                _logger.info("【sso登陆-购票大厅】将key={},value={}存在缓存当中",mobile);
                return sessionBean ;
            }
        }catch(Exception e){
            // 登陆失败，清除此人票务信息
            if(null!=ticket&&!"".equals(ticket)){
                springRedisTemplateCache.deleteCacheByKey(CommonConstant.TICKET_CACHE_KEY+ticket);
            }
            throw new BusinessException(BusinessEnum.LOGIN_EXCEPTION) ;
        }
        return null ;
    }
    @Override
    public boolean reg(Long mobile, String password) {
        TSsoUser ssoUser = new TSsoUser() ;
        String id = StringHelper.generateRandomOfStringByLength(19) ;
        ssoUser.setId(Long.parseLong(id));
        ssoUser.setMobile(mobile);
        ssoUser.setPassword(Base64Helper.stringToBase64OfCc(password));
        ssoUser.setPasswordEncryptionType(ConstantEnum.ENCRYPTION_TYPE_BASE64.getCode());
        ssoUser.setUserName("sso"+System.currentTimeMillis());
        ssoUser.setCreater(Long.parseLong(id));
        ssoUser.setCreateTime(Calendar.getInstance().getTime());
        if(itSsoUserMapper.insertUser(ssoUser)==1){
            return true ;
        }
        return false;
    }

    @Override
    public boolean logout(String ticket) {
        if(StringHelper.isNotNullOrEmpty(ticket)){
            springRedisTemplateCache.deleteCacheByKey(CommonConstant.TICKET_CACHE_KEY+ticket);
            return true ;
        }
        return false;
    }
}
