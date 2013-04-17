/****************************************************************
 *  系统名称  ： 'MQ消息生成模拟器'
 *  文件名    ： SenderMQ.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
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
public class GameSendMQ {

	private static final Logger LOGGER = Logger.getLogger(GameSendMQ.class);

	@Resource
	private RabbitTemplate rabbitTemplate;

	@BeforeClass
	public static void before() {
		PropertyConfigurator.configure("/Users/jjwu/Documents/workspace/iwgame/development/simulator/game-simulator/log4j/log4j.properties"); // 日志文件
		LOGGER.info("===============================");
		LOGGER.info("名称:MQ消息生成模拟器");
		LOGGER.info("版本号: 1.0");
		LOGGER.info("最后修改时间: 20120907");
		LOGGER.info("===============================");
	}

	@Test
	public void talk() {
		String talk = "{\"guid\":\"dx1\",\"groupname\":\"电信二三区【新月风云】\",\"username\":\"wujj\",\"validtime\":30}";
		rabbitTemplate.convertAndSend("p1.account.exchange", "talk", talk);
		LOGGER.info("禁言解除消息： " + talk);
	}

	@Test
	public void notalk() {
		String notalk = "{\"guid\":\"dx1\",\"groupname\":\"电信二三区【新月风云】\",\"username\":\"wujj\",\"validtime\":30}";
		rabbitTemplate.convertAndSend("p1.account.exchange", "notalk", notalk);
		LOGGER.info("禁言消息： " + notalk);
	}

	@Test
	public void kickuser() {
		String kickuser = "{\"guid\":\"dx1\",\"username\":\"_由于以下原因帐号被永久封闭：账号或关联账号在游戏中盗窃或参与诈骗、盗窃他人虚拟财产，或散布出售外挂、木马等。\"}";
		rabbitTemplate.convertAndSend("p1.account.exchange", "kickuser", kickuser);
		LOGGER.info("踢人消息： " + kickuser);
	}

	@Test
	public void unlockaccount() {
		String unlockaccount = "{\"guid\":\"dx1\",\"sealtime\":100,\"username\":\"shumenceshi002\",\"rolename\":\"吴君杰\",\"note\":\"测试\"}";
		rabbitTemplate.convertAndSend("p1.account.exchange", "unlockaccount", unlockaccount);
		LOGGER.info("解封帐号消息： " + unlockaccount);
	}

	@Test
	public void lockaccount() {
		String lockaccount = "{\"guid\":\"dx1\",\"sealtime\":100000,\"username\":\"shumenceshi001\",\"rolename\":\"wujj\",\"showtime\":0,\"online\":0,\"note\":\"账号不允许登录限号封测区，您可登录其他大区，祝您游戏愉快！\"}";
		rabbitTemplate.convertAndSend("p1.account.exchange", "lockaccount", lockaccount);
		LOGGER.info("封杀帐号消息： " + lockaccount);
	}

	@Test
	public void jbmibaoka() {
		String jbmibaoka = "{\"guid\":\"dx1\",\"type\":6,\"name\":\"shumenceshi002\",\"str3\":\"UNBIND\",\"operate\":\"2\"}";
		rabbitTemplate.convertAndSend("p1.securitycard.exchange", "securitycard", jbmibaoka);
		LOGGER.info("密保卡消息： " + jbmibaoka);
	}

	@Test
	public void bindmibaoka() {
		String bindmibaoka = "{\"guid\":\"dx1\",\"type\":6,\"name\":\"shumenceshi002\",\"str3\":\"PASSPOD=9527110\",\"operate\":\"1\"}";
		rabbitTemplate.convertAndSend("p1.securitycard.exchange", "securitycard", bindmibaoka);
		LOGGER.info("绑定密保卡消息： " + bindmibaoka);
	}

	@Test
	public void sendItemCard() {
		String bindmibaoka = "{\"appname\":\"道具卡测试\",\"cardnum\":\"HTSA3585DFAD\",\"cardpwd\":\"123456789012\",\"guid\":\"dx1\",\"itype\":0,\"sign\":\"4E133574B9ED0DCE1F5AAEF6853A5CD8\",\"ts\":1350523335312,\"username\":\"wujunjie\",\"validtime\":\"\"}";
		rabbitTemplate.convertAndSend("p1.itemcard.exchange", "itemcard", bindmibaoka);
		LOGGER.info("道具卡消息： " + bindmibaoka);
	}

	@Test
	public void safemodel() {
		String safemodel = "{\"appname\":\"aosecurity\",\"guid\":\"dx1\",\"dbid\":\"123456789012\",\"rolename\":\"jjwu\",\"batchid\":\"333113132131313\",\"groupname\":\"wujunjie\"}";
		rabbitTemplate.convertAndSend("p1.account.exchange", "safemodel", safemodel);
		LOGGER.info("添加安全模式： " + safemodel);
	}

	@Test
	public void unsafemodel() {
		String unsafemodel = "{\"appname\":\"aosecurity\",\"guid\":\"dx1\",\"dbid\":\"123456789012\",\"rolename\":\"jjwu\",\"batchid\":\"333113132131313\",\"groupname\":\"wujunjie\"}";
		rabbitTemplate.convertAndSend("p1.account.exchange", "unsafemodel", unsafemodel);
		LOGGER.info("解除安全模式： " + unsafemodel);
	}

}
