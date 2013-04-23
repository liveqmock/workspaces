/****************************************************************
 *  文件名     ： SmsSendService.java
 *  日期         :  2012-9-7
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.iwgame.xcloud.searcher.model.MQSendResult;
import com.iwgame.xcloud.searcher.model.SmsParamBean;
import com.iwgame.xcloud.searcher.service.SmsSendService;
import com.iwgame.xcloud.searcher.util.SecurityUtil;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
 * @类名:   SmsSendServiceImpl
=======
 * @类名:   SmsSendServiceImpl 
>>>>>>> Revert "httprequest"
 * @描述:  	短信请求,发送到MQ队列相关实现类
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24下午02:04:02
 * @版本:   1.0
<<<<<<< HEAD
=======
=======
>>>>>>> httprequest
 * @类名: SmsSendServiceImpl
 * @描述: 短信请求,发送到MQ队列相关实现类
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-24下午02:04:02
 * @版本: 1.0
<<<<<<< HEAD
>>>>>>> httprequest
=======
>>>>>>> httprequest
=======
>>>>>>> Revert "httprequest"
 */
@Service
public class SmsSendServiceImpl implements SmsSendService {

	private final Logger logger = Logger.getLogger("common");

	@Resource
	private RabbitTemplate rabbitTemplateCommon;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xcloud.searcher.service.SmsSendService#sendSmsByMQ(java.lang
	 * .String, java.lang.String, com.iwgame.xcloud.searcher.model.SmsParamBean)
	 */
	@Override
	public String sendSmsByMQ(String pid, SmsParamBean smsParamBean) {
		logger.info("AppName:[" + smsParamBean.getAppname() + "]收到短信请求:" + smsParamBean.toString());
<<<<<<< HEAD

		final MQSendResult result = new MQSendResult();

<<<<<<< HEAD
<<<<<<< HEAD


=======
>>>>>>> httprequest
=======
>>>>>>> httprequest
=======
		
		MQSendResult result = new MQSendResult();	
		
		
		
>>>>>>> Revert "httprequest"
		try {
			// 验证必填参数
			if (Xvalidator.getInstance().validate(smsParamBean)) {
				
				// 验证签名
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
				final int rc = SecurityUtil.securityAuthority(pid, smsParamBean.getPhone(), smsParamBean.getSign(), smsParamBean.getTs());
=======
				final int rc = SecurityUtil.securityAuthority(pid, smsParamBean.getPhone(), smsParamBean.getSign(),
						smsParamBean.getTs());
>>>>>>> httprequest
=======
				final int rc = SecurityUtil.securityAuthority(pid, smsParamBean.getPhone(), smsParamBean.getSign(),
						smsParamBean.getTs());
>>>>>>> httprequest
=======
				int rc = SecurityUtil.securityAuthority(pid, smsParamBean.getPhone(), smsParamBean.getSign(), smsParamBean.getTs());
>>>>>>> Revert "httprequest"
				if (rc == 0) {
					String routeKey = "sms" + smsParamBean.getQueueNo();
					rabbitTemplateCommon.convertAndSend("common.sms.exchange", routeKey, smsParamBean.toString());
					logger.info("AppName:[" + smsParamBean.getAppname() + "]发送短信通道(task-common):(" + routeKey + ")成功!请求:" + smsParamBean.toString());
					result.setResult(rc);
					
				} else {
					logger.error("AppName:[" + smsParamBean.getAppname() + "]签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [Phone:" + smsParamBean.getPhone() + "] ");
					result.setResult(rc);
					
				}
			} else {
				result.setResult(-4);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
				logger.error("AppName:[" + smsParamBean.getAppname() + "]请求参数错误,必要参数为空或NULL!请求:" + smsParamBean.toString());
=======
				logger.error("AppName:[" + smsParamBean.getAppname() + "]请求参数错误,必要参数为空或NULL!请求:"
						+ smsParamBean.toString());
>>>>>>> httprequest
=======
				logger.error("AppName:[" + smsParamBean.getAppname() + "]请求参数错误,必要参数为空或NULL!请求:"
						+ smsParamBean.toString());
>>>>>>> httprequest

=======
				logger.error("AppName:[" + smsParamBean.getAppname() + "]请求参数错误,必要参数为空或NULL!请求:" + smsParamBean.toString());
			
>>>>>>> Revert "httprequest"
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + smsParamBean.getAppname() + "]其他异常!请求:" + smsParamBean.toString(), ex);
			
		}
		return result.toString();
	}
<<<<<<< HEAD

<<<<<<< HEAD
<<<<<<< HEAD
	/* (non-Javadoc)
	 * @see com.iwgame.xcloud.searcher.service.SmsSendService#test(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String test(final HttpServletRequest request, final HttpServletResponse response) {
=======
=======
>>>>>>> httprequest
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xcloud.searcher.service.SmsSendService#test(javax.servlet.
	 * http.HttpServletRequest)
	 */
	@Override
	public String getHttpRequest(final HttpServletRequest request, final HttpServletResponse response) {
<<<<<<< HEAD
>>>>>>> httprequest
=======
>>>>>>> httprequest
		return request.getParameterMap().toString();
	}
=======
>>>>>>> Revert "httprequest"
}
