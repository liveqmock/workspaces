/****************************************************************
 *  系统名称  ： 'MQ消息生成模拟器'
 *  文件名    ： SenderMQ.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.mq.sender.simulator.runtime;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

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
		PropertyConfigurator
				.configure("/Users/jjwu/Yunio/workspaces/common-simulator/log4j/log4j.properties"); // 日志文件
		LOGGER.info("===============================");
		LOGGER.info("名称:MQ消息生成模拟器");
		LOGGER.info("版本号: 1.0");
		LOGGER.info("最后修改时间: 20120907");
		LOGGER.info("===============================");
	}

	@Test
	public void sendMail() {
		String mail = "{\"FNAME\":\"测试卡号:10101010101\",\"LNAME\":\"帐号封杀\",\"aparam\":\"\",\"appname\":\"WebServiceTest\",\"bparam\":\"\",\"cparam\":\"\",\"emailAddress\":\"astroboyx@icloud.com\",\"sign\":\"7E53ADCBAA3E320928EB37D934FFD958\",\"templateId\":\"198315\",\"ts\":1350375867578}";
		rabbitTemplate.convertAndSend("common.email.exchange", "sendemail",
				mail);
		LOGGER.info("邮件消息： " + mail);
	}

	@Test
	public void sendSMS3722() {
		String sms = "{\"phone\":\"13776801367\",\"message\":\"sms3722\"}";
		rabbitTemplate.convertAndSend("common.sms.exchange", "sms3722", sms);
		LOGGER.info("短信消息： " + sms);
	}

	@Test
	public void sendSMS3664() {
		String sms = "{\"phone\":\"18621118262\",\"message\":\"test\"}";
		rabbitTemplate.convertAndSend("common.sms.exchange", "sms3664", sms);
		LOGGER.info("短信消息： " + sms);
	}

	@Test
	public void sendSMS7947() {
		String sms = "{\"phone\":\"13776801367\",\"message\":\"sms7947\"}";
		rabbitTemplate.convertAndSend("common.sms.exchange", "sms7947", sms);
		LOGGER.info("短信消息： " + sms);
	}

	@Test
	public void sendSMS3108() {
		String sms = "{\"phone\":\"13776801367\",\"message\":\"sms3108\"}";
		rabbitTemplate.convertAndSend("common.sms.exchange", "sms3108", sms);
		LOGGER.info("短信消息： " + sms);
	}

	@Test
	public void sendFlumeMonitor() {
		RegisterModel model = new RegisterModel();
		model.setPid("p-p1");
		model.setRip("192.168.100.1");
		model.setRmsg("");
		model.setRsource("活动");
		model.setRtime("2013-03-13 15:15:00");
		model.setRtype("注册");
		model.setRusername("wujunjie");
		model.setRstatus("0");

		String sms = "{\"channelId\":\"\",\"dynamicParameter\":{},\"eventMsg\":"
				+ JSONObject.fromObject(model) + "}\"}";
		rabbitTemplate.convertAndSend("flow-bussineslog.p-p1.5.exchange",
				"flow-bussineslog", sms);
		LOGGER.info("sendFlumeMonitor： " + sms);
	}

	@Test
	public void sendFlumeLoginMonitor() {
		LoginModel model = new LoginModel();
		model.setZone("dx1");
		model.setName("OgIdSogRyn");
		model.setIp("127.0.0.1");
		model.setMac("11111");
		model.setAddtime("2013-03-23 10:13:43");
		String sms = "{\"channelId\":\"\",\"dynamicParameter\":{},\"eventMsg\":"
				+ JSONObject.fromObject(model) + "}\"}";
		rabbitTemplate.convertAndSend("flow-login.p-p1.exchange", "flow-login",
				sms);
		LOGGER.info("sendFlumeMonitor： " + sms);
	}

	@Test
	public void sendFlumeMonitor_p1() {
		RegisterModel model = new RegisterModel();
		model.setPid("P-P1");
		model.setRip("192.168.100.1");
		model.setRmsg("");
		model.setRsource("活动");
		model.setRtime("2013-02-26 15:00:00");
		model.setRtype("注册");
		model.setRusername("wujunjie");
		model.setRstatus("0");

		String sms = "{\"channelId\":\"\",\"dynamicParameter\":{},\"eventMsg\":"
				+ JSONObject.fromObject(model) + "}\"}";
		rabbitTemplate.convertAndSend("flow-bussineslog.p-p1.5.exchange",
				"flow-bussineslog", sms);
		LOGGER.info("sendFlumeMonitor： " + sms);
	}

	@Test
	public void sendFlumeActorTradeLogMonitor() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("userlogtype", 1);
		jsonMap.put("logtime", 1);
		jsonMap.put("id1", 1);
		jsonMap.put("i1", 1);
		jsonMap.put("i2", 1);
		jsonMap.put("i3", 1);
		jsonMap.put("i4", 1);
		String sms = "{\"channelId\":\"\",\"dynamicParameter\":{\"$zone\":\"dx1\",\"$gameid\":\"game2\"},\"eventMsg\":"
				+ JSONObject.fromObject(jsonMap) + "}\"}";
		rabbitTemplate.convertAndSend("flow-actortradelog.p-p1.5.exchange",
				"flow-actortradelog", sms);
		LOGGER.info("sendFlumeMonitor： " + sms);
	}

	@Test
	public void sendFlumeChatMonitorActorLog() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("accountid", "10000");
		jsonMap.put("accountname", "wujj21");
		jsonMap.put("actorid", "-1543265423");
		jsonMap.put("actorname", "jjwu");
		jsonMap.put("actorlev", "15");
		jsonMap.put("senderip", "973235210");
		jsonMap.put("chatcontent","AAAAAAAAAAAAAABBBBBBBB#@!$^");
		jsonMap.put("channel", "1");
		jsonMap.put("groupname", "电信二三区【新月风云】");
		jsonMap.put("logtime",String.valueOf(System.currentTimeMillis() / 1000));
		String sms = "{\"channelId\":\"\",\"dynamicParameter\":{\"$zone\":\"dx1\",\"$gameid\":\"game2\"},\"eventMsg\":"+ JSONObject.fromObject(jsonMap) + "}\"}";
		rabbitTemplate.convertAndSend("flow-actorchatlog.p-p1.exchange","flow-actorchatlog", sms);
		LOGGER.info("sendFlumeMonitor： " + sms);
	}

}
