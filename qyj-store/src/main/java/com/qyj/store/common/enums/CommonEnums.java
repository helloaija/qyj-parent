package com.qyj.store.common.enums;

public class CommonEnums {
	
	/**
	 *  产品发布状态[PUBLISH：发布，PUTAWAY：上架，SOLDOUT：下架]
	 * @author shitl
	 *
	 */
	public enum ProductStatusEnum {
		PUBLISH, PUTAWAY, SOLDOUT
	}
	
	/**
	 * 新闻公告状态[PUBLISH：发布，PUTAWAY：上架，SOLDOUT：下架]
	 * @author shitl
	 *
	 */
	public enum NewsStatusEnum {
		PUBLISH, PUTAWAY, SOLDOUT
	}
	
	/**
	 * 产品详细状态
	 * [SHOW:显示,HIDE:隐藏]
	 * @author shitl
	 *
	 */
	public enum ProductDetailStatusEnum {
		SHOW, HIDE
	}
	
	/**
	 * 关联类型[ROLEMENU:角色关联的菜单，USERROLE：用户关联的角色]
	 * @author shitl
	 *
	 */
	public enum RelatinTypeEnum {
		ROLEMENU, USERROLE
	}

	/**
	 * 订单状态[UNPAY：未支付，HASPAY：已支付]
	 */
	public enum OrderStatusEnum {
		UNPAY, HASPAY
	}

	/**
	 * 启用状态[DISABLED: 禁用, USABLE: 启用]
	 */
	public enum UserEnableEnum {
		DISABLED, USABLE
	}
}
