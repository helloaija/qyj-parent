package com.qyj.store.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class IOUtils {
	
	public static void loadFileByByte(String url, byte[] byteArray) throws Exception {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(url));
			bos.write(byteArray);
			bos.flush();
		} finally {
			if (bos != null) {
				bos.close();
			}
		}
	}
	
	public static byte[] getByteFromStream(InputStream inputStream) throws Exception {
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		try {
			bis = new BufferedInputStream(inputStream);
			baos = new ByteArrayOutputStream();
			byte[] byteArr = new byte[100];
			int len = bis.read(byteArr);
			while (len != -1) {
				baos.write(byteArr, 0, len);
				len = bis.read(byteArr);
			}
			baos.flush();
		} finally {
			baos.close();
			bis.close();
		}
		
		return baos.toByteArray();
	}
}
