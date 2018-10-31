package com.qyj.store.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 基础model
 * @author shitongle
 */
public class BaseModel {
	private Long id;

	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/** 修改时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	/** 创建用户 */
	private Long createUser;
	
	/** 修改用户 */
	private Long updateUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the createUser
	 */
	public Long getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the updateUser
	 */
	public Long getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

}
