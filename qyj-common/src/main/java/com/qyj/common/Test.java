package com.qyj.common;

import org.apache.commons.lang3.StringUtils;

import com.qyj.common.utils.EncryptionUtils;

public class Test {

	public static void main(String[] args) {
		System.out.println(StringUtils.isNumeric("012335621623634276473"));
		System.out.println(Integer.valueOf("0001"));
		System.err.println(EncryptionUtils.getMD5("123456"));
	}

}
