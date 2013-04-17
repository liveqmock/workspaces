/**
 * MD5Util.java
 *
 * Copyright 2011 Baidu, Inc.
 *
 * Baidu licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.iwgame.iwcloud.baidu.task.util;

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
 * @修改时间：2012-6-29 上午09:25:59
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
