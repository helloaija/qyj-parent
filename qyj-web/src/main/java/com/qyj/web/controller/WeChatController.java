package com.qyj.web.controller;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qyj.common.utils.CommonEnums.UserStatusEnum;
import com.qyj.common.utils.HttpClientUtil;
import com.qyj.facade.QyjUserFacade;
import com.qyj.facade.vo.QyjUserBean;
import com.qyj.web.common.constant.CommonConstant;
import com.qyj.web.common.utils.PropertiesUtil;
import com.qyj.web.common.utils.SessionUtil;
import com.qyj.web.common.wechat.CoreService;
import com.qyj.web.common.wechat.SignUtil;

/**
 * 微信服务器数据处理
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(WeChatController.class);
	
	@Autowired
	private QyjUserFacade userFacade;

	/**
	 * 验证微信服务器消息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/process", method = RequestMethod.GET)
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 微信业务处理接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public void validPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 调用核心业务类接收消息、处理消息
		String respMessage = CoreService.processRequest(request);

		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}
	
	
	/**
	 * 微信静默登录
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/snsapiBaseLogin", method = RequestMethod.GET)
	public String snsapiBaseLogin(HttpServletRequest request, HttpServletResponse response) {
		String wechatReturn = "/weChat/page/common/wechatReturn.html";
		try {
			logger.info("snsapiBaseLogin sessionId={}", SessionUtil.getRedisKey(request, response, CommonConstant.SESSION_USER));
			QyjUserBean userBean = (QyjUserBean) SessionUtil.getUserAttr(request, response);
			if (userBean != null) {
				logger.info("snsapiBaseLogin seesion userBean={}", userBean.toString());
				return wechatReturn;
			}
			String code = request.getParameter("code");
			logger.info("snsapiBaseLogin code={},state={}", code, request.getParameter("state"));
			
			String weChatUrl = String.format("%s?appid=%s&secret=%s&code=%s&grant_type=authorization_code", 
					PropertiesUtil.getAppProperty("wechat.accessTokenUrl"), PropertiesUtil.getAppProperty("wechat.appId"), 
					PropertiesUtil.getAppProperty("wechat.appSecret"), code);
			// 获取用户openId
			String result = HttpClientUtil.get(weChatUrl);
			
			if (StringUtils.isEmpty(result)) {
				return "/weChat/page/common/wechatReturnError.html";
			}
			
			JSONObject resultJson = JSON.parseObject(result);
			
			
			// 根据手机号码查询用户
			userBean = userFacade.getUserByOpenId(resultJson.getString("openid"));
			
			logger.info("snsapiBaseLogin openid={}", resultJson.getString("openid"));
			if (userBean != null) {
				logger.info("snsapiBaseLogin userBean={}", userBean.toString());
				
				// 如果存在用户就保存登录用户到redis
				SessionUtil.setUserAttr(request, response, userBean);
			} else {
				logger.info("snsapiBaseLogin userBean={}", "null");
				Date nowDate = new Date();
				userBean = new QyjUserBean();
				userBean.setOpenId(resultJson.getString("openid"));
				userBean.setStatus(UserStatusEnum.USABLE.toString());
				userBean.setCreateTime(nowDate);
				userBean.setUpdateTime(nowDate);
				// 如果不存在就新增用户并登陆
				int insertResult = userFacade.insertUser(userBean);
				logger.info("snsapiBaseLogin insertUser insertResult={}", insertResult);
				if (insertResult != 1) {
					return "/weChat/page/common/wechatReturnError.html";
				}
				// 根据手机号码查询用户
				userBean = userFacade.getUserByOpenId(resultJson.getString("openid"));
				SessionUtil.setUserAttr(request, response, userBean);
			}
			
//			response.sendRedirect(CommonUtils.getPath(request) + "/#!/home/account");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wechatReturn;
	}

	/**
	 * 获取公众号token
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	// @RequestMapping(value = "/getAccessToken", method = RequestMethod.POST)
	// public void getAccessToken(HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// String signStr = request.getParameter("signStr");
	// String requestTime = request.getParameter("requestTime");
	// Assert.notNull(signStr);
	// Assert.notNull(requestTime);
	// log.info("getAccessToken, signStr :" + signStr + ",requestTime:" +
	// requestTime);
	// String key = "wdjorjf3435rewf";
	// String md5Text = MethodUtil.getMD5(key + requestTime, null, 0);
	//
	// if (md5Text.equals(signStr)) {
	//
	// AppJsonUtil.toJsonMsg(response, AppConstants.ZERO,
	// MethodUtil.getDES(WechatUtil.getAccessToken(PropertyUtil.getWechatPayProp("huxxilc.appId"),
	// PropertyUtil.getWechatPayProp("huxxilc.appSecret"), request), 0, key));
	// } else {
	// AppJsonUtil.toJsonMsg(response, AppConstants.FIRST, "签名错误");
	// }
	// }
}
