/****************************************************************
 *  文件名     ： WebsgsLoginServiceImpl.java
 *  日期         :  2012-11-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.service.impl;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.gm.p1.task.bean.SecurityConfig;
import com.iwgame.gm.p1.task.service.WebsgsLoginService;
import com.iwgame.gm.p1.task.util.MD5Util;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/** 
 * @描述:  	社区登录相关操作实现类
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-19下午05:54:42
 * @版本:   v1.0 
 */
@Service
public class WebsgsLoginServiceImpl implements WebsgsLoginService {
	
	private final Logger logger = Logger.getLogger("oasecurity");
	
	
	@Resource
	private SecurityConfig securityConfig;

	/**
	 * 禁止社区登入
	 */
	@Override
	public int disableLogin(String username,String pid) {
		try {
			String url = securityConfig.getWebsgs_url_sm()+"/add";
			String key = securityConfig.getWebsgs_key_sm();
			//url
			if("p-p1.5".equalsIgnoreCase(pid)){
				url = securityConfig.getWebsgs_url_zxy()+"/add";
				key = securityConfig.getWebsgs_key_zxy();
			}
			//params
			MultivaluedMap<String, String> map = new MultivaluedMapImpl();
			String ts = String.valueOf(System.currentTimeMillis());
			String sign = MD5Util.md5sum(username+key+ts);
			map.add("accounts", username);
			map.add("ts", ts);
			map.add("sign", sign);
			
			//send
			return senderHttpRequest(url,map);
		} catch (Exception e) {
			logger.error(username+" 帐号,限制社区登录失败!"+e.getMessage());
			return -10;
		}
		
	}

	/**
	 * 解除社区登入
	 */
	@Override
	public int enableLogin(String username,String pid) {
		try {
			//url
			String url = securityConfig.getWebsgs_url_sm()+"/delete";
			String key = securityConfig.getWebsgs_key_sm();
			
			//url
			if("p-p1.5".equalsIgnoreCase(pid)){
				url = securityConfig.getWebsgs_url_zxy()+"/delete";
				key = securityConfig.getWebsgs_key_zxy();
			}
			
			//params
			MultivaluedMap<String, String> map = new MultivaluedMapImpl();
			String ts = String.valueOf(System.currentTimeMillis());
			String sign = MD5Util.md5sum(username+key+ts);
			map.add("accounts", username);
			map.add("ts", ts);
			map.add("sign", sign);
			
			//send
			return senderHttpRequest(url,map);
		} catch (Exception e) {
			logger.error(username+" 帐号,解除限制社区登录失败!"+e.getMessage());
			return -10;
		}
		
	}
	
	
	/**
	 * 
	 * @说明: 发送Htpp请求
	 * @return: int
	 */
	private int senderHttpRequest(String url,MultivaluedMap<String, String> map){
		try {
			WebResource client = Client.create().resource("");
			WebResource wr = client.path(url);
			String result = wr.queryParams(map).accept(MediaType.APPLICATION_JSON).post(String.class);
			return reslutConvertByJson(result);
		} catch (Exception e) {
			logger.error("调用社区httpservice失败! 请求:"+map.toString(),e);
			return -10;
		}
	}
	
	
	/**
	 * 解析返回值
	 */
	private int reslutConvertByJson(String result) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(result);
			Integer ret = jsonObject.getInt("result");
			return ret;
		} catch (Exception e) {
			logger.error("返回值读取错误!",e);
			return -10;
		}
	}

}
