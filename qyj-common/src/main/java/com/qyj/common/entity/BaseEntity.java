package com.qyj.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类，包含各实体公用属性 .
 * @author CTF_stone
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -5412674193784624327L;

	/** 主键ID **/
	private Long id;

	/** 版本号 **/
	private Integer version = 0;

	/** 创建时间 **/
	private Date createTime;

	/** 主键ID **/
	public Long getId() {
		return id;
	}

	/** 主键ID **/
	public void setId(Long id) {
		this.id = id;
	}

	/** 版本号 **/
	public Integer getVersion() {
		return version;
	}

	/** 版本号 **/
	public void setVersion(Integer version) {
		this.version = version;
	}

	/** 创建时间 **/
	public Date getCreateTime() {
		return createTime;
	}

	/** 创建时间 **/
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
