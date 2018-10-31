package com.qyj.store.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.qyj.common.exception.ValidException;
import com.qyj.common.page.PageParam;

/**
 * 公用控制器
 * @author CTF_stone
 */
public class BaseController {

	/**
	 * 加载分页信息
	 * @param request
	 * @return
	 */
	public PageParam initPageParam(HttpServletRequest request) {
		PageParam pageParam = new PageParam();
		// 当前页
		String currentPage = request.getParameter("currentPage");
		if (StringUtils.isNumeric(currentPage)) {
			pageParam.setCurrentPage(Integer.valueOf(currentPage));
		}
		// 分页数
		String pageSize = request.getParameter("pageSize");
		if (StringUtils.isNumeric(pageSize)) {
			pageParam.setPageSize(Integer.valueOf(pageSize));
		}

		return pageParam;
	}
	
	/**
	 * 获取异常信息，自定义异常直接返回
	 * @param e
	 * @return
	 */
	public String getExceptionMessage(Exception e) {
		if (e instanceof ValidException) {
			return e.getMessage();
		} else {
			return "系统异常：" + e.getMessage();
		}
	}
}
