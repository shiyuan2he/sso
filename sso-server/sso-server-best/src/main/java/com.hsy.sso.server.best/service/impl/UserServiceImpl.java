package com.hsy.sso.server.best.service.impl;
import com.hsy.java.bean.dto.ResponseBodyBean;
import com.hsy.java.bean.vo.SessionBean;
import com.hsy.java.bean.vo.UserInfoBean;
import com.hsy.java.enums.BusinessEnum;
import com.hsy.java.enums.CacheEnum;
import com.hsy.java.exception.service.BusinessException;
import com.hsy.java.java.base.string.StringHelper;
import com.hsy.sso.server.best.dao.CrmInterfaceInvoke;
import com.hsy.sso.server.best.dao.RedisInterfaceInvoke;
import com.hsy.sso.server.best.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

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
@Service("userService")
public class UserServiceImpl implements IUserService {
    private Logger _logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    CrmInterfaceInvoke crmInterfaceInvoke ;
    @Autowired
    RedisInterfaceInvoke redisInterfaceInvoke ;

    @Override
    public SessionBean login(Long mobile,String username,String password){
        String ticket = "";
        try{
            ResponseBodyBean<UserInfoBean> userInfoDto = crmInterfaceInvoke.queryUserInfo(null,mobile,username,password) ;
            if(!userInfoDto.isSuccess()){
                return null ;
            }
            UserInfoBean userInfoBean = userInfoDto.getData() ;
            // 生成一张同一时空下唯一通票，并将这张票保存在缓存当中
            ticket = UUID.randomUUID().toString() ;
            _logger.info("【sso登陆-购票大厅】{}购票成功,通票是{}",mobile,ticket);
            redisInterfaceInvoke.setStringValue(
                    CacheEnum.CACHE_KEY_TICKET.getCode() + ticket,
                    String.valueOf(mobile),
                    CacheEnum.CACHE_KEY_TICKET.getExpire());

            _logger.info("【sso登陆-购票大厅】将key={},value={},expire={}存在缓存当中",
                    CacheEnum.CACHE_KEY_TICKET.getCode() + ticket,
                    mobile,CacheEnum.CACHE_KEY_TICKET.getExpire());

            SessionBean sessionBean = new SessionBean() ;
            sessionBean.setMobile(mobile);
            sessionBean.setTicket(ticket);
            sessionBean.setUserName(userInfoBean.getUserName());
            sessionBean.setUserId(userInfoBean.getUserId());
            sessionBean.setUserCode(userInfoBean.getUserCode());
            return sessionBean ;
        }catch(Exception e){
            // 登陆失败，清除此人票务信息
            if(null!=ticket&&!"".equals(ticket)){
                redisInterfaceInvoke.delete(CacheEnum.CACHE_KEY_TICKET.getCode() + ticket);
            }
            throw new BusinessException(BusinessEnum.LOGIN_EXCEPTION) ;
        }
    }

    @Override
    public boolean logout(String ticket) {
        if(StringHelper.isNotNullOrEmpty(ticket)){
            redisInterfaceInvoke.delete(CacheEnum.CACHE_KEY_TICKET.getCode() + ticket);
            return true ;
        }
        return false;
    }
}
