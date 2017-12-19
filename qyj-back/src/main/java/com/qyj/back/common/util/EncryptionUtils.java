package com.qyj.back.common.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;

/**
 * 密码工具类
 * @author shitongle
 */
public class EncryptionUtils {

	/**
	 * 系统DES密钥相关常量
	 */
	public static final String DES_KEY_DEFAULT = "";

	private static final byte[] DES_KEY_TYTE = { 21, 1, -110, 82, -32, -85, -128, -65 };

	/**
	 * 利用MD5加密算法加密字符
	 * @param str 明文
	 * @param encode 编码格式
	 * @param lower_upper_case 0：不分大小写，1：大写，2：小写
	 * @return
	 */
	public static String getMD5(String str, String encode, int lower_upper_case) {
		if (null == encode || "".equals(encode) || "".equals(encode.trim())) {
			encode = "utf-8";
		}

		StringBuilder sb = new StringBuilder();
		try {
			// MD5加密
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] disByteArr = digest.digest(str.getBytes(encode));

			for (int i = 0; i < disByteArr.length; i++) {
				// 转为十六进制
				String byteStr = Integer.toHexString((disByteArr[i] & 0xff) | 0x100).substring(1, 3);
				sb.append(byteStr);
			}

			switch (lower_upper_case) {
			// 不分大小写
			case 0:
				return sb.toString();
			// 大写
			case 1:
				return sb.toString().toUpperCase();
			// 小写
			case 2:
				return sb.toString().toLowerCase();
			}
		} catch (Exception e) {
			return "";
		}

		return sb.toString();
	}

	/**
	 * 获取DES密文、明文
	 * @param message
	 * @param en_or_de 0：加密，1：解密
	 * @return
	 */
	public static String getDES(String message, int en_or_de) {
		if (0 == en_or_de) {
			return getEncodeDES(message);
		}
		if (1 == en_or_de) {
			return getDecodeDES(message);
		}

		return null;
	}

	/**
	 * DES加密字符窜
	 * @param message
	 * @return
	 */
	public static String getEncodeDES(String message) {
		String result = null;
		try {
			SecureRandom random = new SecureRandom();
			// DESKeySpec keySpec = new DESKeySpec(DES_KEY_TYTE);
			//
			// SecretKeyFactory keyFactory =
			// SecretKeyFactory.getInstance("DES");
			// Key key = keyFactory.generateSecret(keySpec);

			Key key = getKey();

			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, random);

			result = new sun.misc.BASE64Encoder().encode(cipher.doFinal(message.getBytes()));
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	/**
	 * DES解密字符串
	 * @param message
	 * @return
	 */
	public static String getDecodeDES(String message) {
		String result = null;
		try {
			SecureRandom random = new SecureRandom();
			// DESKeySpec keySpec = new DESKeySpec(DES_KEY_TYTE);
			//
			// SecretKeyFactory keyFactory =
			// SecretKeyFactory.getInstance("DES");
			// Key key = keyFactory.generateSecret(keySpec);

			Key key = getKey();

			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, random);

			result = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(message)));
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	private static Key getKey() throws Exception {
		return getKey("123456".getBytes("UTF-8"));
	}

	/**
	 * 获取秘钥
	 * @param arrBTmp
	 * @return
	 * @throws Exception
	 */
	private static Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组
		byte[] arrB = new byte[8];
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) { // 将原始字节数组转换为8位
			arrB[i] = arrBTmp[i];
		}
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");// 生成密钥
		return key;
	}

	public static void main(String[] args) {
		// String str = EncryptionUtils.getMD5("123456", null, 0);

		String str = getDES("123456", 0);

		System.out.println(str);

		str = getDES(str, 1);

		System.out.println(str);
	}
}
