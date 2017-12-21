package com.qyj.back.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 产品信息实体类
 * 
 * @author CTF_stone
 */
public class QyjProductBean implements Serializable {

	private static final long serialVersionUID = 1798918175421166596L;

	/** 主键ID **/
	private Long id;

	/** 版本号 **/
	private Integer version = 0;

	private String title;

	private BigDecimal price;

	private String productType;

	private String productStatus;

	private Integer number;

	private Long createUser;

	/** 创建时间 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	private Long updateUser;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 产品详情列表数据 */
	List<QyjProductDetailBean> productDetailList = new ArrayList<QyjProductDetailBean>();
	
	/** 文件列表数据 */
	List<QyjFileInfoBean> fileInfoList = new ArrayList<QyjFileInfoBean>();

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<QyjProductDetailBean> getProductDetailList() {
		return productDetailList;
	}

	public void setProductDetailList(List<QyjProductDetailBean> productDetailList) {
		this.productDetailList = productDetailList;
	}

	public List<QyjFileInfoBean> getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(List<QyjFileInfoBean> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}

	@Override
	public String toString() {
		return "QyjProductBean [id=" + id + ", version=" + version + ", title=" + title + ", price=" + price
				+ ", productType=" + productType + ", productStatus=" + productStatus + ", number=" + number
				+ ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser=" + updateUser
				+ ", updateTime=" + updateTime + "]";
	}

}