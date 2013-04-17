/****************************************************************
 *  系统名称  ：  '消息任务系统-公共服务-业务通道'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.sms.service.sms.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.xengine.xtask.sms.service.SmsXtaskService;
import com.iwgame.xengine.xtask.sms.service.core.SmsCoreSender;
import com.iwgame.xengine.xtask.sms.util.CommonUtils;

/**
 * 
 * @描述: 内部短信通知使用的通道
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-26上午11:43:43
 * @版本: v1.0.2
 */
@Service("smsInnerTaskService")
public class SmsInnerTaskServiceImpl extends SmsCoreSender implements SmsXtaskService {

	private final Logger logger = Logger.getLogger(SmsInnerTaskServiceImpl.class);

	@Override
	public String SMSSending(String phone, String message) throws SocketTimeoutException, ClientProtocolException, UnknownHostException, IOException {
		String resStr = "";
		try {
			// 生成单一下行请求消息 url
			String urlstr = CommonUtils.generateSingleSmsMtUrl("spid3664", "sppassword3664", phone, message);

			// 发送消息 http Post请求，并返回http响应字符串
			resStr = doPostSenderSMS(urlstr);

			if (resStr != null && !"".equals(resStr)) {
				// 解析响应字符串
				Map<String, Object> resolver = CommonUtils.parseResStr(resStr);

				// 下行消息在MLINK端的唯一标识号,在MT中用来匹配请求下发和状态报告 相当于此条短信ID号
				String mtmsgid = resolver.get("mtmsgid") == null ? "" : resolver.get("mtmsgid").toString();
				// MT状态代码
				// 000发送成功 //100 SP API接口参数错误//200 MLINK平台内部过滤路由信息 //300
				// MLINK平台内部SP配置信息//400 MLINK网关发送时错误//500 运行商反馈的信息 //600 MLINK
				// API发送时错误
				String mterrcode = resolver.get("mterrcode") == null ? "" : resolver.get("mterrcode").toString();
				// RT消息状态
				String mtstat = resolver.get("mtstat") == null ? "" : resolver.get("mtstat").toString();

				if (!"".equals(mtmsgid) && "000".equals(mterrcode)) {
					return SUCCESS;
				} else {
					// 这种情况不在重发短信，有可能导致堵塞。但是记录日志
					logger.error("[内部短信下发通道_短信发送失败],消息状态:[ " + mtstat + " ],服务器返回状态code:" + mterrcode + ",请求资源[phone:" + phone + ",message:" + message + "]");
					return resStr;
				}
			} else {
				// 这种情况不在重发短信，有可能导致堵塞 。但是记录日志
				logger.error("[内部短信下发通道_短信发送失败],返回的解析字符串为空或NULL,请求资源[phone:" + phone + ",message:" + message + "]");
				return resStr;
			}
		} catch (ClientProtocolException e) {
			throw e;
		} catch (SocketTimeoutException e) {
			throw e;
		} catch (UnknownHostException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			logger.error("[内部短信下发通道_短信发送失败],其他异常,请求资源[phone:" + phone + ",message:" + message + "] 其他异常信息：", e);
			return resStr;
		}

	}

	@Override
	public String SMSGroupSending(String phones, String message) throws SocketTimeoutException, ClientProtocolException, UnknownHostException, IOException {

		String resStr = "";
		try {
			// 群发下行请求消息 url
			String urlstr = CommonUtils.generateSmsMultiMtUrl("spid3664", "sppassword3664", phones, message);

			// 发送消息 http Post请求，并返回http响应字符串
			resStr = doPostSenderSMS(urlstr);

			if (resStr != null && !"".equals(resStr)) {
				// 解析响应字符串
				Map<String, Object> resolver = CommonUtils.parseResStr(resStr);

				// 下行消息在MLINK端的唯一标识号,在MT中用来匹配请求下发和状态报告
				String mtmsgid = resolver.get("mtmsgid") == null ? "" : resolver.get("mtmsgid").toString();
				// 群发下行消息在MLINK端的唯一标识号,在MT中用来匹配请求下发和状态报告
				String mtmsgids = resolver.get("mtmsgids") == null ? "" : resolver.get("mtmsgids").toString();
				// MT状态代码
				String mterrcode = resolver.get("mterrcode") == null ? "" : resolver.get("mterrcode").toString();
				// RT消息状态
				String mtstat = resolver.get("mtstat") == null ? "" : resolver.get("mtstat").toString();

				if ((!"".equals(mtmsgid) || !"".equals(mtmsgids)) && "000".equals(mterrcode)) {
					logger.info("[内部短信下发通道_短信发送成功],消息状态:[ " + mtstat + " ],请求资源[phone:" + phones + ",message:" + message + "]");
					return SUCCESS;
				} else {
					// 这种情况不在重发短信，有可能导致堵塞。但是记录日志
					logger.error("[内部短信下发通道_短信发送失败],服务器返回状态code:" + mterrcode + ",请求资源[phone:" + phones + ",message:" + message + "]");
					return resStr;
				}

			} else {
				// 这种情况不在重发短信，有可能导致堵塞 。但是记录日志
				logger.error("[内部短信下发通道_短信发送失败],返回的解析字符串为空或NULL,请求资源[phone:" + phones + ",message:" + message + "]");
				return resStr;
			}
		} catch (ClientProtocolException e) {
			throw e;
		} catch (SocketTimeoutException e) {
			throw e;
		} catch (UnknownHostException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			logger.error("[内部短信下发通道_短信发送失败],其他异常,请求资源[phone:" + phones + ",message:" + message + "] 其他异常信息：", e);
			return resStr;
		}
	}

	@Override
	public String SMSGroupDifferentSending(String phones, String message) throws IOException {
		// TODO Auto-generated method stub
		return "";
	}

}
