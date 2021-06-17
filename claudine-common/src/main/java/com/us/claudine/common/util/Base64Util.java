package com.us.claudine.common.util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Author loren
 * @Description TODO
 * @Date 2021-06-17 16:04
 * @Version 1.0
 **/
@Slf4j
public class Base64Util {

	private static final BASE64Encoder encoder;

	private static final BASE64Decoder decoder;

	static {
		encoder = new BASE64Encoder();
		decoder = new BASE64Decoder();
	}

	public static String encode(String text) {
		try {
			byte[] textByte = text.getBytes("UTF-8");
			return encoder.encode(textByte);
		} catch (UnsupportedEncodingException e) {
			log.error("Encode by base64 error, text: {}", text, e);
			throw new RuntimeException(e);
		}
	}

	public static String decode(String text) {
		try {
			return new String(decoder.decodeBuffer(text), "UTF-8");
		} catch (IOException e) {
			log.error("Decode by base64 error, text: {}", text, e);
			throw new RuntimeException(e);
		}
	}

}
