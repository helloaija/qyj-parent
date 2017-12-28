package com.qyj.facade.entity;

import java.io.Serializable;
import java.util.Date;

public class QyjUserEntity implements Serializable {

	private static final long serialVersionUID = 8493596990022713430L;

	/** 主键id */
	private Long id;

	/** 用户名 */
	private String userName;
	
	/** 手机号码 */
	private String phoneNum;

	/** 登录密码 */
	private String password;

	/** 账户状态 */
	private String status;

	/** 最后登录ip */
	private String loginIp;

	/** 最后登录时间 */
	private Date loginTime;

	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "QyjUserEntity [id=" + id + ", userName=" + userName + ", phoneNum=" + phoneNum + ", password="
				+ password + ", status=" + status + ", loginIp=" + loginIp + ", loginTime=" + loginTime
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
