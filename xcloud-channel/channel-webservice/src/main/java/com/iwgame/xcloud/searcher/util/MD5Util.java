/****************************************************************
 *  文件名     ： MD5Util.java
 *  日期         :  2012-8-20
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

import com.iwgame.xcloud.searcher.service.impl.XServiceImpl;

/** 
 * @ClassName:    MD5Util 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-8-20下午05:30:46
 * @Version:      1.0 
 */
public abstract class MD5Util {
	
	private static final Logger LOGGER = Logger.getLogger(XServiceImpl.class);
    
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
        	LOGGER.error("MD5 Encode Faild !....");
        	return "";
        }
    }
    
    public static final String byteToBase16(byte[] bytes) {
        if (bytes == null){
        	 throw new IllegalArgumentException("The parameter should not be null!");
        }
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b & 0xF0) >> 4));
            sb.append(Integer.toHexString(b & 0x0F));
        }
        return sb.toString();
    }
}

