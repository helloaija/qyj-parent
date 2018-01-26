package com.qyj.web.common.wechat.menuUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * 公众平台通用接口工具类
 * @author lirui
 * @date 2014年7月21日 下午3:07:57
 */
public class WeixinUtil {
	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			// log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			// log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// public final static String jsapi_ticket_url =
	// "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	/**
	 * 获取access_token
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败 , jsonObject.getInt("errcode"),
				// jsonObject.getString("errmsg")
				System.out.println("获取token失败 errcode:{} errmsg:{}");
			}
		}
		return accessToken;
	}

	/**
	 * 获取jsticket
	 * @return
	 */
	// public static String getJsTicket(String token) {
	// try {
	// String url = jsapi_ticket_url.replace("ACCESS_TOKEN", token);
	// JSONObject jsonObject = httpRequest(url, "GET", null);
	// if (null != jsonObject) {
	// return jsonObject.getString("ticket");
	// } else {
	// return null;
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// return null;
	// }
	// }

	/**
	 * 获取jsapi相关参数map
	 * @param jsapi_ticket
	 * @param url
	 * @return
	 */
	// public static Map<String, String> getJsSignMap(String jsapi_ticket,
	// String url) {
	// Map<String, String> map = new HashMap<String, String>();
	//
	// String nonce_str = UUID.randomUUID().toString();
	// String timestamp = Long.toString(System.currentTimeMillis() / 1000);
	// String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" +
	// nonce_str + "&timestamp=" + timestamp
	// + "&url=" + url;
	//
	// MessageDigest crypt = null;
	// String signature = null;
	// try {
	// crypt = MessageDigest.getInstance("SHA-1");
	// byte[] digest = crypt.digest(string1.toString().getBytes());
	// signature = SignUtil.byteToStr(digest);
	// } catch (NoSuchAlgorithmException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// map.put("url", url);
	// map.put("jsapi_ticket", jsapi_ticket);
	// map.put("nonceStr", nonce_str);
	// map.put("timestamp", timestamp);
	// map.put("signature", signature);
	//
	// return map;
	// }

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;
		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = com.alibaba.fastjson.JSONObject.toJSONString(menu);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getIntValue("errcode")) {
				result = jsonObject.getIntValue("errcode");
				// 创建菜单失败, jsonObject.getInt("errcode"),
				// jsonObject.getString("errmsg")
				System.out.println("创建菜单失败 ");
			}
		}
		return result;
	}

	/**
	 * 执行微信线程
	 * @throws InterruptedException
	 */
	// public static void runWeixinThread() {
	// ExecutorService executor = Executors.newCachedThreadPool();
	// executor.execute(new Runnable() {
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// CommonConstant.accessToken = getAccessToken("wx3830cbb607084fc5",
	// "f23108c1cc45fd4d9d23229be93de10a");
	// CommonConstant.WECHAT_JSTICKET =
	// getJsTicket(CommonConstant.accessToken.getToken());
	//
	// try {
	// Thread.sleep(7000 * 1000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// });
	// }
}