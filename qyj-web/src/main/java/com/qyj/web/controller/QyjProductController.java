package com.qyj.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;
import com.qyj.facade.QyjProductFacade;
import com.qyj.facade.vo.QyjProductBean;

@Controller
@RequestMapping("/wechat")
public class QyjProductController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(QyjProductController.class);

	@Autowired
	private QyjProductFacade productFacade;

	/**
	 * 获取产品分页数据信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/freedom/product/listProductPage")
	public ResultBean listProductPage(HttpServletRequest request, HttpServletResponse response) {
		PageParam pageParam = this.initPageParam(request);

		try {
			PageBean pageBean = productFacade.listProductPage(pageParam, null);
			return new ResultBean("0000", "请求成功", pageBean);
		} catch (Exception e) {
			logger.error("listProductPage error", e);
			return new ResultBean("0001", "请求异常", null);
		}
	}
	
	/**
	 * 根据主键查询产品信息
	 * @param productId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/freedom/product/getProductInfo")
	public ResultBean getProductInfo(Long productId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjProductBean product = productFacade.getProductInfoById(productId);
			if (product == null) {
				logger.info("getProductInfo result null, productId:{}", productId);
				return new ResultBean("0002", "产品信息为空！", product);
			}
			return new ResultBean("0000", "请求成功！", product);
		} catch (Exception e) {
			logger.error("getProductInfo error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

}
