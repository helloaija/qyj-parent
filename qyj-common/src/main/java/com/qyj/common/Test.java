package com.qyj.common;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;

import com.qyj.common.utils.EncryptionUtils;
import com.qyj.common.utils.HttpClientUtil;

public class Test {

	public static void main(String[] args) {
//		System.out.println(StringUtils.isNumeric("012335621623634276473"));
//		System.out.println(Integer.valueOf("0001"));
//		System.err.println(EncryptionUtils.getMD5("123456"));
		
		String weChatUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=wx1957904a2993fb75&redirect_uri=http%3A%2F%2F39.108.108.147/wechat/snsapiBaseLogin"
				+ "&response_type=code&scope=snsapi_base&state=673646agdggafaggg#wechat_redirect";
		try {
			System.out.println(HttpClientUtil.get(weChatUrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
