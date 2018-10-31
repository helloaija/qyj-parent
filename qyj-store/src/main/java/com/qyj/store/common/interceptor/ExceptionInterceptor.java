package com.qyj.store.common.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qyj.store.common.util.Utils;

/**
 * 全局异常拦截
 * @author shitongle
 */
public class ExceptionInterceptor implements HandlerExceptionResolver {

	private static final Logger log = LoggerFactory.getLogger(ExceptionInterceptor.class);

	@SuppressWarnings("static-access")
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (exception != null) {
			map.put("msg", exception.getMessage());
			// String msg = exception.toString();
			/*
			 * 记录请求参数
			 */
			logParam(request, handler, exception);
			/*
			 * 记录异常到错误日志文件
			 */
			logLogFile(exception);

			response.setStatus(response.SC_SERVICE_UNAVAILABLE);
		}

		String requestType = request.getHeader("X-Requested-With");

		if ("XMLHttpRequest".equals(requestType)) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", false);
			jsonObject.put("msg", "程序异常，请联系技术人员！");
			try {
				Utils.responsePrint(response, jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return new ModelAndView("/error/cy500");
		}
		return null;
	}

	/**
	 * 记录参数
	 * @param request
	 * @param handler
	 * @param ex
	 */
	@SuppressWarnings("unchecked")
	private void logParam(HttpServletRequest request, Object handler, Exception ex) {
		StringBuffer msg = new StringBuffer();
		msg.append("[uri＝").append(request.getRequestURI()).append("]");
		Enumeration<String> enumer = request.getParameterNames();
		while (enumer.hasMoreElements()) {
			String name = (String) enumer.nextElement();
			String[] values = request.getParameterValues(name);
			msg.append("[").append(name).append("=");
			if (values != null) {
				int i = 0;
				for (String value : values) {
					i++;
					msg.append(value);
					if (i < values.length) {
						msg.append("｜");
					}
				}
			}
			msg.append("]");
		}
		log.error(msg.toString(), ex);
	}

	/**
	 * 向日志文件写入异常日志。
	 * @throws Exception
	 */
	private void logLogFile(Exception exception) {
		StackTraceElement[] elements = exception.getStackTrace();
		// for (StackTraceElement element : elements) {
		// if
		// (element.getClassName().indexOf(CommonConstant.SYSTEM_LOG_INTERCEPTOR_PACKAGE)
		// < 0)
		// continue;
		// String fileName = element.getFileName();
		// StringBuffer sb = new StringBuffer();
		// sb.append(DateUtil.dateToString());
		// sb.append("[");
		// sb.append(this.extendErrorType(fileName));
		// sb.append("]");
		// sb.append(CommonConstant.SYSTEM_LOG_CLASS_POSITION);
		// sb.append(element.getClassName());
		// sb.append("；");
		// sb.append(CommonConstant.SYSTEM_LOG_METHOD_POSITION);
		// sb.append(element.getMethodName());
		// sb.append("； ");
		// sb.append(CommonConstant.SYSTEM_LOG_LINENUMBER_POSITION);
		// sb.append(element.getLineNumber());
		// sb.append("；");
		// sb.append(CommonConstant.SYSTEM_LOG_ERRORTYPE_POSITION);
		// sb.append(exception.getMessage());
		// log.error(sb.toString());
		// }
	}

	/**
	 * 返回指定JAVA文件发生的异常类型
	 * @param fileName -发生异常的JAVA文件
	 * @return String -系统日志文件定为的异常描述信息
	 */
	private String extendErrorType(String fileName) {
		// String errorType = this.expandMessage;
		// if (fileName.indexOf(CommonConstant.SYSTEM_LOG_USER_DAO) > 0) {
		// errorType = CommonConstant.SYSTEM_LOG_DAO_MESSAGE;
		// } else if (fileName.indexOf(CommonConstant.SYSTEM_LOG_USER_SERVICE) >
		// 0) {
		// errorType = CommonConstant.SYSTEM_LOG_SERVICE_MESSAGE;
		// } else if
		// (fileName.indexOf(CommonConstant.SYSTEM_LOG_USER_CONTROLLER) > 0) {
		// errorType = CommonConstant.SYSTEM_LOG_CONTROLLER_MESSAGE;
		// } else {
		// errorType = CommonConstant.SYSTEM_LOG_OTHER_MESSAGE;
		// }
		// return errorType;
		return "";
	}

}
