package com.qyj.facade.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.qyj.common.entity.BaseEntity;

/**
 * 产品信息实体类
 * @author CTF_stone
 */
public class QyjProductEntity extends BaseEntity {

	private static final long serialVersionUID = -5320457761225802641L;

	private String title;

	private BigDecimal price;

	private String productType;

	private String productStatus;

	private Integer number;
	
	/** 列表展示图片路径 */
	private String imgUrl;

	private Long createUser;

	private Long updateUser;

	private Date updateTime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "QyjProductEntity [title=" + title + ", price=" + price + ", productType=" + productType
				+ ", productStatus=" + productStatus + ", number=" + number + ", createUser=" + createUser
				+ ", updateUser=" + updateUser + ", updateTime=" + updateTime + "]";
	}

}