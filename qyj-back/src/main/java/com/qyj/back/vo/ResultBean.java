package com.qyj.back.vo;

/**
 * ajax请求返回bean
 * @author shitongle
 */
public class ResultBean {
	// 是否成功
	private boolean success = true;
	// 返回信息
	private String message = "";
	// 返回编码
	private int code = 100;
	// 返回结果对象
	private Object data = null;

	public ResultBean() {

	}

	public ResultBean(boolean success, String message, int code, Object data) {
		this.success = success;
		this.message = message;
		this.code = code;
		this.data = data;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
