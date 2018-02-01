package com.qyj.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.qyj.common.page.ResultBean;
import com.qyj.common.utils.CommonUtils;
import com.qyj.common.utils.EncryptionUtils;
import com.qyj.common.utils.CommonEnums.UserStatusEnum;
import com.qyj.facade.QyjUserFacade;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.constant.CommonConstant;
import com.qyj.web.common.dysms.SmsUtil;
import com.qyj.web.common.utils.SessionUtil;

@Controller
@RequestMapping("/wechat")
public class QyjLoginController {
	private static final Logger logger = LoggerFactory.getLogger(QyjLoginController.class);
	
	@Autowired
	private QyjUserFacade userFacade;
	
	/**
	 * 用户登录
	 * @param phoneNum 手机号码
	 * @param loginCode 短信验证码
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping("/freedom/login/doLogin")
	public ResultBean doLogin(String phoneNum, String loginCode, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isEmpty(phoneNum)) {
			return new ResultBean("0002", "请输入手机号码！", null);
		}
		if (StringUtils.isEmpty(loginCode)) {
			return new ResultBean("0002", "请输入验证码！", null);
		}
		
		// 获取最后一次发送的短信验证码信息，包括手机号码、验证码
		Map<String, String> loginCodeMap = (Map<String, String>) SessionUtil.getObject(request, response, CommonConstant.SESSION_LOGIN_CODE);
		if (loginCodeMap == null || loginCodeMap.isEmpty()) {
			return new ResultBean("0002", "请获取验证码！", null);
		}
		
		if (!phoneNum.equals(loginCodeMap.get("phoneNum"))) {
			return new ResultBean("0002", "获取验证码的手机号和输入的手机号码不一致！", null);
		}
		if (!loginCode.equals(loginCodeMap.get("loginCode"))) {
			return new ResultBean("0002", "验证码不正确！", null);
		}
		
		try {
			// 根据手机号码查询用户
			QyjUserBean userBean = userFacade.getUserByPhoneNum(phoneNum);
			
			if (userBean == null) {
				Date nowDate = new Date();
				userBean = new QyjUserBean();
				userBean.setPhoneNum(phoneNum);	
				userBean.setStatus(UserStatusEnum.USABLE.toString());
				userBean.setCreateTime(nowDate);
				userBean.setUpdateTime(nowDate);
				// 如果不存在就新增用户并登陆
				int insertResult = userFacade.insertUser(userBean);
				logger.info("doLogin insertUser insertResult={}", insertResult);
				if (insertResult != 1) {
					return new ResultBean("0002", "保存登录信息失败！", null);
				}
				// 根据手机号码查询用户
				userBean = userFacade.getUserByPhoneNum(phoneNum);
			}
			
			// 保存登录用户到redis
			SessionUtil.setUserAttr(request, response, userBean);
			
			return new ResultBean("0000", "登录成功", null);
		} catch (Exception e) {
			logger.error("doLogin error", e);
			return new ResultBean("0001", "请求异常:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 获取登录短信验证码
	 * @param phoneNo
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping("/freedom/login/getLoginCode")
	public ResultBean getLoginCode(String phoneNum, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 登录验证码信息，包括手机号码、验证码
			Map<String, String> loginCodeMap = null;
			// 获取最后一次发送的短信验证码
			loginCodeMap = (Map<String, String>) SessionUtil.getObject(request, response, CommonConstant.SESSION_LOGIN_CODE);
			// 如果最后一次发送的短信验证码还在，说明两次操作没有超过三分钟
			if (loginCodeMap != null) {
				return new ResultBean("0002", "请在三分钟之后再获取验证码", null);
			}
			
			// 随机生成验证码
			int code = CommonUtils.getRandom(6);
			SendSmsResponse smsResponse = SmsUtil.sendSms(SmsUtil.TEMPLATE_CODE_LOGIN, "{\"code\":\"" + code + "\"}", phoneNum);
			logger.info("getLoginCode send msg,phoneNum={},code={},reponseCode={},responseMsg={}", 
					phoneNum, code, smsResponse.getCode(), smsResponse.getMessage());
			if (!"OK".equals(smsResponse.getCode().toUpperCase())) {
				return new ResultBean("0002", "发送短信失败:" + smsResponse.getMessage(), null);
			}
			
			loginCodeMap = new HashMap<String, String>();
			loginCodeMap.put("phoneNum", phoneNum);
			loginCodeMap.put("loginCode", String.valueOf(code));
			// 保存验证码到redis，三分钟
			SessionUtil.setObjectAttr(request, response, CommonConstant.SESSION_LOGIN_CODE, loginCodeMap, 3 * 60L, TimeUnit.SECONDS);
			return new ResultBean("0000", "发送短信成功", null);
		} catch (Exception e) {
			logger.error("getLoginCode error", e);
			return new ResultBean("0001", "获取验证码异常:" + e.getMessage(), e);
		}
	}
}
