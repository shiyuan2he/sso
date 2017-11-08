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
    private Long id;
    private String userCode;
    private String userName;
    private String password;
    private String passwordEncryptionType;
    private Long mobile;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCodeStirng() {
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

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getMobile() {
        return mobile;
    }

    public String getUserCode() {
        return userCode;
    }


    @Override
    public String toString() {
        return "TSsoUser{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", passwordEncryptionType='" + passwordEncryptionType + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}

