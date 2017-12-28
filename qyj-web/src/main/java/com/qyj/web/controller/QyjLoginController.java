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
import com.qyj.common.utils.EncryptionUtils;
import com.qyj.facade.QyjUserFacade;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.utils.SessionUtil;

@Controller
@RequestMapping("/wechat")
public class QyjLoginController {
	private static final Logger logger = LoggerFactory.getLogger(QyjLoginController.class);
	
	@Autowired
	private QyjUserFacade userFacade;
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/freedom/login/doLogin")
	public ResultBean doLogin(HttpServletRequest request, HttpServletResponse response) {
		String phoneNum = request.getParameter("phoneNum");
		if (StringUtils.isEmpty(phoneNum)) {
			return new ResultBean("0002", "请输入用户名！", null);
		}
		String password = request.getParameter("password");
		if (StringUtils.isEmpty(password)) {
			return new ResultBean("0002", "请输入密码！", null);
		}
		
		try {
			// 根据手机号码查询用户
			QyjUserBean userBean = userFacade.getUserByPhoneNum(phoneNum);
			
			if (userBean == null) {
				return new ResultBean("0002", "手机号码" + phoneNum + "还没有注册！", null);
			}
			if (!EncryptionUtils.getMD5(password).equals(userBean.getPassword())) {
				return new ResultBean("0002", "登录密码不正确！", null);
			}
			
			// 保存登录用户到redis
			SessionUtil.setUserSttr(request, userBean);
			
			return new ResultBean("0000", "登录成功", null);
		} catch (Exception e) {
			logger.error("doLogin error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
}
