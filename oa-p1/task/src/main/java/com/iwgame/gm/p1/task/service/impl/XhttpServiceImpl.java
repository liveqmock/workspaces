/****************************************************************
 *  文件名     ： XhttpServiceImpl.java
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

import com.iwgame.gm.p1.task.bean.SafeModelBean;
import com.iwgame.gm.p1.task.bean.SecurityAccount;
import com.iwgame.gm.p1.task.bean.SecurityConfig;
import com.iwgame.gm.p1.task.bean.SecurityMail;
import com.iwgame.gm.p1.task.service.XhttpService;
import com.iwgame.gm.p1.task.util.MD5Util;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/** 
 * @描述:  	httpservice 实现
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-19上午09:45:31
 * @版本:   v1.0 
 */
@Service
public class XhttpServiceImpl implements XhttpService{
	
	private final Logger logger = Logger.getLogger("oasecurity");
	
	
	@Resource
	private SecurityConfig securityConfig;
	
	
	/**
	 * 邮件通知
	 */
	@Override
	public int sendMailByHttpService(SecurityMail param) {
		try {
			//url
			StringBuilder builder = new StringBuilder(securityConfig.getXhttpservice_url());
			builder.append("/mail/common/sendmail");
			
			//params
			MultivaluedMap<String, String> map = new MultivaluedMapImpl();
			String ts = String.valueOf(System.currentTimeMillis());
			String sign = getSign("common", param.getEmailAddress() , securityConfig.getXhttpservice_key() , ts);
			map.add("appname", "oasecurity");
			map.add("ts", ts);
			map.add("sign", sign);
			map.add("templateId", param.getTemplateId().toString());
			map.add("emailAddress", param.getEmailAddress());
			map.add("FNAME", param.getFNAME());
			map.add("LNAME", param.getLNAME());
			map.add("aparam", param.getAparam());
			map.add("bparam", param.getBparam());
			map.add("cparam", param.getCparam());
			
			//send
			return senderHttpRequest(builder.toString(), map);
		} catch (Exception e) {
			logger.error("帐号封杀邮件通知调用xhttpservice失败! 请求:"+param.toString(),e);
			return -10;
		}
	}

	/**
	 * 帐号封杀&冻结
	 */
	@Override
	public int sendLockAccountByHttpService(SecurityAccount param)  {
		try {
			String pid = param.getPid().toLowerCase();
			//url
			StringBuilder builder = new StringBuilder(securityConfig.getXhttpservice_url());
			builder.append("/game/").append(pid).append("/sendlockaccount");
			//map
			MultivaluedMap<String, String> map = new MultivaluedMapImpl();
			String ts = String.valueOf(System.currentTimeMillis());
			String sign = getSign(pid, param.getUsername() , securityConfig.getXhttpservice_key() , ts);
			map.add("appname", "oasecurity");
			map.add("ts", ts);
			map.add("sign", sign);
			map.add("guid", param.getGuid());
			map.add("username", param.getUsername());
			map.add("sealtime", String.valueOf(param.getSealtime()));
			map.add("showtime", String.valueOf(param.getShowtime()));
			map.add("online", String.valueOf(param.getOnline()));
			map.add("batchid", param.getBatchid());
			map.add("note", param.getCausenote());
			
			//send
			return senderHttpRequest(builder.toString(),map);
		} catch (ClientHandlerException e) {
			throw e;
		} catch (UniformInterfaceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("帐号封杀调用xhttpservice失败! 请求:"+param.toString(),e);
			return -10;
		}
	}


