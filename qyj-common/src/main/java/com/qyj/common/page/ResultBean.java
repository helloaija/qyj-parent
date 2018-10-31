package com.qyj.common.page;

/**
 * 返回页面结果
 * @author CTF_stone
 */
public class ResultBean {
	/** 结果编码 */
	private String resultCode;

	/** 结果信息 */
	private String resultMessage;

	/** 结果数据 */
	private Object result;
	
	public ResultBean() {
	}

	public ResultBean(String resultCode, String resultMessage) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.result = null;
	}

	public ResultBean(String resultCode, String resultMessage, Object result) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.result = result;
	}

	public ResultBean init(String resultCode, String resultMessage) {
		this.setResultCode(resultCode);
		this.resultMessage = resultMessage;
		return this;
	}

	public ResultBean init(String resultCode, String resultMessage, Object result) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.result = result;
		return this;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
