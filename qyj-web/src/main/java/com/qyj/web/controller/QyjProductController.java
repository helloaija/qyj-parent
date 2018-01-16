package com.qyj.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.qyj.facade.QyjAddressFacade;
import com.qyj.facade.QyjOrderFacade;
import com.qyj.facade.QyjProductFacade;
import com.qyj.facade.vo.QyjAddressBean;
import com.qyj.facade.vo.QyjOrderBean;
import com.qyj.facade.vo.QyjProductBean;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.utils.SessionUtil;

@Controller
@RequestMapping("/wechat")
public class QyjProductController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(QyjProductController.class);

	@Autowired
	private QyjProductFacade productFacade;
	
	@Autowired
	private QyjAddressFacade addressFacade;
	
	@Autowired
	private QyjOrderFacade orderFacade;
	
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
			pageParam.setOrderByCondition("create_time desc");
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

	/**
	 * 获取产品订单数据，返回产品信息和默认送货地址
	 * @param productId 产品id
	 * @param addressId 送货地址id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/product/getProductOrder")
	public ResultBean getProductOrder(Long productId, Long addressId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			// 获取产品信息
			QyjProductBean productBean = productFacade.getProductInfoById(productId);
			if (productBean == null) {
				logger.info("getProductOrder result null, productId:{}", productId);
				return new ResultBean("0002", "产品信息为空！", productBean);
			}
			
			QyjAddressBean addressBean = null;
			// 如果地址id不为空就取该id的地址，否则取默认地址
			if (addressId != null) {
				addressBean = addressFacade.getAddressById(addressId);
			} else {
				List<QyjAddressBean> addressList = addressFacade.listAddressByUserId(userBean.getId());
				if (addressList != null && !addressList.isEmpty()) {
					// 如果没有设置默认地址，就取第一条地址
					addressBean = addressList.get(0);
					for (QyjAddressBean bean : addressList) {
						if (bean.getIsDefault()) {
							addressBean = bean;
							break;
						}
					}
				}
			}
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("product", productBean);
			dataMap.put("address", addressBean);
			
			return new ResultBean("0000", "请求成功！", dataMap);
		} catch (Exception e) {
			logger.error("getProductOrder error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 保存订单
	 * @param orderBean
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/product/saveProductOrder")
	public ResultBean saveProductOrder(QyjOrderBean orderBean, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			if (orderBean == null) {
				return new ResultBean("0002", "订单为空！", null);
			}
			
			orderBean.setUserId(userBean.getId());
			
			// 保存订单
			Long orderId = productFacade.saveOrder(orderBean);
			
			if (orderId != null) {
				QyjOrderBean queryBean = new QyjOrderBean();
				queryBean.setId(orderId);
				queryBean.setUserId(userBean.getId());
				// 根据订单id和用户id查询订单
				List<QyjOrderBean> orderBeanList = orderFacade.listOrderAndGoodsByModel(queryBean);
				if (orderBeanList != null && !orderBeanList.isEmpty()) {
					// 保存订单成功，并返回新增的订单
					return new ResultBean("0000", "保存订单成功！", orderBeanList.get(0));
				}
			}
			return new ResultBean("0002", "保存订单失败！", null);
		} catch (Exception e) {
			logger.error("saveProductOrder error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
}
