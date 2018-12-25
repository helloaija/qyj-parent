package com.qyj.web.common.wechat;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qyj.web.common.wechat.response.TextMessageResponse;

/**
 * 核心服务类
 * @author CTF_stone
 */
public class CoreService {

	protected final static Logger log = LoggerFactory.getLogger(CoreService.class);

	/**
	 * 处理微信发来的请求 换行符仍然是"\n"
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "hi，亲亲你来啦[玫瑰]！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			log.info("processRequest requestMap:{}", requestMap);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessageResponse textMessage = new TextMessageResponse();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
			
			log.info("wechat reponse EventKey:" + requestMap.get("EventKey"));
		} catch (Exception e) {
			log.error("processRequest error", e);
		}

		return respMessage;
	}
}
