package com.qyj.store.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 产品详细信息实体类
 * @author CTF_stone
 *
 */
public class QyjProductDetailEntity {
	/** 主键id */
    private Long id;

    /** 关联的产品id */
    private Long productId;

    /** 详情名称[图文说明、参数规格、用法用量]等 */
    private String name;

    /** 展示序号 */
    private Integer detailIndex;

    /** 状态[SHOW:显示,HIDE:隐藏] */
    private String detailStatus;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

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

    public Integer getDetailIndex() {
        return detailIndex;
    }

    public void setDetailIndex(Integer detailIndex) {
        this.detailIndex = detailIndex;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
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
		return "QyjProductDetailEntity [id=" + id + ", productId=" + productId + ", name=" + name + ", detailIndex=" + detailIndex
				+ ", detailStatus=" + detailStatus + ", remark=" + remark + ", createTime=" + createTime + ", createUser="
				+ createUser + ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", content=" + content
				+ "]";
	}
}