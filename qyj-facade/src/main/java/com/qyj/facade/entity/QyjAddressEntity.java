package com.qyj.facade.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 收获地址实体类
 * @author CTF_stone
 *
 */
public class QyjAddressEntity implements Serializable {
	private static final long serialVersionUID = -2946269171157406116L;

	private Long id;

    private Long userId;

    private String province;

    private String city;

    private String county;

    private String detail;

    private String phone;

    private String name;

    private Boolean isDefault;

    private Date createTime;

    private Date updateTime;

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "QyjAddressEntity [id=" + id + ", userId=" + userId + ", province=" + province + ", city=" + city
				+ ", county=" + county + ", detail=" + detail + ", phone=" + phone + ", name=" + name + ", isDefault="
				+ isDefault + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}