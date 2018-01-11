package com.qyj.common.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class CommonUtils {

	/**
	 * 判断集合是否为空
	 * @param con
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmptyCollection(Collection con) {
		if (con == null || con.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取项目部署绝对路径（evan.webapp在web.xml中有配置）
	 * @return
	 */
	public static String getWebAppPath() {
		return System.getProperty("evan.webapp");
	}
	
	/**
	 * 获取tomcat根目录
	 * @return
	 */
	public static String getTomcatPath() {
		return System.getProperty("catalina.home");
	}
	
	/**
	 * 获取上传文件目录（保存在tomcat根目录中，获取文件需要配置虚拟目录）
	 * @return
	 */
	public static String getUploadFilePath() {
		File uploadFileDir = new File(getTomcatPath() + File.separator + "uploadFile");
		if (!uploadFileDir.exists()) {
			uploadFileDir.mkdirs();
		}
		return uploadFileDir.getAbsolutePath();
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
	
	/**
	 * 获取随机数
	 * @param min 最小数
	 * @param max 最大数
	 * @return int
	 */
	public static int getRandom(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}

	/**
	 * 获取随机数从1开始,格式10000000-99999999
	 * @param number 随机数长度
	 * @return
	 */
	public static int getRandom(int number) {
		int max = 9;
		int min = 1;
		for (int i = 1; i < number; i++) {
			min = min * 10;
			max = max * 10 + 9;
		}
		return getRandom(min, max);
	}

	/**
	 * 12位时间加上number位数
	 * @param number
	 * @return Long
	 */
	public static Long getUid(int number) {
		return Long.parseLong(new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + getRandom(number));
	}
	
	/**
	 * 12位时间加上六位随机数
	 * @return
	 */
	public static Long getUid() {
		return getUid(6);
	}
}
