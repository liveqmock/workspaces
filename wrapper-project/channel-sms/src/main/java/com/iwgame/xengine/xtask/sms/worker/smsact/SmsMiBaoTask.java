/****************************************************************
 *  文件名     ： SmsMiBaoTask.java
 *  日期         :  2012-9-7
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.sms.worker.smsact;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.sms.model.Sms;
import com.iwgame.xengine.xtask.sms.service.SmsXtaskService;
import com.iwgame.xengine.xtask.sms.util.CommonUtils;
import com.iwgame.xengine.xtask.sms.util.ConfigProperties;
import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.tools.XtaskTools;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 
 * @描述: 密保短信通知
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-26上午09:40:10
 * @版本: v1.0.2
 */
@Component
public class SmsMiBaoTask extends AbstractMQWorker {

	private final Logger logger = Logger.getLogger(SmsActTask.class);

	@Resource
	private SmsXtaskService smsMiBaoTaskService;

	@Resource
	private XtaskTools xtaskTools;

	public void handlesmsMiBao(String source) throws IOException {

		logger.info("[密保手机短信下发通道]请求资源：" + source);

		SupportLog monitorlog = new SupportLog();// 日志存储
		monitorlog.setBizcode(12);// 业务代码
		monitorlog.setResource(source);// 请求
		monitorlog.setProductid("COMMON");// PID

		Sms sms = null;
		try {
			sms = new Sms(source);
			// 验证必要字段是否为空
			boolean falg = Xvalidator.getInstance().validate(sms);

			monitorlog.setAppname(sms.getAppname());
			monitorlog.setVal1(sms.getPhone());
			monitorlog.setVal2("3108");

			String[] phones = sms.getPhone().split(",");

			// 短信群发相同内容
			if (phones != null && phones.length > 1) {
				if (falg) {

					StringBuffer group = new StringBuffer();

					// 验证手机号是否合规范,构建手机群发格式 例如:
					// "8613776801367,8613776801366,8613776801365"
					for (int i = 0; i < phones.length; i++) {
						boolean isPhone = CommonUtils.regularExpCommon(ConfigProperties.getString("regPhone"), phones[i]);
						if (isPhone) {
							group.append("86");
							group.append(phones[i]);
							if (i < (phones.length - 1)) {
								group.append(",");
							}
						} else {
							logger.error("忽略不合法的手机号:" + phones[i]);
						}
					}
					// 短信群发
					String result = smsMiBaoTaskService.SMSGroupSending(group.toString(), sms.getMessage());

					if ("success".equalsIgnoreCase(result)) {
						logger.info("短信发送成功-> Phones: " + group.toString());
						monitorlog.setLognote("群发短信成功!");
						monitorlog.setLogtype(0);
					} else {
						logger.error("活动短信群发失败,忽略! phones: " + group.toString() + "服务器返回：" + result);
						monitorlog.setLognote("密保手机短信下发通道]群发短信失败! 服务器返回：" + result);
					}
				} else {
					logger.error("必要要字段为空,请求:" + source);
					monitorlog.setLognote("必要要字段为空,忽略请求...");
				}
			} else {
				if (falg) {
					// 验证手机号是否合规范
					boolean isPhone = CommonUtils.regularExpCommon(ConfigProperties.getString("regPhone"), sms.getPhone());
					// 处理手机号
					if (isPhone) {
						sms.setPhone("86" + sms.getPhone());
						String result = smsMiBaoTaskService.SMSSending(sms.getPhone(), sms.getMessage());
						if ("success".equalsIgnoreCase(result)) {
							logger.info("短信发送成功-> Phones: " + sms.getPhone());
							monitorlog.setLognote("发短信成功!");
							monitorlog.setLogtype(0);
						} else {
							logger.error("活动短信群发失败,忽略! phones: " + sms.getPhone() + "服务器返回：" + result);
							monitorlog.setLognote("密保手机短信下发通道]发短信失败! 服务器返回：" + result);
						}
					} else {
						logger.error("手机号不合法,忽略请求..!" + sms.getPhone());
						monitorlog.setLognote("手机号不合法,忽略请求..!" + sms.getPhone());
					}
				} else {
					logger.error("必要要字段为空,请求:" + source);
					monitorlog.setLognote("必要要字段为空,忽略请求...");
				}
			}
		} catch (ClientProtocolException e) {
			monitorlog.setLognote("[活动系统短信下发通道]短信发送失败,重试,服务器协议错误" + e.getMessage());
			throw e;
		} catch (SocketTimeoutException e) {
			monitorlog.setLognote("[活动系统短信下发通道]短信发送失败,重试,服务器连接超时" + e.getMessage());
			throw e;
		} catch (UnknownHostException e) {
			monitorlog.setLognote("[活动系统短信下发通道]短信发送失败,重试,无法解析服务器url" + e.getMessage());
			throw e;
		} catch (IOException e) {
			monitorlog.setLognote("[活动系统短信下发通道]短信发送失败,重试,HTTP连接异常" + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("[内部短信下发通道]_短信发送失败:其他异常：[" + sms.toString() + "]异常:", e);
			monitorlog.setLognote("[内部短信下发通道]短信发送失败,忽略,其他异常:" + e.getMessage());
		} finally {
			try {
				xtaskTools.saveXtaskLogInfo(monitorlog);
			} catch (Exception ex) {
				logger.error("[内部短信下发通道],保存日志失败!");
			}
		}
	}

}
