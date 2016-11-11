package com.wangzhixuan.commons.utils;

import java.nio.charset.Charset;

/**
 * Base64工具类
 * @author L.cm
 */
public class Base64Utils extends org.springframework.util.Base64Utils {
	/**
	 * 编码
	 * @param value 字符串
	 * @return {String}
	 */
	public static String encode(String value) {
		return Base64Utils.encode(value, Charsets.UTF_8);
	}
	
	/**
	 * 编码
	 * @param value 字符串
	 * @param charsetName charSet
	 * @return {String}
	 */
	public static String encode(String value, Charset charset) {
		byte[] val = value.getBytes(charset);
		return Base64Utils.encodeToString(val);
	}
	
	/**
	 * url编码
	 * @param value 字符串
	 * @return {String}
	 */
	public static String encodeUrlSafe(String value) {
		return Base64Utils.encodeUrlSafe(value, Charsets.UTF_8);
	}
	
	/**
	 * url编码
	 * @param value 字符串
	 * @param charsetName charSet
	 * @return {String}
	 */
	public static String encodeUrlSafe(String value, Charset charset) {
		byte[] val = value.getBytes(charset);
		return Base64Utils.encodeToUrlSafeString(val);
	}
	
	/**
	 * 解码
	 * @param value 字符串
	 * @return {String}
	 */
	public static String decode(String value, Charset charset) {
		byte[] decodedValue = Base64Utils.decodeFromString(value);
		return new String(decodedValue, charset);
	}
	
	/**
	 * 解码
	 * @param value 字符串
	 * @param charsetName 字符集
	 * @return {String}
	 */
	public static String decode(String value) {
		return Base64Utils.decode(value, Charsets.UTF_8);
	}

	/**
	 * URL解码
	 * @param value 字符串
	 * @return {String}
	 */
	public static String decodeUrlSafe(String value, Charset charset) {
		byte[] decodedValue = Base64Utils.decodeFromUrlSafeString(value);
		return new String(decodedValue, charset);
	}
	
	/**
	 * URL解码
	 * @param value 字符串
	 * @param charsetName 字符集
	 * @return {String}
	 */
	public static String decodeUrlSafe(String value) {
		return Base64Utils.decodeUrlSafe(value, Charsets.UTF_8);
	}
}
