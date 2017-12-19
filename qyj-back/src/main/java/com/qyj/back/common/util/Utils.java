package com.qyj.back.common.util;

import java.io.File;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Utils {

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
	 * resonse返回字符串
	 * @param response
	 * @param str
	 * @throws Exception
	 */
	public static void responsePrint(HttpServletResponse response, String str) throws Exception {
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		Writer writer = response.getWriter();
		writer.write(str);
		writer.flush();
		writer.close();
	}

	/**
	 * resonse返回map
	 * @param response
	 * @param map
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void responsePrint(HttpServletResponse response, Map map) throws Exception {
		String str = JSON.toJSONString(map);
		responsePrint(response, str);
	}

	/**
	 * resonse返回json
	 * @param response
	 * @param json
	 * @throws Exception
	 */
	public static void responsePrint(HttpServletResponse response, JSONObject json) throws Exception {
		responsePrint(response, json.toJSONString());
	}

	/**
	 * 获取服务器地址
	 * @param request
	 * @return
	 */
	public static String getPath(HttpServletRequest request) {
		String serverPort = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		String path = request.getScheme() + "://" + request.getServerName() + serverPort + request.getContextPath();
		return path;
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
}
