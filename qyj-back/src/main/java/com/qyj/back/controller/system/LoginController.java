package com.qyj.back.controller.system;

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

import com.qyj.back.common.util.SessionUtil;
import com.qyj.back.common.util.Utils;
import com.qyj.back.entity.SysUserModel;
import com.qyj.back.service.SysUserService;
import com.qyj.back.vo.SysUserBean;
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
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("/toLoginPage")
	public String toLoginPage(HttpServletRequest request, HttpServletResponse response) {
		return "/page/common/login";
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
}
