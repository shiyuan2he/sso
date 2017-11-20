package com.hsy.sso.service.api;
import com.hsy.bean.vo.SessionBean;
import com.hsy.sso.base.entity.sso.TSsoUser;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.service.jdbc
 * @date 20/10/2017 1:18 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public interface ITSsoUserService {
    /**
     * @description <p>登陆</p>
     * @param mobile 手机号
     * @param password 用户密码
     * @return 是否登陆成功
     * @author heshiyuan
     * @date 20/10/2017 1:19 PM
     */
    SessionBean login(Long mobile, String password);
    /**
     * @description <p>注册</p>
     * @param mobile 手机号
     * @param password 用户密码
     * @return 是否注册成功
     * @author heshiyuan 
     * @date 2017/10/27 09:49
     */
    boolean reg(String userName,Long mobile, String password,Short sex,String email,String remark,Long userId) ;
    /**
     * @description <p>退出</p>
     * @param
     * @return
     * @author heshiyuan
     * @date 2017/11/2 14:33
     */
    boolean logout(String ticket);

    List<TSsoUser> getAll(Integer offset,Integer limit) ;

    boolean update(Long id,String userName,String password,Long mobile,Long userId) ;

    boolean delete(Long id) ;
}
