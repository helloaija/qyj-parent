package com.qyj.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyj.common.page.ResultBean;
import com.qyj.facade.QyjUserFacade;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.utils.SessionUtil;

@Controller
@RequestMapping("/wechat")
public class QyjAccountController {
	private static final Logger logger = LoggerFactory.getLogger(QyjAccountController.class);
	
	@Autowired
	private QyjUserFacade userFacade;
	
	/**
	 * 获取登录用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/restrict/account/getLoginUserInfo")
	public ResultBean getLoginUserInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			QyjUserBean userBean = SessionUtil.getUserAttr(request, response);
			if (userBean == null) {
				return new ResultBean("0002", "用户未登录", null);
			}
			
			// 返回页面的部分用户信息，用户密码等信息不能返回
			QyjUserBean returnUserBean = new QyjUserBean();
			returnUserBean.setId(userBean.getId());
			returnUserBean.setPhoneNum(userBean.getPhoneNum());
			returnUserBean.setUserName(userBean.getUserName());
			
			return new ResultBean("0000", "获取用户信息成功", returnUserBean);
		} catch (Exception e) {
			logger.error("getLoginUser error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
}
