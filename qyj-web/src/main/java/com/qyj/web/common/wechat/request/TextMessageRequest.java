package com.qyj.web.common.wechat.request;

/**
 *  
 */
public class TextMessageRequest extends BaseMessageRequest {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
