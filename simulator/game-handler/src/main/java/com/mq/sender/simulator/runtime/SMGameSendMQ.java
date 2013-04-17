/****************************************************************
 *  系统名称  ： 'MQ消息生成模拟器'
 *  文件名    ： SenderMQ.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.mq.sender.simulator.runtime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.Resource;

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
public class SMGameSendMQ {

	@Resource
	private RabbitTemplate rabbitTemplate;

	/**
	 * 注意:执行之前请改变配置 game.simulator.mq.virtualhost=task-p1-game
	 */
	@Test
	public void lockaccount() {

		// List<String> guids = new ArrayList<String>();
		// guids.add("dx1");
		// guids.add("dx10");
		// guids.add("dx11");
		// guids.add("dx13");
		// guids.add("dx14");
		// guids.add("dx15");
		// guids.add("dx2");
		// guids.add("dx4");
		// guids.add("dx7");
		// guids.add("dx8");
		// guids.add("dxfc");
		// guids.add("wt1");
		// guids.add("wt2");
		// guids.add("wt3");
		// guids.add("wt4");
		// guids.add("wt5");
		// guids.add("wt7");
		// guids.add("wt8");
		// guids.add("wtfc");

		File file = new File("/Users/jjwu/Documents/sm.txt");
		FileReader reader = null;
		BufferedReader buffer = null;
		int line = 0;
		try {
			reader = new FileReader(file);
			buffer = new BufferedReader(reader);
			String username = null;

			while ((username = buffer.readLine()) != null) {
				String lockaccount = "{\"appname\":\"\",\"guid\":\"all\",\"batchid\":\"\",\"optype\":1,\"sealtime\":10000000,\"showtime\":0,\"online\":0,\"username\":\""
						+ username + "\",\"note\":\"帐号状态异常，暂时封闭，可致电021-22817059进行咨询。\"}";
				rabbitTemplate.convertAndSend("p1.account.exchange", "lockaccount", lockaccount);
				System.out.println(++line);
			}
		} catch (FileNotFoundException e) {
			System.err.println("文件没有找到!");
		} catch (IOException e) {
			System.err.println("IO异常");
		} finally {
			try {
				reader.close();
				buffer.close();
			} catch (IOException e) {
				System.err.println("IO异常");
			}
		}

		/**
		 * 注意:执行之前请改变配置 game.simulator.mq.virtualhost=task-p1-game
		 */

	}
}
