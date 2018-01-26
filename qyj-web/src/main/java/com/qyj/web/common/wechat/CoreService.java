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
			String respContent = "hi，亲亲你来啦[玫瑰]！~ \n我这么低调都能被你关注，你真是太有才了！~(?ω?)\n网络一线牵，珍惜这段缘[愉快]\n\n这里有每日热点，不定期福利，理财经验分享~\n不准取关哦\n\n如果你是新人，享200元红包壕礼[玫瑰]\n<a href=\"https://www.huilc.cn/wxfront/noviceBorrow?utm=xialacaidan\">点这里</a>[嘴唇]\n\n更多资讯：\n1、续投返现0.2%不变[玫瑰][调皮]\n<a href=\"https://www.huilc.cn/nWeChat/toActivityNoticeView?pageType=0\">点这里</a> [嘴唇]\n\n2、天天抽奖、红包任性送[玫瑰][嘿哈]\n<a href=\"https://www.huilc.cn/wxfront/draw/toLuckDraw?utm=shouye\">点这里</a> [嘴唇]\n\n有疑问或建议请直接，\n拨打客服热线：400-966-0198[调皮]";

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