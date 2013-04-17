/****************************************************************
 *  系统名称  ： '消息任务系统-公共服务-业务通道'
 *  文件名    ： XtaskService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xtask.support.service;

import java.io.IOException;



/**
 * 类说明
 * 
 * @简述： 短信通道接口规范
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-4-8 下午04:41:09
 */
public interface SmsService {
	
	
	/**
	 * 短信发送
	 * @param phone 手机号
	 * @param message 短信内容
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public void SMSSending(String phone,String message);
	
	/**
	 * 短信群发送
	 * @param phones 手机号 format=>"13776801367,13776801368"
	 * @param message 相同消息内容
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public void SMSGroupSending(String phones,String message);
	
	/**
	 * 短信群发送
	 * @param phones
	 * @param message  不相同消息内容
	 * @throws HttpException
	 * @throws IOException
	 */
	public void SMSGroupDifferentSending(String phones,String message);
}
