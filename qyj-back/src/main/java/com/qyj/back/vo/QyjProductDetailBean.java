package com.qyj.back.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 产品详细信息实体类
 * 
 * @author CTF_stone
 *
 */
public class QyjProductDetailBean implements Serializable {
	private static final long serialVersionUID = -495653491936823456L;

	/** 主键id */
	private Long id;

	/** 关联的产品id */
	private Long productId;

	/** 详情名称[图文说明、参数规格、用法用量]等 */
	private String name;

	/** 展示序号 */
	private Integer index;

	/** 状态[SHOW:显示,HIDE:隐藏] */
	private String status;

	/** 备注 */
	private String remark;

	/** 创建时间 */
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

	/** 创建人id */
	private Long createUser;

	/** 更新时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 更新人id */
	private Long updateUser;

	/** 详情内容 */
	private String content;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "QyjProductDetailEntity [id=" + id + ", productId=" + productId + ", name=" + name + ", index=" + index
				+ ", status=" + status + ", remark=" + remark + ", createTime=" + createTime + ", createUser="
				+ createUser + ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", content=" + content
				+ "]";
	}
}