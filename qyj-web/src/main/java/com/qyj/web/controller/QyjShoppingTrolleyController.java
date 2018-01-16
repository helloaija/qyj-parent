package com.qyj.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyj.common.page.ResultBean;
import com.qyj.facade.QyjShoppingTrolleyFacade;
import com.qyj.facade.vo.QyjShoppingTrolleyBean;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.utils.SessionUtil;

/**
 * 控制器 - 购物车
 * @author CTF_stone
 *
 */
@Controller
@RequestMapping("/wechat")
public class QyjShoppingTrolleyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(QyjProductController.class);
	
	@Autowired
	private QyjShoppingTrolleyFacade shoppingTrolleyFacade;
	
	/**
	 * 添加购物车记录
	 * @param productId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/shoppingTrolley/addShoppingTrolley")
	public ResultBean addShoppingTrolley(Long productId, HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserStrr(request);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			QyjShoppingTrolleyBean bean = new QyjShoppingTrolleyBean();
			bean.setUserId(userBean.getId());
			bean.setProductId(productId);
			bean.setNumber(1);
			bean.setCreateTime(new Date());
			
			if (shoppingTrolleyFacade.addShoppingTrolley(bean) != null) {
				return new ResultBean("0000", "添加购物车成功", null);
			}
			
			return new ResultBean("0002", "添加购物车失败", null);
		} catch (Exception e) {
			logger.error("addShoppingTrolley error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}

}
