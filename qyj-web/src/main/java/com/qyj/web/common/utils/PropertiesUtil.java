package com.qyj.web.common.utils;

import org.apache.commons.lang3.StringUtils;

import com.qyj.common.utils.CommonPropertiesUtil;

/**
 * 读取资源文件 properties
 * @author CTF_stone
 */
public class PropertiesUtil extends CommonPropertiesUtil {

	/** app.properties */
	private final static String PROPERTY_FILE_APP = "app.properties";

	/**
	 * 获取app.properties配置文件key对应的值
	 * @param propertyFileName
	 * @param key
	 * @return
	 */
	public static String getAppProperty(String key) {
		return getProperty(PROPERTY_FILE_APP, key);
	}
	
	/**
	 * 获取app.properties配置文件key对应的值，值为空就返回默认值
	 * @param key
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String getAppProperty(String key, String defaultValue) {
		String value = getProperty(PROPERTY_FILE_APP, key);
		if (StringUtils.isEmpty(value)) {
			value = defaultValue;
		}
		return value;
	}
	
	public static void main(String[] args) {
		String ss = String.format("%s?appid=%s&secret=%s&code=%s&grant_type=authorization_code", PropertiesUtil.getAppProperty("wechat.accessTokenUrl"), 
				PropertiesUtil.getAppProperty("wechat.appId"), PropertiesUtil.getAppProperty("wechat.appSecret"), 123);
		System.out.println(ss);
	}

}
