package com.qyj.web.common.wechat.request;

/**
 */
public class ImageMessageRequest extends BaseMessageRequest {
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}