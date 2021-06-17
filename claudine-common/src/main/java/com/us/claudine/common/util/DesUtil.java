package com.us.claudine.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author loren
 * @Description TODO
 * @Date 2021-06-17 16:06
 * @Version 1.0
 **/
@Slf4j
public class DesUtil {

	private static final String HEX_KEY = "83a26843835b9b94";

	public static byte[] initKey() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56);
		SecretKey secretKey = keyGen.generateKey();
		return secretKey.getEncoded();
	}

	public static byte[] encrypt(byte[] data) {
		SecretKey secretKey = new SecretKeySpec(HexUtil.hexToByte(HEX_KEY), "DES");

		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] cipherBytes = cipher.doFinal(data);
			return cipherBytes;
		} catch (Exception e) {
			log.error("encrypt by des error, data: {}", data, e);
		}

		return null;
	}

	public static byte[] decrypt(byte[] data) {
		SecretKey secretKey = new SecretKeySpec(HexUtil.hexToByte(HEX_KEY), "DES");

		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] plainBytes = cipher.doFinal(data);
			return plainBytes;
		} catch (Exception e) {
			log.error("decrypt by des error, data: {}", data, e);
		}

		return null;
	}

	public static void main(String[] args) throws Exception {
		String text = "f32d2e3b37e61172b59875490fd0b2af7ad90601.png";

		//byte[] desKey = DesUtil.initKey();
		//System.out.println("DES KEY : " + HexUtil.byteToHex(desKey));

		byte[] desResult = DesUtil.encrypt(text.getBytes());
		System.out.println(">>>DES 加密结果>>>" + HexUtil.byteToHex(desResult));

		byte[] desPlain = DesUtil.decrypt(desResult);
		System.out.println(">>>DES 解密结果>>>" + new String(desPlain));
	}

}
