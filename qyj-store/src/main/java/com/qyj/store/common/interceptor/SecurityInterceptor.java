package com.qyj.store.common.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.qyj.store.common.util.StringUtils;
import com.qyj.store.entity.SysMenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qyj.store.common.constant.CommonConstant;
import com.qyj.store.common.util.SessionUtil;
import com.qyj.store.common.util.Utils;
import com.qyj.store.vo.SysUserBean;

/**
 * @author CTF_stone
 * 类说明：权限校验拦截器
 */
public class SecurityInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	/** 不用登陆请求路径 */
	private List<String> excludeUrls;

	/**
	 * 完成页面的render后调用
	 * @param request
	 * @param response
	 * @param object
	 * @param exception
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 * @param request
	 * @param response
	 * @param object
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 拦截过滤权限控制，登录访问拦截、菜单权限拦截
	 * @param request
	 * @param response
	 * @param object
	 * @return
	 * @throws Exception
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
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "session timeout.");
				return false;
			}
			response.sendRedirect(Utils.getPath(request) + "/page/index.html");
			return false;
		}

		// 用户可以访问的地址
		List<SysMenuModel> menuList = (List<SysMenuModel>) SessionUtil.getAttribute(request, CommonConstant.SESSION_MENU);
		List<String> menuUrlList = new ArrayList<>(menuList.size() + 1);
		menuUrlList.add("/admin/login/getLoginInfo".replaceAll("/", ""));
		menuUrlList.add("/admin/login/logOut".replaceAll("/", ""));
		for (SysMenuModel menu : menuList) {
			if (!StringUtils.isEmpty(menu.getUrl())) {
				menuUrlList.addAll(Arrays.asList(menu.getUrl().replaceAll("/", "").split(";")));
			}
		}

		if (!menuUrlList.contains(url.replaceAll("/", ""))) {
			if ("XMLHttpRequest".equals(requestType)) {
				// 没有访问权限
				response.sendError(550, "forbidden.");

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("resultCode", "0001");
				jsonObject.put("resultMessage", "没有访问权限！");
				Utils.responsePrint(response, jsonObject);
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
