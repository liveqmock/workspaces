/****************************************************************
 *  文件名     ： SmsCoreSender.java
 *  日期         :  2012-10-17
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.sms.service.core;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.sms.util.ConfigProperties;

/**
 * 类说明：
 * 
 * @简述: 短信发送核心类
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-12-17 下午05:33:47
 * @版本: 1.0
 */
public abstract class SmsCoreSender {

	private final Logger logger = Logger.getLogger(SmsCoreSender.class);

	protected static final String SUCCESS = "success";

	/**
	 * 
	 * @说明: 发送短信实现类
	 * @return: String
	 */
	protected String doPostSenderSMS(String smsurl) throws SocketTimeoutException, SocketTimeoutException, ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10 * 1000);
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20 * 1000);
		HttpPost httpPost = new HttpPost(smsurl);
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			// 读取response
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return ConfigProperties.replaceBlank(responseHandler.handleResponse(httpResponse));
		} catch (ClientProtocolException e) {
			logger.error("短信服务协议错误，等待10秒重试！");
			sleepTime(10);
			throw e;
		} catch (SocketTimeoutException e) {
			logger.error("短信服务连接超时，等待10秒重试！");
			sleepTime(10);
			throw e;
		} catch (UnknownHostException e) {
			logger.error("无法解析Host地址:" + smsurl + "，短信服务器可能维护，等待3分钟重试！");
			sleepTime(180);
			throw e;
		} catch (IOException e) {
			logger.error("网络连接异常url:" + smsurl + "，短信服务器可能维护，等待10秒钟重试！");
			sleepTime(10);
			throw e;
		} catch (Exception e) {
			logger.error("短信发送失败,其他异常,消息忽略!", e);
		} finally {
			if (null != httpPost) {
				httpPost.releaseConnection();
				httpPost = null;
			}
			if (null != httpClient) {
				httpClient.getConnectionManager().shutdown();
				httpClient = null;
			}
		}
		return "";
	}

	/**
	 * @说明: 睡眠
	 * @return: void
	 */
	public void sleepTime(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e1) {
		}
	}

}
