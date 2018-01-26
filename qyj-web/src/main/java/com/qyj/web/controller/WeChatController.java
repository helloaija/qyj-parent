package com.qyj.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qyj.web.common.wechat.CoreService;
import com.qyj.web.common.wechat.SignUtil;

/**
 * 微信服务器数据处理
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController extends BaseController {

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
	// MethodUtil.getDES(WechatUtil.getAccessToken(PropertyUtil.getWechatPayProp("huilc.appId"),
	// PropertyUtil.getWechatPayProp("huilc.appSecret"), request), 0, key));
	// } else {
	// AppJsonUtil.toJsonMsg(response, AppConstants.FIRST, "签名错误");
	// }
	// }
}
