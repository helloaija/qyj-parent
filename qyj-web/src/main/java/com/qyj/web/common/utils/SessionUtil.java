package com.qyj.web.common.utils;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.constant.CommonConstant;

public class SessionUtil {
	
	/** sessionId默认过期时间30分钟 */
	private static Long TIMEOUT = 30 * 60L;
	
	/** 登录默认过期时间15分钟 */
	private static Long TIMEOUT_USER = 15 * 60L;
	
	/**
	 * 保存sessionId+key为键的redis对象数据
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setObjectAttr(HttpServletRequest request, String key, Object value) {
		if (StringUtils.isEmpty(key)) {
			return;
		}
		String redisKey = getRedisKey(request, key);
		RedisUtil.setObjectEx(redisKey, value, TIMEOUT);
	}
	
	/**
	 * 保存sessionId+key为键的redis对象数据
	 * @param request
	 * @param key
	 * @param value
	 * @param timeout 过期时间
	 * @param timeUnit 时间单位
	 */
	public static void setObjectAttr(HttpServletRequest request, String key, Object value, Long timeout, TimeUnit timeUnit) {
		if (StringUtils.isEmpty(key)) {
			return;
		}
		String redisKey = getRedisKey(request, key);
		RedisUtil.setObjectEx(redisKey, value, timeout, timeUnit);
	}
	
	/**
	 * 保存sessionId+key为键的redis字符串数据
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setStringAttr(HttpServletRequest request, String key, String value) {
		if (StringUtils.isEmpty(key)) {
			return;
		}
		String redisKey = getRedisKey(request, key);
		RedisUtil.setString(redisKey, value);
	}
	
	/**
	 * 保存sessionId+key为键的redis字符串数据
	 * @param request
	 * @param key
	 * @param value
	 * @param timeout 过期时间
	 * @param timeUnit 时间单位
	 */
	public static void setStringAttr(HttpServletRequest request, String key, String value, Long timeout, TimeUnit timeUnit) {
		if (StringUtils.isEmpty(key)) {
			return;
		}
		String redisKey = getRedisKey(request, key);
		RedisUtil.setStringEx(redisKey, value, timeout, timeUnit);
	}
	
	/**
	 * 获取对象信息
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getObject(HttpServletRequest request, String key) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		String redisKey = getRedisKey(request, key);
		return RedisUtil.getObject(redisKey);
	}
	
	/**
	 * 获取字符串信息
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getString(HttpServletRequest request, String key) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		String redisKey = getRedisKey(request, key);
		return RedisUtil.getString(redisKey);
	}
	
	/**
	 * 保存用户登录信息
	 * @param request
	 * @param key
	 * @param userBean
	 */
	public static void setUserSttr(HttpServletRequest request, QyjUserBean userBean) {
		setObjectAttr(request, CommonConstant.SESSION_USER, userBean, TIMEOUT_USER, TimeUnit.SECONDS);
	}
	
	/**
	 * 获取登录用户信息，如果用户存在就延长用户信息保存时间
	 * @param request
	 * @param key
	 * @return
	 */
	public static QyjUserBean getUserStrr(HttpServletRequest request) {
		Object userObject = getObject(request, CommonConstant.SESSION_USER);
		if (userObject == null) {
			return null;
		}
		
		QyjUserBean userBean = (QyjUserBean) userObject;
		
		setUserSttr(request, userBean);
		
		return userBean;
	}
	
	/**
	 * 获取保存redis的key
	 * @param request
	 * @param key
	 * @return
	 */
	private static String getRedisKey(HttpServletRequest request, String key) {
		return request.getSession().getId() + key;
	}
}