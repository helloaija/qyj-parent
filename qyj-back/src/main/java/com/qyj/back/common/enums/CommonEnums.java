package com.qyj.back.common.enums;

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
	 * 预处理状态[SUCCESS:成功,FAILURE:失败,EXCEPTION:异常, CANCEL:取消预处理]
	 * @author shitl
	 *
	 */
	public enum PreTransactionStatus {
		SUCCESS, FAILURE, EXCEPTION, CANCEL
	}
}
