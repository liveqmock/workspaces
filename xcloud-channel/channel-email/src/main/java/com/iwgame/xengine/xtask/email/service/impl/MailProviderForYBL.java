/****************************************************************
 *  文件名     ： MailProvider_nx_impl.java
 *  日期         :  2013-1-2
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.email.service.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.xengine.xtask.email.model.Mail;
import com.iwgame.xengine.xtask.email.service.MailProvider;
import com.iwgame.xengine.xtask.email.tools.exception.MailException;
import com.iwgame.xengine.xtask.email.tools.utils.ProperUtils;

/**
 * @类名: MailProvider_nx_impl
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮件: wujunjie@iwgame.com
 * @日期: 2013-1-2下午10:35:47
 * @版本: 1.0
 */
@Service
public class MailProviderForYBL implements MailProvider {

	private final Logger logger = Logger.getLogger(MailProviderForYBL.class);

	private static Cookie cookie = null;

	/**
	 * 汉启邮件发送实现
	 * 
	 * @see
	 */
	@Override
	public String sendMailHandler(Mail mail) throws MailException {
		String result = "";
		DefaultHttpClient httpclient = null;
		HttpPost post = null;
		try {
			if (cookie == null) {
				initCookie();
			}
			StringBuilder sendMailUrl = new StringBuilder();
			sendMailUrl.append(ProperUtils.getString("mail_send_url"));
			sendMailUrl.append("&email=");
			sendMailUrl.append(mail.getEmailAddress());
			sendMailUrl.append("&eid=");
			sendMailUrl.append(mail.getTemplateId());
			sendMailUrl.append("&FNAME=");
			sendMailUrl.append(URLEncoder.encode(mail.getFNAME() == null ? "" : mail.getFNAME(), "gbk"));// 中文转换
			sendMailUrl.append("&LNAME=");
			sendMailUrl.append(URLEncoder.encode(mail.getLNAME() == null ? "" : mail.getLNAME(), "gbk"));// 中文转换
			sendMailUrl.append("&APARAM=");
			sendMailUrl.append(URLEncoder.encode(mail.getAparam() == null ? "" : mail.getAparam(), "gbk"));// 中文转换
			sendMailUrl.append("&BPARAM=");
			sendMailUrl.append(URLEncoder.encode(mail.getBparam() == null ? "" : mail.getBparam(), "gbk"));// 中文转换
			sendMailUrl.append("&CPARAM=");
			sendMailUrl.append(URLEncoder.encode(mail.getCparam() == null ? "" : mail.getCparam(), "gbk"));// 中文转换
			httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10 * 1000);
			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20 * 1000);
			httpclient.getCookieStore().addCookie(cookie);
			post = new HttpPost(sendMailUrl.toString());
			HttpResponse response = httpclient.execute(post);
			result = resultHandler(response, mail.toString());
		} catch (ClientProtocolException e) {
			cookie = null;// 设置为null,重新去服务器获取cookie认证
			logger.error("发送邮件服务,协议错误,重试！异常:" + e.getMessage());
			throw new MailException(e.getMessage());
		} catch (SocketTimeoutException e) {// 连接超时停留10秒钟再去请求
			cookie = null;// 设置为null,重新去服务器获取cookie认证
			logger.error("发送邮件服务,连接超时,重试！异常:" + e.getMessage());
			throw new MailException(e.getMessage());
		} catch (UnknownHostException e) {// 无法解析对方地址,第三方邮件服务器可能在维护,停留3分钟再次请求
			cookie = null;// 设置为null,重新去服务器获取cookie认证
			logger.error("发送邮件服务,无法解析Host地址:" + ProperUtils.getString("mail_send_url") + ",异常:" + e.getMessage());
			throw new MailException(e.getMessage());
		} catch (MailException e) {
			cookie = null;// 设置为null,重新去服务器获取cookie认证
			throw new MailException(e.getMessage());
		} catch (IOException e) {
			cookie = null;// 设置为null,重新去服务器获取cookie认证
			logger.error("发送邮件服务,网络连接异常,重试！");
			throw new MailException(e.getMessage());
		} catch (Exception e) {
			cookie = null;
			logger.error("发送邮件服务,失败,其他异常,消息忽略!", e);
		} finally {
			if (null != post) {
				post.releaseConnection();
				post = null;
			}
			if (null != httpclient) {
				httpclient.getConnectionManager().shutdown();
				httpclient = null;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.email.service.MailProvider#resultHandler(java
	 * .lang.Object)
	 */
	@Override
	public String resultHandler(Object reslut, String source) throws MailException {
		HttpResponse httpResponse = (HttpResponse) reslut;
		if (httpResponse.getStatusLine().getStatusCode() != java.net.HttpURLConnection.HTTP_OK || !"OK".equalsIgnoreCase(httpResponse.getStatusLine().getReasonPhrase())) {
			logger.error("服务器返回状态码错误,StatusCode: " + httpResponse.getStatusLine().getStatusCode());
			throw new MailException("服务器返回状态码错误,StatusCode: " + httpResponse.getStatusLine().getStatusCode());
		}

		// 读取response
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response = "";
		try {
			response = ProperUtils.replaceBlank(responseHandler.handleResponse(httpResponse));
		} catch (ClientProtocolException e) {
			logger.error("解析返回结果失败!");
		} catch (IOException e) {
			logger.error("解析返回结果失败!");
		}

		if (null != response && (response.startsWith("err:") || response.startsWith("500") || response.length() > 100)) {
			// "err:email:illegal" 非法邮件,忽略掉... 服务器:err:eid:illegal
			if (response.indexOf("err:email:illegal") != -1) {
				return response;
			} else if (((response.startsWith("err:internal")) || (response.startsWith("err:server")) || (response.startsWith("err:param:requested data not available")) || (response
					.startsWith("500")))) {
				logger.error("服务器内部错误,消息返回队列! response:" + response);
				throw new MailException("服务器内部错误,消息返回队列! response:" + response);
			} else {
				logger.error("Cookie可能过期,服务器认证失败,将重新获取cookie,消息返回队列! response:" + response);
				throw new MailException("Cookie可能过期,服务器认证失败,将重新获取cookie,消息返回队列! response:" + response);
			}
		}
		return response;
	}

	/**
	 * @说明: 登录认证,并且缓存cookie
	 * @return: void
	 */
	private synchronized void initCookie() throws MailException, SocketTimeoutException, ClientProtocolException, UnknownHostException, IOException {
		if (cookie == null) {
			DefaultHttpClient httpclient = null;
			HttpGet httpGet = null;
			try {
				httpclient = new DefaultHttpClient();
				httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10 * 1000);
				httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20 * 1000);
				httpGet = new HttpGet(ProperUtils.getString("mail_server_connect_url"));
				HttpResponse response = httpclient.execute(httpGet);
				String auth = resultHandler(response, "");
				logger.info("执行登录认证:" + auth);
				List<Cookie> cookies = httpclient.getCookieStore().getCookies();
				if (cookies.size() > 0) {
					cookie = cookies.get(0);
				} else {
					throw new IOException("获取认证失败!Cookie为null,请检测登陆认证url...");
				}
			} catch (ClientProtocolException e) {
				logger.error("邮件服务登录认证,邮件服务协议错误,重试！");
				throw e;
			} catch (SocketTimeoutException e) {
				logger.error("邮件服务登录认证,证连接超时,重试！");
				throw e;
			} catch (UnknownHostException e) {
				logger.error("邮件服务登录认证证,无法解析Host地址:" + ProperUtils.getString("mail_server_connect_url") + ",邮件服务器可能维护,重试！");
				throw e;
			} catch (MailException e) {
				throw e;
			} catch (IOException e) {
				logger.error("邮件服务登录认证,证网络连接异常,重试！");
				throw e;
			} catch (Exception e) {
				logger.error("邮件服务登录认证,邮件获取认证失败,其他异常,消息忽略!", e);
			} finally {
				if (null != httpGet) {
					httpGet.releaseConnection();
					httpGet = null;
				}
				if (null != httpclient) {
					httpclient.getConnectionManager().shutdown();
					httpclient = null;
				}
			}
		}
	}

}
