package com.qyj.facade.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
	
	/** 产品标题 */
	private String productTitle;
	
	/** 产品价格 */
	private BigDecimal productPrice;
	
	/** 产品实际剩余数量 */
	private Integer productNumber;
	
	/** 产品实际卖出数量 */
	private Integer productSoldNumber;
	
	/** 产品未支付数量 */
	private Integer productUnpayNumber;
	
	/** 产品状态 */
	private String productStatus;
	
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

	public Integer getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}

	public Integer getProductSoldNumber() {
		return productSoldNumber;
	}

	public void setProductSoldNumber(Integer productSoldNumber) {
		this.productSoldNumber = productSoldNumber;
	}

	public Integer getProductUnpayNumber() {
		return productUnpayNumber;
	}

	public void setProductUnpayNumber(Integer productUnpayNumber) {
		this.productUnpayNumber = productUnpayNumber;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	@Override
	public String toString() {
		return "QyjShoppingTrolleyEntity [id=" + id + ", userId=" + userId + ", productId=" + productId + ", number="
				+ number + ", createTime=" + createTime + ", productTitle=" + productTitle + ", productPrice="
				+ productPrice + ", productNumber=" + productNumber + ", productSoldNumber=" + productSoldNumber
				+ ", productUnpayNumber=" + productUnpayNumber + ", productStatus=" + productStatus + ", productImgUrl="
				+ productImgUrl + "]";
	}
	
}

