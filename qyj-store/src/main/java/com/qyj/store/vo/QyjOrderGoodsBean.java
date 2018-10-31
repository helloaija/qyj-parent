package com.qyj.store.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单商品
 * @author CTF_stone
 *
 */
public class QyjOrderGoodsBean implements Serializable {
	private static final long serialVersionUID = -7577697342578367708L;

	private Long id;

    /** 用户id */
    private Long userId;

    /** 订单id */
    private Long orderId;

    /** 产品id */
    private Long productId;
    
    /** 产品名称 */
    private String productTitle;

    /** 产品单价 */
    private BigDecimal price;

    /** 购买数量 */
    private Integer number;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
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
		return "QyjOrderGoodsBean [id=" + id + ", userId=" + userId + ", orderId=" + orderId + ", productId="
				+ productId + ", price=" + price + ", number=" + number + ", createTime=" + createTime + "]";
	}
}