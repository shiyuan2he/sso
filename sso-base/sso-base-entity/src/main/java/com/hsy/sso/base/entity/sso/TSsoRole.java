package com.hsy.sso.base.entity.sso;

import java.io.Serializable;
import java.util.Date;


public class TSsoRole implements Serializable {
	private Long id;
	private String roleName;
	private String roleDescription;
	private Long creater;
	private Date createTime;
	private Long updater;
	private Date updateTime;
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	public String getRoleName(){
		return roleName;
	}
	public void setRoleDescription(String roleDescription){
		this.roleDescription=roleDescription;
	}
	public String getRoleDescription(){
		return roleDescription;
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
}

