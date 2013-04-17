/****************************************************************
 *  文件名     ： ItemCardServiceImpl.java
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

import com.iwgame.xcloud.searcher.model.ItemCardParamBean;
import com.iwgame.xcloud.searcher.model.MQSendResult;
import com.iwgame.xcloud.searcher.service.ItemCardService;
import com.iwgame.xcloud.searcher.util.SecurityUtil;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 
 * @类名:   ItemCardServiceImpl 
 * @描述:  	道具卡激活,发送到MQ消息队列实现类
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24下午01:59:48
 * @版本:   1.0
 */
@Service
public class ItemCardServiceImpl implements ItemCardService {

	private final Logger logger = Logger.getLogger("itemcard");

	@Resource
	private RabbitTemplate rabbitTemplateSm;    //蜀门

	@Resource
	private RabbitTemplate rabbitTemplateZxy;   //醉逍遥
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xcloud.searcher.service.ItemCardService#sendItemCardByMQ(java
	 * .lang.String, com.iwgame.xcloud.searcher.model.ItemCardParamBean)
	 */
	@Override
	public String sendItemCardByMQ(String pid, ItemCardParamBean itemCardParamBean) {
		logger.info("AppName:[" + itemCardParamBean.getAppname() + "]收到道具卡 (产品ID:" + pid + ") 请求:" + itemCardParamBean.toString());
		
		MQSendResult result = new MQSendResult();
		
		try {
			// 验证必要参数
			if (Xvalidator.getInstance().validate(itemCardParamBean)) {
				
				//验证签名
				int rc = SecurityUtil.securityAuthority(pid, itemCardParamBean.getUsername(), itemCardParamBean.getSign(), itemCardParamBean.getTs());
				if (0 == rc) {
					if ("P-P1".equalsIgnoreCase(pid)) {
						rabbitTemplateSm.convertAndSend("p1.itemcard.exchange", "itemcard", itemCardParamBean.toString());
						String virtualHost = rabbitTemplateSm.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + itemCardParamBean.getAppname() + "]发送蜀门道具卡到[("+virtualHost+")]成功!请求:" + itemCardParamBean.toString());
						result.setResult(rc);
						
					} else if ("P-P1.5".equalsIgnoreCase(pid)) {
						rabbitTemplateZxy.convertAndSend("p1.itemcard.exchange", "itemcard", itemCardParamBean.toString());
						String virtualHost = rabbitTemplateZxy.getConnectionFactory().getVirtualHost();
						logger.info("AppName:[" + itemCardParamBean.getAppname() + "]发送醉逍遥道具卡到[("+virtualHost+")]成功!请求:" + itemCardParamBean.toString());
						result.setResult(rc);
						
					} else {
						logger.info("道具卡激活请求错误,未找到产品ID:" + pid);
						result.setResult(-4);
					}
				} else {
					result.setResult(rc);
					logger.error("AppName:[" + itemCardParamBean.getAppname() + "]道具卡激活签名验证失败!返回值[" + rc + "] 参数:[pid:" + pid + "] [username:" + itemCardParamBean.getUsername() + "] [sign:" + itemCardParamBean.getSign() + "] [ts:" + itemCardParamBean.getTs() + "]");
				}
			} else {
				result.setResult(-4);
				logger.error("AppName:[" + itemCardParamBean.getAppname() + "]道具卡激活参数错误,必要参数为空或NULL!请求:" + itemCardParamBean.toString());
			}
		} catch (Exception ex) {
			result.setResult(-4);
			logger.error("AppName:[" + itemCardParamBean.getAppname() + "]道具卡激活其他异常!请求:" + itemCardParamBean.toString(), ex);
		}
		
		return result.toString();
	}

}
