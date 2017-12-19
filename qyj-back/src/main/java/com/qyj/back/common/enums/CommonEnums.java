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
	 * 银行卡状态
	 * [UNCOMMITTED:未提交, SUBMIT：已经提交到存管，AUDIT:审核中, PASSED:审核通过, BACK:审核回退, REFUSED:审核拒绝，
	 *	UNBIND:解绑，UNACTIVE：未激活]
	 * @author shitl
	 *
	 */
	public enum BankCardAuditStatus {
		UNCOMMITTED, SUBMIT, AUDIT, PASSED, BACK, REFUSED, UNBIND, UNACTIVE
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
