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
import com.qyj.facade.QyjOrderFacade;
import com.qyj.facade.vo.QyjOrderBean;
import com.qyj.facade.vo.QyjShoppingTrolleyListBean;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.utils.SessionUtil;

/**
 * 我的订单
 * @author CTF_stone
 *
 */
@Controller
@RequestMapping("/wechat")
public class QyjOrderController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(QyjOrderController.class);

	@Autowired
	private QyjOrderFacade orderFacade;
	
	/**
	 * 根据订单id获取订单
	 * @param orderId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/order/getOrderById")
	public ResultBean getOrderById(Long orderId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			if (orderId == null) {
				return new ResultBean("0002", "请求的订单编号为空", null);
			}
			
			QyjOrderBean queryBean = new QyjOrderBean();
			queryBean.setId(orderId);
			queryBean.setUserId(userBean.getId());
			
			List<QyjOrderBean> orderBeanList = orderFacade.listOrderAndGoodsByModel(queryBean);
			
			if (orderBeanList == null || orderBeanList.isEmpty()) {
				return new ResultBean("0002", "找不到订单" + orderId + "数据", null);
			}
			
			return new ResultBean("0000", "请求成功", orderBeanList.get(0));
		} catch (Exception e) {
			logger.error("getOrderById error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 获取订单分页数据信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/order/listOrderPage")
	public ResultBean listOrderPage(HttpServletRequest request, HttpServletResponse response) {
		QyjUserBean userBean = SessionUtil.getUserStrr(request);
		if (userBean == null) {
			return new ResultBean("0002", "用户未登录", null);
		}
		
		// 加载的订单状态
		String state = request.getParameter("state");
		PageParam pageParam = this.initPageParam(request);
		
		if ("UNPAY".equals(state)) {
			pageParam.setQueryCondition(" and status = 'UNPAY' ");
		} else if ("UNSEND".equals(state)) {
			pageParam.setQueryCondition(" and status = 'UNSEND' ");
		} else if ("UNTAKE".equals(state)) {
			pageParam.setQueryCondition(" and (status = 'UNSEND' or status = 'UNTAKE') ");
		} else if ("END".equals(state)) {
			pageParam.setQueryCondition(" and status = 'END' ");
		} else {
			pageParam.setQueryCondition(" and status in ('UNPAY', 'UNSEND', 'UNTAKE', 'END') ");
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userBean.getId());
		try {
			pageParam.setOrderByCondition("create_time desc");
			PageBean pageBean = orderFacade.listOrderPage(pageParam, paramMap);
			return new ResultBean("0000", "请求成功", pageBean);
		} catch (Exception e) {
			logger.error("listOrderPage error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 取消订单
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/order/cancelOrder")
	public ResultBean cancelOrder(Long orderId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			if (orderId == null) {
				return new ResultBean("0002", "取消订单id为空", null);
			}
			
			if (!orderFacade.cancelOrder(orderId, userBean.getId())) {
				return new ResultBean("0002", "更新订单失败", null);
			}
			
			return new ResultBean("0000", "请求成功", null);
		} catch (Exception e) {
			logger.error("cancelOrder error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 确认支付
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/order/confirmPayOrder")
	public ResultBean confirmPayOrder(Long orderId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			if (orderId == null) {
				return new ResultBean("0002", "支付订单id为空，支付失败", null);
			}
			
			if (!orderFacade.confirmPayOrder(orderId, userBean.getId())) {
				return new ResultBean("0002", "支付订单失败", null);
			}
			
			return new ResultBean("0000", "请求成功", null);
		} catch (Exception e) {
			logger.error("confirmPayOrder error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 保存购物车订单
	 * @param orderBean
	 * @param listBean
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/order/saveTrolleyOrder")
	public ResultBean saveTrolleyOrder(QyjOrderBean orderBean, QyjShoppingTrolleyListBean listBean, 
			HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			if (orderBean == null) {
				return new ResultBean("0002", "订单为空", null);
			}
			if (listBean == null || listBean.getList() == null || listBean.getList().isEmpty()) {
				return new ResultBean("0002", "订单商品为空", null);
			}
			
			orderBean.setUserId(userBean.getId());
			
			// 保存订单
			Long orderId = orderFacade.saveTrolleyOrder(orderBean, listBean.getList());
			
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
			logger.error("saveTrolleyOrder error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

}
