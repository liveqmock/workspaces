/**
 * SHA1Util.java
 */
package com.iwgame.xengine.xtask.game.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

/**
 * 
 * 类说明
 * 
 * @简述： 加密修改
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-7-17 下午19:25:59
 */
public abstract class MD5Util {
	
	private static final Logger logger = Logger.getLogger(MD5Util.class);
    
    public static final boolean md5check(byte [] data, String md5) {
        String sum = md5sum(data);
        return sum.equalsIgnoreCase(md5);
    }

    public static final String md5sum(byte [] data) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(data);
            return byteToBase16(md.digest());
        } catch (Exception e) {
        	logger.error("SHA1 not supported.", e);
        }
        return "";
    }
    
    public static final String byteToBase16(byte[] bytes) {
        if (bytes == null){
        	logger.error("The parameter should not be null!");
        	return "";
        }
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b & 0xF0) >> 4));
            sb.append(Integer.toHexString(b & 0x0F));
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	
    	System.out.println(md5sum("1234567890".getBytes()));
		
	}
}
