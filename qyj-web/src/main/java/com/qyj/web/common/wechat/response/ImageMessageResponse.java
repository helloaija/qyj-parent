package com.qyj.web.common.wechat.response;

/**
 * 文本消息
 */
public class ImageMessageResponse extends BaseMessageResponse {
	// 回复的消息内容
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image Image) {
		this.Image = Image;
	}
}
