package com.qyj.back.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qyj.back.common.constant.CommonConstant;
import com.qyj.back.common.util.SessionUtil;
import com.qyj.back.common.util.Utils;
import com.qyj.back.vo.SysUserBean;

/**
 * @author CTF_stone
 * @类说明：权限校验拦截器
 */
public class SecurityInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	/** 不用登陆请求路径 */
	private List<String> excludeUrls;

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
		String requestType = request.getHeader("X-Requested-With");

		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if (excludeUrls.contains(url)) {
			return true;
		}
		SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
		if (userBean == null) {
			if ("XMLHttpRequest".equals(requestType)) {
				// ajax请求
				response.setHeader("sessionstatus", "timeout");
                response.sendError(401, "session timeout.");
				return false;
			}
			response.sendRedirect(Utils.getPath(request) + "/page/index.html");
			return false;
		}

		return true;
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
}
