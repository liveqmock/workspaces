/****************************************************************
 *  系统名称  ： '广告系统-[xportal-task]'
 *  文件名    ： HttpUtil.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.util;

import javax.ws.rs.core.MultivaluedMap;

import com.iwgame.gm.p1.task.exception.HttpRequestException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * 类说明
 * @简述： 封装的HTTP请求
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-11-30 下午03:12:41
 */
public class HttpUtil {
	
	/**
	 * HTTP请求
	 * @param url
	 * @param param
	 * @return
	 * @throws HttpRequestException
	 */
    public synchronized static String getHttpRequestResult(String url,MultivaluedMap<String, String> param) throws HttpRequestException{
    	String result = "";
    	WebResource client = null;
    	WebResource wr = null;
    	try {
    		client = Client.create().resource("");
    		wr = client.path(url);
    		result = wr.queryParams(param).get(String.class);
		} catch (Exception e) {
			throw new HttpRequestException("HTTP请求失败, url:" + url + ", err=" + e);
		} finally {
			client = null;
			wr = null;
		}
    	return result;
    }
}
