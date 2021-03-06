package com.hsy.sso.base.entity.sso;

import java.io.Serializable;
import java.util.Date;


public class TSsoUser implements Serializable{
	private Long id;
	private String userCode;
	private String userName;
	private String password;
	private String passwordEncryptionType;
	private Short sex ;
	private Long mobile;
	private String email ;
	private Long creater;
	private Date createTime;
	private Long updater;
	private Date updateTime;
	private String source;
	private String remark ;
	private Short isDel ;
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setUserCode(String userCode){
		this.userCode=userCode;
	}
	public String getUserCode(){
		return userCode;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setPasswordEncryptionType(String passwordEncryptionType){
		this.passwordEncryptionType=passwordEncryptionType;
	}
	public String getPasswordEncryptionType(){
		return passwordEncryptionType;
	}
	public void setMobile(Long mobile){
		this.mobile=mobile;
	}
	public Long getMobile(){
		return mobile;
	}
	public void setCreater(Long creater){
		this.creater=creater;
	}
	public Long getCreater(){
		return creater;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setUpdater(Long updater){
		this.updater=updater;
	}
	public Long getUpdater(){
		return updater;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public void setSource(String source){
		this.source=source;
	}
	public String getSource(){
		return source;
	}

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }
}

