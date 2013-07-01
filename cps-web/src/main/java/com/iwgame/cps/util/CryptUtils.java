/****************************************************************
 * 文件名 : CryptUtils.java
 * 日期 : 2013-6-29
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-6-29 上午9:50:11
 * @版本: v1.0.0
 */
@SuppressWarnings("restriction")
public class CryptUtils {

	private static final byte[] keydata = { 62, -38, 14, 82, 38, 11, -95, 13 };

	public static String encrypt(String data) {
		if ("MD5".equalsIgnoreCase("Caesar")) {
			return CryptUtils.makMd5Digest(data);
		}
		if ("Caesar".equalsIgnoreCase("Caesar")) {
			return CryptUtils.makCaesarSecurity(data);
		}
		return CryptUtils.makCaesarSecurity(data);
	}

	public static char rotatec(char c, int key) {
		key %= 26;
		if ((c <= 'Z') && (c >= 'A')) {
			int n = (c - 'A' + key) % 26;
			if (n < 0) {
				n += 26;
			}
			return (char) (n + 65);
		}
		if ((c <= 'z') && (c >= 'a')) {
			int n = (c - 'a' + key) % 26;
			if (n < 0) {
				n += 26;
			}
			return (char) (n + 97);
		}
		if ((c <= '9') && (c >= '0')) {
			int n = (c - '0' + key) % 10;
			if (n < 0) {
				n += 10;
			}
			return (char) (n + 48);
		}
		return c;
	}

	public static String makCaesarSecurity(String s) {
		String role_seq = "";
		StringBuffer sb = new StringBuffer();
		if (s.length() > "780404351".length()) {
			int tint = s.length() / "780404351".length();
			for (int i = 0; i < tint; i++) {
				role_seq = role_seq + "780404351";
			}
			tint = s.length() % "780404351".length();
			role_seq = role_seq + "780404351".substring(0, tint);
		} else {
			role_seq = "780404351".substring(0, s.length());
		}
		for (int i = 0; i < s.length(); i++) {
			sb.append(CryptUtils.rotatec(s.charAt(i), role_seq.charAt(i)));
		}
		return sb.toString();
	}

	public static String deCaesarSecurity(String s) {
		String role_seq = "";
		StringBuffer sb = new StringBuffer();
		if (s.length() > "780404351".length()) {
			int tint = s.length() / "780404351".length();
			for (int i = 0; i < tint; i++) {
				role_seq = role_seq + "780404351";
			}
			tint = s.length() % "780404351".length();
			role_seq = role_seq + "780404351".substring(0, tint);
		} else {
			role_seq = "780404351".substring(0, s.length());
		}
		for (int i = 0; i < s.length(); i++) {
			sb.append(CryptUtils.rotatec(s.charAt(i), -role_seq.charAt(i)));
		}
		return sb.toString();
	}

	public static String makMd5Digest(String f) {
		try {
			MessageDigest alg = MessageDigest.getInstance("MD5");
			alg.update(f.getBytes());
			byte[] digest = alg.digest();
			return CryptUtils.byte2hex(digest);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "";
	}

	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (byte element : b) {
			stmp = Integer.toHexString(element & 0xFF);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	public static void generateDESKeyFile(String filename) {
		try {
			SecureRandom sr = new SecureRandom();

			KeyGenerator kg = KeyGenerator.getInstance("DES");
			kg.init(sr);

			SecretKey key = kg.generateKey();

			OutputStream fos = new FileOutputStream(filename);
			fos.write(key.getEncoded());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] desEncryptData(byte[] data, byte[] rawKeyData) {
		try {
			SecureRandom sr = new SecureRandom();

			DESKeySpec dks = new DESKeySpec(rawKeyData);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);

			Cipher cipher = Cipher.getInstance("DES");

			cipher.init(1, key, sr);

			return cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] desDecryptData(byte[] data, byte[] rawKeyData) {
		try {
			SecureRandom sr = new SecureRandom();

			DESKeySpec dks = new DESKeySpec(rawKeyData);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);

			Cipher cipher = Cipher.getInstance("DES");

			cipher.init(2, key, sr);

			return cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] fromBase64(String s) {
		try {
			return new BASE64Decoder().decodeBuffer(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String toBase64(byte[] bs) {
		return new BASE64Encoder().encode(bs);
	}

	public static String encode(String data) {
		return CryptUtils.toBase64(CryptUtils.desEncryptData(data.getBytes(), CryptUtils.keydata));
	}

	public static String decode(String data) {
		return new String(CryptUtils.desDecryptData(CryptUtils.fromBase64(data), CryptUtils.keydata));
	}

	public static void main(String[] args) {
		try {
			String data = CryptUtils.encode("ABBBSSSXXXX");
			System.out.println("Encrypt data is:" + data);
			System.out.println("Decrypt data is:" + CryptUtils.decode(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
