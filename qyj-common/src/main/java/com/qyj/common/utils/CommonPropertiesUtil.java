package com.qyj.common.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 读取资源文件 properties
 * @author CTF_stone
 */
public class CommonPropertiesUtil {

	/**
	 * 获取配置文件key对应的值
	 * @param propertyFileName
	 * @param key
	 * @return
	 */
	public static String getProperty(String propertyFileName, String key) {
		Properties props = null;
		String value = null;
		try {
			props = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
			value = new String(props.getProperty(key).getBytes("ISO8859-1"), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 设置配置文件值
	 * @param propertyFileName
	 * @param key
	 * @param value
	 */
	public static void setPropertyString(String propertyFileName, String key, String value) {
		Properties props = null;

		try {
			props = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
			props.setProperty(key, value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
