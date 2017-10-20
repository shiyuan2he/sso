package com.hsy.sso.base.entity.sso;
/**
 * @description <p>sso用户实体</p>
 * @author heshiyuan 
 * @date 20/10/2017 10:08 AM
 * @email shiyuan4work@sina.com
 * @github https://github.com/shiyuan2he.git
 * Copyright (c) 2016 shiyuan4work@sina.com All rights reserved
 */
public class TSsoUser {
    private String id;
    private String userCode;
    private String userName;
    private String password;
    private String passwordEncryptionType;
    private String mobile;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordEncryptionType(String passwordEncryptionType) {
        this.passwordEncryptionType = passwordEncryptionType;
    }

    public String getPasswordEncryptionType() {
        return passwordEncryptionType;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }
}

