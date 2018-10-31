package com.qyj.store.entity;

import com.qyj.store.vo.QyjOrderBean;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 进货单
 * @author CTF_stone
 */
public class QyjStockOrderEntity implements Serializable {

	private Long id;

	/** 订单号 */
	private String orderNumber;

	/** 订单实际价格 */
	private BigDecimal orderAmount;

	/** 修改后的订单价格 */
	private BigDecimal modifyAmount;

	/** 已支付金额 */
	private BigDecimal hasPayAmount;

	/** 订单状态[UNPAY：待支付，UNSEND：代发货，UNTAKE：待收货，END：已结束] */
	private String orderStatus;

	/** 供应商名称 */
	private String supplierName;

	/** 供应商电话 */
	private String supplierPhone;

	/** 供应商地址 */
	private String supplierAddress;

	/** 供应商信息 */
	private String supplierMessage;

	/** 支付时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payTime;

	/** 完成时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date finishTime;

	/** 备注 */
	private String remark;

	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 创建人 */
	private Long createUser;

	/** 更新时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 更新人 */
	private Long updateUser;
	
	/** 订单商品列表 */
	List<QyjStockProductEntity> stockProductList = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getModifyAmount() {
		return modifyAmount;
	}

	public void setModifyAmount(BigDecimal modifyAmount) {
		this.modifyAmount = modifyAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getHasPayAmount() {
		return hasPayAmount;
	}

	public void setHasPayAmount(BigDecimal hasPayAmount) {
		this.hasPayAmount = hasPayAmount;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierMessage() {
		return supplierMessage;
	}

	public void setSupplierMessage(String supplierMessage) {
		this.supplierMessage = supplierMessage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public List<QyjStockProductEntity> getStockProductList() {
		return stockProductList;
	}

	public void setStockProductList(List<QyjStockProductEntity> stockProductList) {
		this.stockProductList = stockProductList;
	}

	@Override
	public String toString() {
		return "QyjStockOrderEntity{" +
				"id=" + id +
				", orderNumber='" + orderNumber + '\'' +
				", orderAmount=" + orderAmount +
				", modifyAmount=" + modifyAmount +
				", hasPayAmount=" + hasPayAmount +
				", orderStatus='" + orderStatus + '\'' +
				", supplierName='" + supplierName + '\'' +
				", supplierPhone='" + supplierPhone + '\'' +
				", supplierAddress='" + supplierAddress + '\'' +
				", supplierMessage='" + supplierMessage + '\'' +
				", payTime=" + payTime +
				", finishTime=" + finishTime +
				", remark='" + remark + '\'' +
				", createTime=" + createTime +
				", createUser=" + createUser +
				", updateTime=" + updateTime +
				", updateUser=" + updateUser +
				", stockProductList=" + stockProductList +
				'}';
	}
}