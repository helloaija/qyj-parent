package com.qyj.back.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qyj.back.common.constant.CommonConstant;

/**
 * session工具类
 * @author CTF_stone
 */
public final class SessionUtil {
	protected static final Logger logger = LoggerFactory.getLogger(SessionUtil.class);

	/**
	 * 设置session的值
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setAttribute(HttpServletRequest request, String key, Object value) {
		request.getSession(true).setAttribute(key, value);
	}

	/**
	 * 获取session的值
	 * @param request
	 * @param key
	 * @param value
	 */
	public static Object getAttribute(HttpServletRequest request, String key) {
		return request.getSession(true).getAttribute(key);
	}

	/**
	 * 删除Session值
	 * @param request
	 * @param key
	 */
	public static void removeAttribute(HttpServletRequest request, String key) {
		request.getSession(true).removeAttribute(key);
	}

	/**
	 * 设置用户信息 到session
	 * @param request
	 * @param user
	 */
	public static void setUserSession(HttpServletRequest request, Object user) {
		request.getSession(true).setAttribute(CommonConstant.SESSION_USER, user);
	}

	/**
	 * 从session中获取用户信息
	 * @param request
	 * @return SysUser
	 */
	public static Object getUserSession(HttpServletRequest request) {
		return request.getSession(true).getAttribute(CommonConstant.SESSION_USER);
	}

	/**
	 * 从session中获取用户信息
	 * @param request
	 * @return SysUser
	 */
	public static void removeUserSession(HttpServletRequest request) {
		removeAttribute(request, CommonConstant.SESSION_USER);
	}

	/**
	 * 设置验证码 到session
	 * @param request
	 * @param user
	 */
	public static void setVerifySession(HttpServletRequest request, String sessionVerifyCode) {
		request.getSession(true).setAttribute(CommonConstant.SESSION_VERIFY, sessionVerifyCode);
	}

	/**
	 * 从session中获取验证码
	 * @param request
	 * @return SysUser
	 */
	public static String getVerifySession(HttpServletRequest request) {
		return (String) request.getSession(true).getAttribute(CommonConstant.SESSION_VERIFY);
	}

	/**
	 * 从session中获删除验证码
	 * @param request
	 * @return SysUser
	 */
	public static void removeVerifySession(HttpServletRequest request) {
		removeAttribute(request, CommonConstant.SESSION_VERIFY);
	}

	/**
	 * 清除所有Session
	 * @param session
	 */
	public static void removeSessionAll(HttpSession session) {
		if (session != null) {
			java.util.Enumeration<?> e = session.getAttributeNames();
			while (e.hasMoreElements()) {
				String sessionName = (String) e.nextElement();
				logger.info("removeSessionName:" + sessionName);
				session.removeAttribute(sessionName);
			}
		}
	}
}
