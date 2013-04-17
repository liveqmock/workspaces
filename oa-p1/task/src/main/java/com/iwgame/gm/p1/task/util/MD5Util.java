/****************************************************************
 *  文件名     ：MD5Util.java
 *  日期         :  2012-11-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

/**
 * 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-19下午04:17:58
 * @版本:   v1.0
 */
public abstract class MD5Util {
	
	private static final Logger logger = Logger.getLogger(Constant.LOG_XPORTAL_TASK);
    
    public static final boolean md5check(String data, String md5) {
        String sum = md5sum(data);
        return sum.equalsIgnoreCase(md5);
    }

    public static final String md5sum(String data) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            return byteToBase16(md.digest()).toUpperCase();
        } catch (Exception e) {
        	logger.error("MD5 not supported.", e);
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
}
