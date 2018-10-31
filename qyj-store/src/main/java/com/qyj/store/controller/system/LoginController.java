package com.qyj.store.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyj.store.common.constant.CommonConstant;
import com.qyj.store.common.util.SessionUtil;
import com.qyj.store.common.util.Utils;
import com.qyj.store.entity.SysUserModel;
import com.qyj.store.service.SysUserService;
import com.qyj.store.vo.SysUserBean;
import com.qyj.common.page.ResultBean;

/**
 * 登录跳转控制器
 * @author shitongle
 */
@Controller
@RequestMapping("/admin/login")
public class LoginController {

	// 系统用户service
	@Autowired
	private SysUserService sysUserService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 跳转到主页面
	 * @param request
	 * @param response
	 */
	@RequestMapping("/toIndexPage")
	public String toIndexPage(HttpServletRequest request, HttpServletResponse response) {
		return "/WEB-INF/page/index";
	}

	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @param verifyCode
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doLogin")
	public ResultBean doLogin(String userName, String password, String verifyCode,
			HttpServletRequest request, HttpServletResponse response) {
		try {
//			if (StringUtils.isEmpty(verifyCode)) {
//				throw new Exception("验证码不能为空！");
//			}

//			String sysVerifyCode = SessionUtil.getVerifyCode(request);
//			if (!verifyCode.equalsIgnoreCase(sysVerifyCode)) {
//				throw new Exception("验证码不正确！");
//			}

			List<SysUserModel> userList = sysUserService.queryUserByNameAndPsw(userName, password);

			if (Utils.isEmptyCollection(userList)) {
				throw new Exception("用户名或密码错误！");
			}

			SysUserBean userBean = new SysUserBean();
			BeanUtils.copyProperties(userList.get(0), userBean);
			SessionUtil.setUserSession(request, userBean);
			
			return new ResultBean("0000", "用户名密码验证通过", null); 

		} catch (Exception e) {
			logger.error("doLogin error", e);
			return new ResultBean("0001", e.getMessage(), null); 
		}
	}
	
	/**
	 * 获取登录信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLoginInfo")
	public ResultBean getLoginInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			SysUserBean userBean = (SysUserBean) SessionUtil.getAttribute(request, CommonConstant.SESSION_USER);
			if (userBean == null) {
				return new ResultBean("0002", "未登录", null); 
			}
			return new ResultBean("0000", "请求成功", userBean); 
		} catch (Exception e) {
			logger.error("getLoginInfo error", e);
			return new ResultBean("0001", e.getMessage(), null); 
		}
	}
}
