/****************************************************************
 *  系统名称  ： '消息任务系统-公共服务-业务通道'
 *  文件名    ： XtaskService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.sms.service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.client.ClientProtocolException;

import com.iwgame.xengine.xtask.sms.util.MailException;

/**
 * 
 * @描述: 短信通道接口
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-26上午11:43:06
 * @版本: v1.0.2
 */
public interface SmsXtaskService {

	/**
	 * 短信发送
	 * 
	 * @param phone
	 * @param message
	 * @throws MailException
	 * @throws SocketTimeoutException
	 * @throws ClientProtocolException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public String SMSSending(String phone, String message) throws MailException, SocketTimeoutException, ClientProtocolException, UnknownHostException, IOException;

	/**
	 * 短信群发送
	 * 
	 * @param phones
	 *            手机号 format=>"13776801367,13776801368"
	 * @param message
	 *            相同消息内容
	 * @throws MailException
	 * @throws SocketTimeoutException
	 * @throws ClientProtocolException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public String SMSGroupSending(String phones, String message) throws MailException, SocketTimeoutException, ClientProtocolException, UnknownHostException, IOException;

	/**
	 * 短信群发送不同内容
	 * 
	 * @param phones
	 *            手机号 format=>"13776801367,13776801368"
	 * @param message
	 *            不同消息内容
	 * @throws MailException
	 * @throws SocketTimeoutException
	 * @throws ClientProtocolException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public String SMSGroupDifferentSending(String phones, String message) throws MailException, SocketTimeoutException, ClientProtocolException, UnknownHostException, IOException;

}
