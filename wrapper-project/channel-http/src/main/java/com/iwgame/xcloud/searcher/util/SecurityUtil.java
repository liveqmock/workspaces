/****************************************************************
 *  文件名     ： SecurityUtil.java
 *  日期         :  2012-9-7
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.util;

import org.apache.log4j.Logger;

import com.iwgame.security.checksign.SignUtil;

/**
 * 
 * @类名:   SecurityUtil 
 * @描述:  	加密util
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24下午03:11:08
 * @版本:   1.0
 */
public class SecurityUtil {
	
	private static final Logger LOGGER = Logger.getLogger(SecurityUtil.class);
	
	/**
	 * 
	 * @param pid
	 * @param value
	 * @param sign
	 * @param ts
	 * @return
	 */
	public static int securityAuthority(String pid, Object value, String sign, long ts) {
		int rc = -3;
		try {
			Object[] params = new Object[] { pid, value };
			rc = SignUtil.checkSign(sign, ts, params);
			return rc;
		} catch (Exception e) {
			LOGGER.error("加密Key验证失败");
			rc = -3;
		}
		return rc;
	}

}
