/****************************************************************
 *  文件名   	:	d.java
 *  日期		:  	2013-6-29
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps.util;

import java.security.MessageDigest;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import sun.misc.BASE64Encoder;

import com.sun.crypto.provider.SunJCE;

/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-6-29 上午9:55:48
 * @版本:   	v1.0.0
 */
@SuppressWarnings("restriction")
public class MD5Encoder extends MessageDigestPasswordEncoder
{
	protected static final byte[] BYTES_KEY = { -99, 118, 97, -105, -51, -17, 81, 14 };

	static
	{
		Security.addProvider(new SunJCE());
	}

	public MD5Encoder()
	{
		super("MD5");
	}

	public String encodePassword(String strEncrypt, String salt) {
		if (strEncrypt == null) {
			strEncrypt = "";
		}
		if (salt == null) {
			salt = "";
		}
		try {
			strEncrypt = strEncrypt + salt;
			byte[] b = strEncrypt.getBytes("UTF8");
			SecretKey key = new SecretKeySpec(MD5Encoder.BYTES_KEY, "DES");
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding", "SunJCE");
			cipher.init(1, key);
			b = cipher.doFinal(b);
			BASE64Encoder encoder = new BASE64Encoder();
			strEncrypt = encoder.encode(b);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strEncrypt.getBytes("UTF8"));
			b = md.digest();
			strEncrypt = encoder.encode(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strEncrypt;
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt)
	{
		String pass1 = encPass;
		String pass2 = encodePassword(rawPass, salt);
		System.out.println("pass1==" + pass1 + "pass2==" + pass2);
		return pass1.equals(pass2);
	}

	public static void main(String[] args) {
		MD5Encoder encoder = new MD5Encoder();
		String tmp = encoder.encodePassword("123", null);
		System.out.println("tmp===" + tmp);
	}
}