package com.qyj.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.utils.SessionUtil;

@Controller
@RequestMapping("/wechat")
public class QyjOrderController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(QyjOrderController.class);

	@Autowired
	private QyjOrderFacade orderFacade;

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

}
