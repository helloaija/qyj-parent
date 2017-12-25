package com.qyj.back.common.util;

import java.io.File;

public class FileUtils {

	/**
	 * 获取上传文件目录
	 * @return
	 */
	public static Boolean mkDirs(String path) {
		File uploadFileDir = new File(path);
		if (!uploadFileDir.exists()) {
			return uploadFileDir.mkdirs();
		}
		return Boolean.TRUE;
	}
}
