package com.qyj.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.utils.CommonUtils;
import com.qyj.web.common.utils.SessionUtil;

/**
 * 登录访问地址拦截器
 * @author CTF_stone
 */
public class RestrictInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(RestrictInterceptor.class);

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
		
		QyjUserBean userBean = (QyjUserBean) SessionUtil.getUserAttr(request, response);
		if (userBean == null) {
			// 判断 是否是微信浏览器
			String userAgent = request.getHeader("user-agent").toLowerCase();
			logger.info("preHandle userAgent={}", userAgent);
			// 微信客户端
//			if (userAgent.indexOf("micromessenger") > -1) {
//				String weChatUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
//						+ "appid=wx1957904a2993fb75&redirect_uri=http%3A%2F%2F39.108.108.147/wechat/snsapiBaseLogin"
//						+ "&response_type=code&scope=snsapi_base&state=673646agdggafaggg#wechat_redirect";
//				HttpClientUtil.get(weChatUrl);
//			} else {
				if ("XMLHttpRequest".equals(requestType)) {
					// ajax请求
					response.setHeader("sessionstatus", "timeout");
					response.sendError(401, "session timeout.");
					return false;
				}
				response.sendRedirect(CommonUtils.getPath(request) + "/weChat/page/index.html#!/login");
				return false;
//			}
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
