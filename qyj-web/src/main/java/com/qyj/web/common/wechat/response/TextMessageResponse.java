package com.qyj.web.common.wechat.response;

/**
 * 文本消息
 */
public class TextMessageResponse extends BaseMessageResponse {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
