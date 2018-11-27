package com.qyj.store.controller.bussiness;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;
import com.qyj.store.common.constant.CommonConstant;
import com.qyj.store.common.util.SessionUtil;
import com.qyj.store.controller.BaseController;
import com.qyj.store.entity.QyjSellOrderEntity;
import com.qyj.store.service.QyjSellOrderService;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 控制器-订单管理
 * @author CTF_stone
 *
 */
@Controller
@RequestMapping("/admin/sellOrder")
public class QyjSellOrderController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(QyjSellOrderController.class);

	@Autowired
	private QyjSellOrderService sellOrderService;

	/**
	 * 获取销售订单分页数据信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listSellOrderPage", method = RequestMethod.GET)
	public ResultBean listSellOrderPage(HttpServletRequest request, HttpServletResponse response) {
		PageParam pageParam = this.initPageParam(request);
		// 订单状态
		String status = request.getParameter("orderStatus");
		// 订单编号
		String orderNumber = request.getParameter("orderNumber");
		// 创建开始时间
		String createTimeBegin = request.getParameter("createTimeBegin");
		// 创建结束时间
		String createTimeEnd = request.getParameter("createTimeEnd");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderStatus", status);
		paramMap.put("likeOrderNumber", orderNumber);
		paramMap.put("createTimeBegin", createTimeBegin);
		paramMap.put("createTimeEnd", createTimeEnd);
		try {
			pageParam.setOrderByCondition("create_time desc");
			PageBean pageBean = sellOrderService.listSellOrderAndProductPage(pageParam, paramMap);
			return new ResultBean("0000", "请求成功", pageBean);
		} catch (Exception e) {
			logger.error("listNewsInfoPage error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 添加销售单
	 * @param sellOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addSellOrder", method = RequestMethod.POST)
	public ResultBean addSellOrder(QyjSellOrderEntity sellOrder, HttpServletRequest request,
								   HttpServletResponse response) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);

			if (sellOrder == null) {
				return new ResultBean("0001", "相关参数为空", null);
			}

			sellOrder.setCreateUser(userBean.getId());
			sellOrder.setUpdateUser(userBean.getId());

			return sellOrderService.addSellOrder(sellOrder);
		} catch (Exception e) {
			logger.error("addSellOrder error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 根据主键查询销售已经其商品
	 * @param sellId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSellOrderInfo")
	public ResultBean getSellOrderInfo(Long sellId, HttpServletRequest request, HttpServletResponse response) {
		try {
			return sellOrderService.loadSellOrderAndProductBySellId(sellId);
		} catch (Exception e) {
			logger.error("getSellOrderInfo error", e);
			return new ResultBean("0001", getExceptionMessage(e), e);
		}
	}

	/**
	 * 更新销售单
	 * @param sellOrder
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateSellOrder", method = RequestMethod.POST)
	public ResultBean updateSellOrder(QyjSellOrderEntity sellOrder, HttpServletRequest request,
									  HttpServletResponse response) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);

			if (sellOrder == null) {
				return new ResultBean("0001", "相关参数为空", null);
			}

			sellOrder.setUpdateUser(userBean.getId());

			return sellOrderService.editSellOrder(sellOrder);
		} catch (Exception e) {
			logger.error("updateSellOrder error", e);
			return new ResultBean("0001", getExceptionMessage(e), e);
		}
	}

	/**
	 * 删除销售单
	 * @param sellId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteSellOrder", method = RequestMethod.POST)
	public ResultBean deleteSellOrder(Long sellId, HttpServletRequest request, HttpServletResponse response) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
			logger.info("deleteSellOrder userId = {}, SellId = {}", userBean.getId(), sellId);

			if (sellId == null) {
				return new ResultBean("0002", "相关参数为空", null);
			}

			return sellOrderService.deleteSellOrder(sellId);
		} catch (Exception e) {
			logger.error("deleteSellOrder error", e);
			return new ResultBean("0001", getExceptionMessage(e), e);
		}
	}
}
