package com.qyj.store.controller.bussiness;

import java.math.BigDecimal;
import java.util.Date;
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

import com.qyj.store.common.constant.CommonConstant;
import com.qyj.store.common.util.SessionUtil;
import com.qyj.store.controller.BaseController;
import com.qyj.store.service.QyjOrderService;
import com.qyj.store.vo.QyjOrderBean;
import com.qyj.store.vo.SysUserBean;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;
import com.qyj.common.utils.CommonEnums.OrderStatusEnum;

/**
 * 控制器-订单管理
 * @author CTF_stone
 *
 */
@Controller
@RequestMapping("/admin/order")
public class QyjOrderController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(QyjOrderController.class);

	@Autowired
	private QyjOrderService orderService;

	/**
	 * 获取订单分页数据信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listOrderPage")
	public ResultBean listOrderPage(HttpServletRequest request, HttpServletResponse response) {
		PageParam pageParam = this.initPageParam(request);
		// 订单状态
		String status = request.getParameter("status");
		// 订单编号
		String orderNumber = request.getParameter("orderNumber");
		// 创建开始时间
		String createTimeBegin = request.getParameter("createTimeBegin");
		// 创建结束时间
		String createTimeEnd = request.getParameter("createTimeEnd");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("likeOrderNumber", orderNumber);
		paramMap.put("createTimeBegin", createTimeBegin);
		paramMap.put("createTimeEnd", createTimeEnd);
		try {
			pageParam.setOrderByCondition("create_time desc");
			PageBean pageBean = orderService.listOrderPage(pageParam, paramMap);
			return new ResultBean("0000", "请求成功", pageBean);
		} catch (Exception e) {
			logger.error("listNewsInfoPage error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 根据主键查询订单已经其商品
	 * @param orderId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOrderAndGoods")
	public ResultBean getOrderAndGoods(Long orderId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjOrderBean queryBean = new QyjOrderBean();
			queryBean.setId(orderId);
			List<QyjOrderBean> orderBeanList = orderService.listOrderAndGoodsByModel(queryBean);
			if (orderBeanList == null || orderBeanList.isEmpty()) {
				return new ResultBean("0002", "没有找到订单" + orderId + "信息", null);
			}
			return new ResultBean("0000", "请求成功", orderBeanList.get(0));
		} catch (Exception e) {
			logger.error("getOrderAndGoods error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 更新订单价格
	 * @param orderId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateOrderPrice")
	public ResultBean updateOrderPrice(Long orderId, BigDecimal modifyAmount, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
			if (userBean == null) {
				return new ResultBean("0002", "请先登陆", null);
			}
			
			if (orderId == null || modifyAmount == null) {
				return new ResultBean("0002", "相关参数为空", null);
			}
			
			// 查询订单信息并判断是否能够修改价格
			QyjOrderBean orderBean = orderService.getOrderById(orderId);
			if (orderBean == null) {
				return new ResultBean("0002", "没有找到订单" + orderId, null);
			}
			if (!OrderStatusEnum.UNPAY.toString().equals(orderBean.getStatus())) {
				return new ResultBean("0002", "订单" + orderId + "不是未支付状态", null);
			}
			
			QyjOrderBean updateBean = new QyjOrderBean();
			updateBean.setId(orderId);
			updateBean.setModifyAmount(modifyAmount);
			updateBean.setUpdateTime(new Date());
			// 更新订单金额
			if (orderService.updateOrder(updateBean) <= 0) {
				return new ResultBean("0002", "订单" + orderId + "更新价格失败", null);
			}
			
			return new ResultBean("0000", "订单价格调整成功", null);
		} catch (Exception e) {
			logger.error("updateOrderPrice error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

}
