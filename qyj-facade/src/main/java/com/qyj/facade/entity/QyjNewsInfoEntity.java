package com.qyj.facade.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻资讯实体类
 * @author CTF_stone
 */
public class QyjNewsInfoEntity implements Serializable {
	private static final long serialVersionUID = -120351497325117485L;
	
	private Long id;
	/** 标题 */
	private String title;

	/** 类型[NEWS：新闻资讯，TECHNIQUE：技术支持，NOTICE：通知公告] */
	private String type;

	/** 状态[PUBLISHED：发布状态，UNPUBLISH：未发布状态] */
	private String status;

	/** 说明 */
	private String description;

	/** 序号 */
	private Integer orderNum;

	/** 访问量 */
	private Integer visitCount;

	/** 创建人id */
	private Long createUser;

	/** 创建时间 */
	private Date createTime;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "QyjNewsInfoEntity [id=" + id + ", title=" + title + ", type=" + type + ", status=" + status
				+ ", description=" + description + ", orderNum=" + orderNum + ", visitCount=" + visitCount
				+ ", createUser=" + createUser + ", createTime=" + createTime + ", content=" + content + "]";
	}
}