	/**
	 * 帐号解封
	 */
	@Override
	public int sendUnLockAccountByHttpService(SecurityAccount param) {
		try {
			String pid = param.getPid().toLowerCase();
			//url
			StringBuilder builder = new StringBuilder(securityConfig.getXhttpservice_url());
			builder.append("/game/").append(pid).append("/sendunlockaccount");

			//params
			MultivaluedMap<String, String> map = new MultivaluedMapImpl();
			String ts = String.valueOf(System.currentTimeMillis());
			String sign = getSign(pid, param.getUsername() , securityConfig.getXhttpservice_key() , ts);
			map.add("appname", "oasecurity");
			map.add("ts", ts);
			map.add("sign", sign);
			map.add("guid", param.getGuid());
			map.add("username", param.getUsername());
			map.add("sealtime", "0");
			map.add("showtime", "0");
			map.add("online", "0");
			map.add("batchid", param.getBatchid());
			map.add("note", param.getCausenote());
			
			//send
			return senderHttpRequest(builder.toString(),map);
		} catch (ClientHandlerException e) {
			throw e;
		} catch (UniformInterfaceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("帐号解封调用xhttpservice失败! 请求:"+param.toString(),e);
			return -10;
		}
		
	}
	
	/**
	 * 解除安全模式
	 */
	@Override
	public int sendUnSafeModelByHttpService(SafeModelBean param) {
		try {
			String pid = param.getPid().toLowerCase();
			//url
			StringBuilder builder = new StringBuilder(securityConfig.getXhttpservice_url());
			builder.append("/game/").append(pid).append("/sendUnSafeModel");

			//params
			MultivaluedMap<String, String> map = new MultivaluedMapImpl();
			String ts = String.valueOf(System.currentTimeMillis());
			String sign = getSign(pid, param.getDbid(), securityConfig.getXhttpservice_key() , ts);
			map.add("appname", "oasecurity");
			map.add("ts", ts);
			map.add("sign", sign);
			map.add("guid", param.getGuid());
			map.add("rolename", param.getRolename());
			map.add("batchid", param.getBatchid());
			map.add("groupname", param.getGroupname());
			map.add("dbid", param.getDbid());
			map.add("username", param.getUsername());
			
			//send
			return senderHttpRequest(builder.toString(),map);
		} catch (ClientHandlerException e) {
			throw e;
		} catch (UniformInterfaceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("帐号解封调用xhttpservice失败! 请求:"+param.toString(),e);
			return -10;
		}
		
	}
	
	/**
	 * 添加安全模式
	 */
	@Override
	public int sendAddSafeModelByHttpService(SafeModelBean param) {
		try {
			String pid = param.getPid().toLowerCase();
			//url
			StringBuilder builder = new StringBuilder(securityConfig.getXhttpservice_url());
			builder.append("/game/").append(pid).append("/sendAddSafeModel");

			//params
			MultivaluedMap<String, String> map = new MultivaluedMapImpl();
			String ts = String.valueOf(System.currentTimeMillis());
			String sign = getSign(pid, param.getDbid(), securityConfig.getXhttpservice_key() , ts);
			map.add("appname", "oasecurity");
			map.add("ts", ts);
			map.add("sign", sign);
			map.add("guid", param.getGuid());
			map.add("rolename", param.getRolename());
			map.add("batchid", param.getBatchid());
			map.add("groupname", param.getGroupname());
			map.add("dbid", param.getDbid());
			map.add("username", param.getUsername());
			
			//send
			return senderHttpRequest(builder.toString(),map);
		} catch (ClientHandlerException e) {
			throw e;
		} catch (UniformInterfaceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("添加安全模式调用xhttpservice失败! 请求:"+param.toString(),e);
			return -10;
		}
		
	}

	/**
	 * 转换返回值
	 */
	private int reslutConvertByJson(String result) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(result);
			Integer ret = jsonObject.getInt("result");
			return ret;
		} catch (Exception e) {
			logger.error("xhttpservice 返回值读取错误!");
			return -10;
		}
	}
	
	/**
	 * @说明: 生成签名 sgin
	 * @return: void
	*/
	private String getSign(String... params) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < params.length; i++) {
			builder.append(params[i]);
			if(i < (params.length -1)){
				builder.append("&");
			}
		}
		return MD5Util.md5sum(builder.toString());
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
		} catch (ClientHandlerException e) {
			throw e;
		} catch (UniformInterfaceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("调用xhttpservice失败! 请求:"+map.toString(),e);
			return -10;
		}
	}
}
