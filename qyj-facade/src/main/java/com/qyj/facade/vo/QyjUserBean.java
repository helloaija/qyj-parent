package com.qyj.facade.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QyjUserBean implements Serializable {

	private static final long serialVersionUID = 9089952264125236067L;
	
	/** 主键id */
	private Long id;

	/** 用户名 */
	private String userName;

	/** 手机号码 */
	private String phoneNum;

	/** 登录密码 */
	private String password;
	
	/** 微信公众号openId */
	private String openId;

	/** 账户状态 */
	private String status;

	/** 最后登录ip */
	private String loginIp;

	/** 最后登录时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;

	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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
		return "QyjUserBean [id=" + id + ", userName=" + userName + ", phoneNum=" + phoneNum + ", password=" + password
				+ ", openId=" + openId + ", status=" + status + ", loginIp=" + loginIp + ", loginTime=" + loginTime
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
