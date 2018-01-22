package com.qyj.back.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 新闻资讯实体类
 * @author CTF_stone
 */
public class QyjNewsInfoEntity implements Serializable {
	private static final long serialVersionUID = -5879129681942289931L;
	
	private Long id;
	/** 标题 */
	private String title;

	/** 类型[NEWS：新闻资讯，TECHNIQUE：技术支持，NOTICE：通知公告] */
	private String type;

	/** 状态[PUBLISHED：发布状态，UNPUBLISH：未发布状态] */
	private String newsStatus;

	/** 说明 */
	private String description;

	/** 序号 */
	private Integer orderNum;

	/** 访问量 */
	private Integer visitCount;

	/** 创建人id */
	private Long createUser;

	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/** 更新人id */
	private Long updateUser;

	/** 更新时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 内容 */
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(String newsStatus) {
		this.newsStatus = newsStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "QyjNewsInfoEntity [id=" + id + ", title=" + title + ", type=" + type + ", newsStatus=" + newsStatus
				+ ", description=" + description + ", orderNum=" + orderNum + ", visitCount=" + visitCount
				+ ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser=" + updateUser
				+ ", updateTime=" + updateTime + "]";
	}

}