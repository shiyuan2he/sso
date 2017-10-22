package com.hsy.sso.base.bean.user;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.base.bean.user
 * @date 20/10/2017 5:50 PM
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class SessionBean {
    private String sessionId ;
    private String userName ;
    private String userCode ;
    private String password ;
    private String mobile ;
    private String passwordEncryptionType ;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPasswordEncryptionType() {
        return passwordEncryptionType;
    }

    public void setPasswordEncryptionType(String passwordEncryptionType) {
        this.passwordEncryptionType = passwordEncryptionType;
    }
}
