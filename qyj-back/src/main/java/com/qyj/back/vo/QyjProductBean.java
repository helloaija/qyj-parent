package com.qyj.back.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 产品信息实体类
 * @author CTF_stone
 */
public class QyjProductBean implements Serializable {

	private static final long serialVersionUID = 1798918175421166596L;

	/** 主键ID **/
	private Long id;

	/** 产品标题 */
	private String title;

	/** 单位价格 */
	private BigDecimal price;

	/** 产品类型 */
	private String productType;

	/** 产品状态 */
	private String productStatus;

	/** 产品库存 */
	private Integer number;

	/** 上架时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date putawayTime;

	/** 下架时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date soldoutTime;

	/** 已售数量 */
	private Integer soldNumber;

	/** 产品数量单位 */
	private String productUnit;

	/** 列表展示图片路径 */
	private String imgUrl;

	/** 版本号 **/
	private Integer version = 0;

	/** 创建人id */
	private Long createUser;

	/** 创建时间 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 更新人id */
	private Long updateUser;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	/** 产品详情列表数据 */
	List<QyjProductDetailBean> productDetailList = new ArrayList<QyjProductDetailBean>();
	
	/** 文件列表数据 */
	List<QyjFileInfoBean> fileInfoList = new ArrayList<QyjFileInfoBean>();

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

	public Date getPutawayTime() {
		return putawayTime;
	}

	public void setPutawayTime(Date putawayTime) {
		this.putawayTime = putawayTime;
	}

	public Date getSoldoutTime() {
		return soldoutTime;
	}

	public void setSoldoutTime(Date soldoutTime) {
		this.soldoutTime = soldoutTime;
	}

	public Integer getSoldNumber() {
		return soldNumber;
	}

	public void setSoldNumber(Integer soldNumber) {
		this.soldNumber = soldNumber;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
		return "QyjProductEntity [id=" + id + ", title=" + title + ", price=" + price + ", productType=" + productType
				+ ", productStatus=" + productStatus + ", number=" + number + ", putawayTime=" + putawayTime
				+ ", soldoutTime=" + soldoutTime + ", soldNumber=" + soldNumber + ", productUnit=" + productUnit
				+ ", imgUrl=" + imgUrl + ", version=" + version + ", createUser=" + createUser + ", createTime="
				+ createTime + ", updateUser=" + updateUser + ", updateTime=" + updateTime + "]";
	}

}