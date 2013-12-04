/****************************************************************
 *  文件名     ： MailSendServiceImpl.java
 *  日期         :  2012-9-9
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
import com.iwgame.xcloud.searcher.model.MailParamBean;
import com.iwgame.xcloud.searcher.service.MailSendService;
import com.iwgame.xcloud.searcher.util.SecurityUtil;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 
 * @类名:   MailSendServiceImpl 
 * @描述:  	邮件请求,发送到MQ消息队列实现类
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24下午02:02:10
 * @版本:   1.0
 */
@Service
public class MailSendServiceImpl implements MailSendService {

	private final Logger logger = Logger.getLogger("common");
	
	@Resource
	private RabbitTemplate rabbitTemplateCommon;
	
	/* (non-Javadoc)
	 * @see com.iwgame.xcloud.searcher.service.MailSendService#sendMailByMQ(java.lang.String, java.lang.String, com.iwgame.xcloud.searcher.model.MailParamBean)
	 */
	@Override
	public String sendMailByMQ(String pid, MailParamBean mailParamBean) {
		logger.info("AppName:["+mailParamBean.getAppname()+"]收到邮件请求:"+mailParamBean.toString());
		
		MQSendResult result = new MQSendResult();			
		
		try {
			//验证必要参数
			if(Xvalidator.getInstance().validate(mailParamBean)){
				
				int rc = SecurityUtil.securityAuthority(pid, mailParamBean.getEmailAddress(), mailParamBean.getSign(), mailParamBean.getTs());
				if(0 == rc){
					rabbitTemplateCommon.convertAndSend("common.email.exchange", "sendemail", mailParamBean.toString());
					logger.info("AppName:["+mailParamBean.getAppname()+"]发送邮件通道(task-common)成功!请求:" + mailParamBean.toString());
					result.setResult(rc);
					
				}else{
					result.setResult(rc);
					logger.error("AppName:[" + mailParamBean.getAppname() + "]邮件签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [emaillAddress:" + mailParamBean.getEmailAddress() + "] [sign:" + mailParamBean.getSign() + "] [ts:" + mailParamBean.getTs() + "]");
					
				}
			}else{
				result.setResult(-4);
				logger.error("AppName:["+mailParamBean.getAppname()+"]请求参数错误,必要参数为空或NULL!请求:"+mailParamBean.toString());
				
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:["+mailParamBean.getAppname()+"]其他异常!请求:" + mailParamBean.toString(), ex);
			
		}
		return result.toString();
	}

}
