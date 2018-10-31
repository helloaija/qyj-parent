package com.qyj.common.exception;

/**
 * 自定义异常 - 校验异常，用来提示用户校验不通过
 * @author CTF_stone
 *
 */
public class ValidException extends Exception {

	private static final long serialVersionUID = -6606877244787368027L;

	public ValidException() {}
	
	public ValidException(String message) {
		super(message);
	} 
}
