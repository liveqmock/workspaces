/****************************************************************
 * 系统名称 ： 'MQ消息生成模拟器'
 * 文件名 ： SenderMQ.java
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * **************************************************************
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.mq.sender.simulator.runtime;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author jjwu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/conf/applicationContext-simulator.xml" })
public class CommonSendMQ {

	private static final Logger LOGGER = Logger.getLogger(CommonSendMQ.class);

	@Resource
	private RabbitTemplate rabbitTemplate;

	@BeforeClass
	public static void before() {
		PropertyConfigurator.configure("/Users/jjwu/Documents/workspace/iwgame/development/simulator/common-simulator/log4j/log4j.properties"); // 日志文件
		LOGGER.info("===============================");
		LOGGER.info("名称:MQ消息生成模拟器");
		LOGGER.info("版本号: 1.0");
		LOGGER.info("最后修改时间: 20120907");
		LOGGER.info("===============================");
	}

	@Test
	public void sendMail() {
		for (int i = 0; i < 1; i++) {
			String mail = "{\"FNAME\":\"测试卡号:10101010101\",\"LNAME\":\"测试卡密:12345678901\",\"aparam\":\"\",\"appname\":\"WebServiceTest\",\"bparam\":\"\",\"cparam\":\"\",\"emailAddress\":\"335201@qq.com\",\"sign\":\"7E53ADCBAA3E320928EB37D934FFD958\",\"templateId\":\"1\",\"ts\":1350375867578}";
			rabbitTemplate.convertAndSend("common.email.exchange", "sendemail-dispatch", mail);
			LOGGER.info("邮件消息： " + mail);
		}

	}

	@Test
	public void notice() {
		String mail = "{\"optype\":2,\"channelid\":1}";
		rabbitTemplate.convertAndSend("common.email.exchange", "sendemail-notification", mail);
		LOGGER.info("邮件消息： " + mail);
	}

	@Test
	public void sendSMS3722() {
		String sms = "{\"phone\":\"13776801367\",\"message\":\"sms3722\"}";
		rabbitTemplate.convertAndSend("common.sms.exchange", "sms3722", sms);
		LOGGER.info("短信消息： " + sms);
	}
	//
	// @Test
	// public void sendSMS3664() {
	// String sms = "{\"phone\":\"18621118262\",\"message\":\"test\"}";
	// rabbitTemplate.convertAndSend("common.sms.exchange", "sms3664", sms);
	// LOGGER.info("短信消息： " + sms);
	// }
	//
	// @Test
	// public void sendSMS7947() {
	// String sms = "{\"phone\":\"13776801367\",\"message\":\"sms7947\"}";
	// rabbitTemplate.convertAndSend("common.sms.exchange", "sms7947", sms);
	// LOGGER.info("短信消息： " + sms);
	// }
	//
	// @Test
	// public void sendSMS3108() {
	// String sms = "{\"phone\":\"13776801367\",\"message\":\"sms3108\"}";
	// rabbitTemplate.convertAndSend("common.sms.exchange", "sms3108", sms);
	// LOGGER.info("短信消息： " + sms);
	// }
}
