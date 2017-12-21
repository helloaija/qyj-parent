package com.qyj.back.common.enums;

public class CommonEnums {
	
	/**
	 *  产品发布状态[PUBLISHED：已发布，UNPUBLISHED：未发布]
	 * @author shitl
	 *
	 */
	public enum ProductStatusEnum {
		PUBLISHED, UNPUBLISHED
	}
	
	/**
	 * 新闻公告状态[PUBLISHED：已发布，UNPUBLISHED：未发布]
	 * @author shitl
	 *
	 */
	public enum NewsStatusEnum {
		PUBLISHED, UNPUBLISHED
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
