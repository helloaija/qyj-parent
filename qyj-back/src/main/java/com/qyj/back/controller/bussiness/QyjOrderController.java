package com.qyj.back.controller.bussiness;

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

import com.qyj.back.common.constant.CommonConstant;
import com.qyj.back.common.enums.CommonEnums.NewsStatusEnum;
import com.qyj.back.common.util.SessionUtil;
import com.qyj.back.controller.BaseController;
import com.qyj.back.entity.QyjNewsInfoEntity;
import com.qyj.back.service.QyjOrderService;
import com.qyj.back.vo.QyjOrderBean;
import com.qyj.back.vo.SysUserBean;
import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;

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
	 * 插入新闻公告信息
	 * @param NewsInfoId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveNewsInfo")
	public ResultBean saveNewsInfo(QyjNewsInfoEntity newsInfoEntity, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
			
			Date nowDate = new Date();
			// id为空插入数据
			if (newsInfoEntity.getId() == null || newsInfoEntity.getId() == 0) {
				newsInfoEntity.setCreateTime(nowDate);
				newsInfoEntity.setCreateUser(userBean.getId());
				newsInfoEntity.setVisitCount(0);
				// 未发布状态
				newsInfoEntity.setNewsStatus(NewsStatusEnum.PUBLISH.toString());
				int insertResult = newsInfoService.insert(newsInfoEntity);
				logger.info("saveNewsInfoInfo insert NewsInfoEntity, info={}, result={}", newsInfoEntity.toString(),
						insertResult);
				if (insertResult == 1) {
					return new ResultBean("0000", "新增新闻公告信息成功", insertResult);
				}
				return new ResultBean("0002", "新增新闻公告信息失败", insertResult);
			}

			// 编辑更新数据
			int updateResult = newsInfoService.updateByPrimaryKey(newsInfoEntity);
			logger.info("saveNewsInfoInfo update newsInfoEntity, info={}, result={}", newsInfoEntity.toString(),
					updateResult);
			if (updateResult == 1) {
				return new ResultBean("0000", "更新新闻公告信息成功", updateResult);
			}
			return new ResultBean("0002", "更新新闻公告信息失败", updateResult);
		} catch (Exception e) {
			logger.error("saveNewsInfoInfo error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

	/**
	 * 根据主键删除新闻公告信息
	 * @param newsInfoId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delNewsInfo")
	public ResultBean delNewsInfo(Long newsInfoId, HttpServletRequest request, HttpServletResponse response) {
		try {
			int deleteResult = newsInfoService.deleteByPrimaryKey(newsInfoId);
			if (deleteResult == 1) {
				return new ResultBean("0000", "删除新闻公告信息成功", deleteResult);
			}
			return new ResultBean("0002", "删除新闻公告信息失败", deleteResult);
		} catch (Exception e) {
			logger.error("delNewsInfoInfo error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 更新新闻状态
	 * @param newsId 产品id
	 * @param newsStatus 产品状态
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateNewsStatus")
	public ResultBean updateNewsStatus(Long newsId, String newsStatus, HttpServletResponse response, HttpServletRequest request) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
			Boolean result = newsInfoService.updateNewsStatus(userBean, newsId, newsStatus);
			if (result) {
				return new ResultBean("0000", "成功", null);
			}
			return new ResultBean("0002", "更新状态失败", null);
		} catch (Exception e) {
			logger.error("updateNewsStatus error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
}