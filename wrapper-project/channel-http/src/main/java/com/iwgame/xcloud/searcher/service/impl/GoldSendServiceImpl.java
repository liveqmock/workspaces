/****************************************************************
 *  文件名     ： GoldSendServiceImpl.java
 *  日期         :  2012-9-21
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

import com.iwgame.xcloud.searcher.model.GoldParamBean;
import com.iwgame.xcloud.searcher.model.MQSendResult;
import com.iwgame.xcloud.searcher.service.GoldSendService;
import com.iwgame.xcloud.searcher.util.SecurityUtil;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 
 * @类名:   GoldSendServiceImpl 
 * @描述:  	水晶点卡充值操作,发送到MQ消息队列
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24下午01:59:10
 * @版本:   1.0
 */
@Service
public class GoldSendServiceImpl implements GoldSendService {

	private final Logger logger = Logger.getLogger("gold");

	@Resource
	private RabbitTemplate rabbitTemplateSmUcenter; 	// 蜀门水晶点卡

	@Resource
	private RabbitTemplate rabbitTemplateZxyUcenter; 	// 醉逍遥水晶点卡

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xcloud.searcher.service.GoldSendService#sendGoldByMQ(java.
	 * lang.String, com.iwgame.xcloud.searcher.model.GoldParamBean)
	 */
	@Override
	public String sendGoldByMQ(String pid, GoldParamBean goldParamBean) {
		logger.info("AppName:[" + goldParamBean.getAppname() + "]收到水晶点卡 (产品ID:" + pid + ") 请求:" + goldParamBean.toString());
		
		MQSendResult result = new MQSendResult();
		
		try {
			// 验证必要参数
			if (Xvalidator.getInstance().validate(goldParamBean)) {
				// 验证签名
				int rc = SecurityUtil.securityAuthority(pid, goldParamBean.getAccountid(), goldParamBean.getSign(), goldParamBean.getTs());
				if (0 == rc) {
					if ("P-P1".equalsIgnoreCase(pid)) {
						rabbitTemplateSmUcenter.convertAndSend("p1.goldsend.exchange", "goldsend", goldParamBean.toString());
						String virtualHost = rabbitTemplateSmUcenter.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + goldParamBean.getAppname() + "]发送蜀门水晶点卡到[(" + virtualHost + ")]成功!请求:" + goldParamBean.toString());
						result.setResult(rc);
						
						
					} else if ("P-P1.5".equalsIgnoreCase(pid)) {
						rabbitTemplateZxyUcenter.convertAndSend("p1.goldsend.exchange", "goldsend", goldParamBean.toString());
						String virtualHost = rabbitTemplateZxyUcenter.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + goldParamBean.getAppname() + "]发送醉逍遥水晶点卡到[(" + virtualHost + ")]成功!请求:" + goldParamBean.toString());
						result.setResult(rc);
						
					} else {
						logger.info("水晶点卡请求错误,未找到产品ID:" + pid);
						result.setResult(-4);
						
					}
				} else {
					result.setResult(rc);
					logger.error("AppName:[" + goldParamBean.getAppname() + "]水晶点卡签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [Accountid:" + goldParamBean.getAccountid()
							+ "] [sign:" + goldParamBean.getSign() + "] [ts:" + goldParamBean.getTs() + "]");
					
				}
			} else {
				result.setResult(-4);
				logger.error("AppName:[" + goldParamBean.getAppname() + "]水晶点卡参数错误,必要参数为空或NULL!请求:" + goldParamBean.toString());
				
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + goldParamBean.getAppname() + "]水晶点卡其他异常!请求:" + goldParamBean.toString(), ex);
			
		}
		
		return result.toString();
	}
}
