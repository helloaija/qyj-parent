package com.qyj.store.common.util;

import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qyj.common.utils.CommonUtils;

/**
 * 工具类
 * @author CTF_stone
 */
public class Utils extends CommonUtils {

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
	 * 获取区间随机数
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
	public static String getCurrentUid(int number) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + getRandom(number);
	}

}
