package com.qyj.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类-购物车
 * @author CTF_stone
 *
 */
public class QyjShoppingTrolleyBean implements Serializable {

	private static final long serialVersionUID = -8263911067281048656L;

	private Long id;
	
	/** 用户id */
	private Long userId;
	
	/** 产品id */
	private Long productId;
	
	/** 购买数量 */
	private int number;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 产品标题 */
	private String productTitle;
	
	/** 产品价格 */
	private BigDecimal productPrice;
	
	/** 产品图片 */
	private String productImgUrl;

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

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	@Override
	public String toString() {
		return "QyjShoppingTrolley [id=" + id + ", userId=" + userId + ", productId=" + productId + ", number=" + number
				+ ", createTime=" + createTime + "]";
	}
}

