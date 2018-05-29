package com.example.wangchong.mvpdemo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DigestUtils {
	private static final String SIGN_KEY = "MhxzKhl";
	public static String getMD5(String src) {
		MessageDigest digester = null;
		try {
			digester = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] srcBytes = null;
		try {
			srcBytes = src.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		digester.update(srcBytes);
		byte[] digest = digester.digest();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			StringBuilder tem = new StringBuilder(
					Integer.toHexString(0xFF & digest[i]));
			sb.append(tem.length() > 1 ? tem : tem.insert(0, "0"));
		}

		return sb.toString().toLowerCase();
	}
	
	public static String makeVeriSign(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder prestr = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			if(key.endsWith("ImgUrl")){
				continue;
			}
			String value = params.get(key);

			if (isJsonArrayString(value))
				continue;
			if(value == null)
				value = "";

			if (i == keys.size() - 1) {
				prestr.append(key).append("=").append(value);
			} else {
				prestr.append(key).append("=").append(value).append("&");
			}
		}
		return DigestUtils.getMD5(prestr.append("&").append(SIGN_KEY)
				.toString());
	}
	
	public static boolean isJsonArrayString(String str) {
		if(str == null)
			return false;
		return str.startsWith("[]") && str.length() >= 4;
	}
	
	
	
}
