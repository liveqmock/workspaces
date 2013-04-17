/****************************************************************
 *  文件名     ： CommonMessageServiceImpl.java
 *  日期         :  2012-9-14
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xtask.support.tools;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.model.SupportSms;
import com.iwgame.xtask.support.service.SmsService;
import com.iwgame.xtask.support.service.XtaskLogService;
import com.iwgame.xtask.support.util.SMSUtils;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 
 * @类名: XtaskTools
 * @描述: 短信发送,日志存储工具类
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-9-14上午10:59:35
 * @版本: 1.0
 */
@Service
public class XtaskTools {

	private final Logger logger = Logger.getLogger(XtaskTools.class);

	/**
	 * 最大缓存数量
	 */
	private final int maxKeyCount = 1000;

	/**
	 * 短信通知缓存
	 */
	private static Map<String, Long> notificationsPool = new Hashtable<String, Long>();

	@Resource
	private XtaskLogService xtaskLogService;

	@Resource
	private SmsService smsInnerService;

	/**
	 * 
	 * @说明: 添加缓存
	 * @return: void
	 */
	private synchronized void putCurrentBiz(String key, Long time) {
		if (notificationsPool.size() < maxKeyCount) {
			notificationsPool.put(key, time);
			logger.info("[NotificationsPool] 添加key: (" + key + ")");
		} else {
			logger.error("[XtaskSupport--> notificationsPool(Map)] 缓存超过最大极限[1000],请检查！");
		}
	}

	/**
	 * 
	 * @说明: 得到缓存
	 * @return: Long
	 */
	private Long getCacheBiz(String key) {
		return notificationsPool.get(key);
	}

	/**
	 * 
	 * @说明: 短信发送
	 * @return: void
	 */
	private void sendCommonSMS(String phone, String message) {

		SupportSms sms = new SupportSms();
		sms.setPhone(phone);
		sms.setMessage(message);

		String[] phones = sms.getPhone().split(",");

		StringBuffer group = new StringBuffer();

		// 短信群发相同内容
		if (phones != null && phones.length > 1) {
			// 验证手机号是否合规范,构建手机群发格式 例如:
			// "8613776801367,8613776801366,8613776801365"
			for (int i = 0; i < phones.length; i++) {
				boolean isPhone = SMSUtils.regularExpPhone(phones[i]);
				if (isPhone) {
					group.append("86");
					group.append(phones[i]);
					if (i < (phones.length - 1)) {
						group.append(",");
					}
				}
			}
			// 短信群发
			smsInnerService.SMSGroupSending(group.toString(), sms.getMessage());
		} else {
			// 验证手机号是否合规范
			boolean isPhone = SMSUtils.regularExpPhone(sms.getPhone());
			// 处理手机号
			if (isPhone) {
				sms.setPhone("86" + sms.getPhone());
				smsInnerService.SMSSending(sms.getPhone(), sms.getMessage());
			}
		}
	}

	/**
	 * 
	 * @说明: 用户自定义间隔时间发送短信
	 * @return: void
	 */
	public synchronized void intervalCustomNotice(String bizKey, String phone, long timer, String message) {
		logger.info("收到短信通知请求：bizKey:" + bizKey + ", phone:" + phone + ", timer:" + timer + ", message:" + message);
		if (getCacheBiz(bizKey) != null) {
			long cacheTime = getCacheBiz(bizKey);
			long curreTiem = System.currentTimeMillis();
			if ((curreTiem - cacheTime) > timer * 1000 * 60) {
				sendCommonSMS(phone, message);
				putCurrentBiz(bizKey, System.currentTimeMillis());
				logger.info("短信发送成功...");
			} else {
				logger.info("未超过指定的间隔时间,忽略此条信息...");
			}
		} else {
			sendCommonSMS(phone, message);
			putCurrentBiz(bizKey, System.currentTimeMillis());
		}
	}

	/**
	 * 
	 * @说明: 日志存储
	 * @return: void
	 */
	public void saveXtaskLogInfo(SupportLog monitorlog) {
		try {
			if (Xvalidator.getInstance().validate(monitorlog)) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("productId", monitorlog.getProductid());
				params.put("bizName", monitorlog.getBizcode());
				params.put("source", monitorlog.getResource());
				params.put("logType", monitorlog.getLogtype());
				if ("websgs2.x".equalsIgnoreCase(monitorlog.getAppname()) || "sm_sq".equalsIgnoreCase(monitorlog.getAppname())) {
					params.put("appName", "sq");// 社区处理appname
				} else if (null == monitorlog.getAppname() || "null".equalsIgnoreCase(monitorlog.getAppname())) {
					params.put("appName", "unknown");// 没有appname的
				} else {
					params.put("appName", monitorlog.getAppname());
				}
				params.put("logintime", monitorlog.getDatatime());
				params.put("logNote", monitorlog.getLognote());
				params.put("varOne", monitorlog.getVal1());
				params.put("varTwo", monitorlog.getVal2());
				params.put("varThree", monitorlog.getVal3());
				params.put("varFour", monitorlog.getVal4());
				params.put("varFive", monitorlog.getVal5());
				try {
					xtaskLogService.saveXtaskLogInfo(params);
					int reslurt = (Integer) params.get("result");
					if (reslurt == 0) {
						logger.info("产品ID[" + monitorlog.getProductid() + "], bizcode:[" + monitorlog.getBizcode() + "]日志存入成功!");
					} else {
						logger.error("产品ID[" + monitorlog.getProductid() + "], bizcode:[" + monitorlog.getBizcode() + "]日志存入失败!");
					}
				} catch (Throwable e) {
					e.printStackTrace();
					logger.error("产品ID[" + monitorlog.getProductid() + "], bizcode:[" + monitorlog.getBizcode() + "]日志存入失败!", e);
				}
			} else {
				logger.error("产品ID[" + monitorlog.getProductid() + "], bizcode:[" + monitorlog.getBizcode() + "]日志存入失败!,必要参数为NULL");
			}
		} catch (Exception e) {
			logger.error("产品ID[" + monitorlog.getProductid() + "], bizcode:[" + monitorlog.getBizcode() + "]日志存入失败!", e);
		}

	}

	/**
	 * 
	 * @说明: 短信通知,默认十分钟一次
	 * @return: void
	 */
	public synchronized void intervalNotice(String bizKey, String phone, String message) {
		logger.info("收到短信通知请求：bizKey:" + bizKey + ", phone:" + phone + ", message:" + message);
		if (getCacheBiz(bizKey) != null) {
			long cacheTime = getCacheBiz(bizKey);
			long curreTiem = System.currentTimeMillis();
			if ((curreTiem - cacheTime) > 600000 - 300) {
				sendCommonSMS(phone, message);
				putCurrentBiz(bizKey, System.currentTimeMillis());
				logger.info("bizKey:[" + bizKey + "] 短信发送成功...");
			} else {
				logger.info("未超过指定的间隔时间,忽略此条信息...");
			}
		} else {
			sendCommonSMS(phone, message);
			putCurrentBiz(bizKey, System.currentTimeMillis());
		}
	}
}
