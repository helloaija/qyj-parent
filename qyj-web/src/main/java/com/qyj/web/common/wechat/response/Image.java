package com.qyj.web.common.wechat.response;

/**
 * 音乐model
 */
public class Image {

	private String MediaId;

	public Image(String MediaId) {
		this.MediaId = MediaId;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String MediaId) {
		this.MediaId = MediaId;
	}
}
