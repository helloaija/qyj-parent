package com.qyj.store.controller.bussiness;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;
import com.qyj.common.utils.CommonEnums.OrderStatusEnum;
import com.qyj.store.common.constant.CommonConstant;
import com.qyj.store.common.util.SessionUtil;
import com.qyj.store.controller.BaseController;
import com.qyj.store.entity.QyjStockOrderEntity;
import com.qyj.store.service.QyjOrderService;
import com.qyj.store.service.QyjStockOrderService;
import com.qyj.store.vo.QyjOrderBean;
import com.qyj.store.vo.SysUserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器-订单管理
 * @author CTF_stone
 *
 */
@Controller
@RequestMapping("/admin/stockOrder")
public class QyjStockOrderController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(QyjStockOrderController.class);

	@Autowired
	private QyjStockOrderService stockOrderService;

	/**
	 * 获取进货订单分页数据信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listStockOrderPage", method = RequestMethod.GET)
	public ResultBean listStockOrderPage(HttpServletRequest request, HttpServletResponse response) {
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
			PageBean pageBean = stockOrderService.listStockOrderAndProductPage(pageParam, paramMap);
			return new ResultBean("0000", "请求成功", pageBean);
		} catch (Exception e) {
			logger.error("listNewsInfoPage error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 添加进货单
	 * @param stockOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addStockOrder", method = RequestMethod.POST)
	public ResultBean addStockOrder(QyjStockOrderEntity stockOrder, HttpServletRequest request,
									   HttpServletResponse response) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);

			if (stockOrder == null) {
				return new ResultBean("0001", "相关参数为空", null);
			}

			stockOrder.setCreateUser(userBean.getId());
			stockOrder.setUpdateUser(userBean.getId());

			return stockOrderService.addStockOrder(stockOrder);
		} catch (Exception e) {
			logger.error("addStockOrder error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 根据主键查询进货已经其商品
	 * @param stockId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getStockOrderInfo")
	public ResultBean getStockOrderInfo(Long stockId, HttpServletRequest request, HttpServletResponse response) {
		try {
			return stockOrderService.loadStockOrderAndProductByStockId(stockId);
		} catch (Exception e) {
			logger.error("getStockOrderInfo error", e);
			return new ResultBean("0001", getExceptionMessage(e), e);
		}
	}

	/**
	 * 更新进货单
	 * @param stockOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateStockOrder", method = RequestMethod.POST)
	public ResultBean updateStockOrder(QyjStockOrderEntity stockOrder, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);

			if (stockOrder == null) {
				return new ResultBean("0001", "相关参数为空", null);
			}

			stockOrder.setUpdateUser(userBean.getId());

			return stockOrderService.editStockOrder(stockOrder);
		} catch (Exception e) {
			logger.error("updateStockOrder error", e);
			return new ResultBean("0001", getExceptionMessage(e), e);
		}
	}

	/**
	 * 删除进货单
	 * @param stockId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteStockOrder", method = RequestMethod.POST)
	public ResultBean deleteStockOrder(Long stockId, HttpServletRequest request, HttpServletResponse response) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
			logger.info("deleteStockOrder userId = {}, stockId = {}", userBean.getId(), stockId);

			if (stockId == null) {
				return new ResultBean("0002", "相关参数为空", null);
			}

			return stockOrderService.deleteStockOrder(stockId);
		} catch (Exception e) {
			logger.error("deleteStockOrder error", e);
			return new ResultBean("0001", getExceptionMessage(e), e);
		}
	}
}
