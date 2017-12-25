package com.hsy.sso.server.best.service.impl;
import com.hsy.java.bean.vo.SessionBean;
import com.hsy.java.enums.BusinessEnum;
import com.hsy.java.enums.CacheEnum;
import com.hsy.java.enums.ConstantEnum;
import com.hsy.java.java.base.string.StringHelper;
import com.hsy.sso.server.best.dao.impl.SpringDataRedisDao;
import com.hsy.sso.server.best.service.IUserService;
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

public class UserServiceImpl implements IUserService {
    private Logger _logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SpringDataRedisDao springDataRedisDao ;


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
                springDataRedisDao.getValue(CacheEnum.CACHE_KEY_IMAGE_CODE) .putCacheWithExpireTime(CommonConstant.TICKET_CACHE_KEY+ticket,sessionBean,2 * 60) ;
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
    public boolean reg(String userName,Long mobile, String password,Short sex,String email,String remark,Long userId) {
        TSsoUser ssoUser = new TSsoUser() ;
        String id = StringHelper.generateRandomOfStringByLength(19) ;
        ssoUser.setId(Long.parseLong(id));
        ssoUser.setMobile(mobile);
        if (StringHelper.isNullOrEmpty(password)){
            ssoUser.setPassword(Base64Helper.stringToBase64OfCc("123"));
        }else{
            ssoUser.setPassword(Base64Helper.stringToBase64OfCc(password));
        }
        ssoUser.setPasswordEncryptionType(ConstantEnum.ENCRYPTION_TYPE_BASE64.getCode());
        if (StringHelper.isNullOrEmpty(userName)){
            ssoUser.setUserName("sso"+System.currentTimeMillis());
        }else{
            ssoUser.setUserName(userName);
        }
        if(null == userId){
            ssoUser.setCreater(Long.parseLong(id));
        }else{
            ssoUser.setCreater(userId);
        }
        if(StringHelper.isNotNullOrEmpty(email)){
            ssoUser.setEmail(email);
        }
        if(null!=sex){
            ssoUser.setSex(sex);
        }
        if(StringHelper.isNotNullOrEmpty(remark)){
            ssoUser.setRemark(remark);
        }
        ssoUser.setCreateTime(Calendar.getInstance().getTime());
        ssoUser.setIsDel((short)0);
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

    @Override
    public List<TSsoUser> getAll(Integer offset, Integer limit) {
        return itSsoUserMapper.getAll(offset, limit);
    }

    @Override
    public boolean update(Long id, String userName, String password,Long mobile,Long userId) {
        TSsoUser user = new TSsoUser();
        user.setId(id);
        user.setUserName(userName);
        user.setPassword(password);
        user.setMobile(mobile);
        user.setUpdater(userId);
        user.setUpdateTime(Calendar.getInstance().getTime());
        if(itSsoUserMapper.update(user) > 0){
            return true ;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(itSsoUserMapper.delete(id) > 0){
            return true ;
        }
        return false;
    }
}
