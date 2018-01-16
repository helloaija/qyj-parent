package com.qyj.facade.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类-购物车
 * @author CTF_stone
 *
 */
public class QyjShoppingTrolleyEntity implements Serializable {
	private static final long serialVersionUID = -1639141527498336928L;

	private Long id;
	
	/** 用户id */
	private Long userId;
	
	/** 产品id */
	private Long productId;
	
	/** 购买数量 */
	private int number;
	
	/** 创建时间 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "QyjShoppingTrolley [id=" + id + ", userId=" + userId + ", productId=" + productId + ", number=" + number
				+ ", createTime=" + createTime + "]";
	}
}

