package com.qyj.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.utils.SessionUtil;

/**
 * 不用登录访问地址拦截器
 * @author CTF_stone
 */
public class FreedomInterceptor implements HandlerInterceptor {

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截 session 过期拦截 权限操作控制拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// 如果用户已经登录，延长用户登录时间
		SessionUtil.getUserAttr(request, response);
		return true;
	}

}
