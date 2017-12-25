package com.qyj.back.common.util;

/**
 * 字符串工具类
 * @author shitongle
 *
 */
public class StringUtils {
	
	/**
	 * 判断字符串是否为空
	 * @param str 字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str)) {
			return true;
		}
		
		if ("".equals(str.trim())) {
			return true;
		}
		
		return false;
	}
}
