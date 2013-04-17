/****************************************************************
 *  文件名     ： LockAccountHandlerTask.java
 *  日期         :  2012-11-16
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.worker.unlock;

import javax.annotation.Resource;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iwgame.gm.p1.task.bean.SecurityAccount;
import com.iwgame.gm.p1.task.service.WebsgsLoginService;
import com.iwgame.gm.p1.task.service.XhttpService;
import com.iwgame.gm.p1.task.util.Constant;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

/**
 * @描述: 解封
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-11-16下午02:56:40
 * @版本: v1.0
 */
@Component
public class UnLockAccountHandlerTask {

	@Resource
	private XhttpService xhttpService;

	@Resource
	private WebsgsLoginService websgsLoginService;

	private final Logger logger = Logger.getLogger(Constant.LOG_OA_SECURITY);

	public void handleMessage(String source) {

		logger.info("收到解封,解冻请求:" + source);

		try {
			SecurityAccount account = new SecurityAccount(source);
			// 帐号解封
			int result = xhttpService.sendUnLockAccountByHttpService(account);
			if (result == 0) {
				logger.info("帐号 [" + account.getUsername() + "] 解封成功!");
			} else {
				logger.error("帐号 [" + account.getUsername() + "] 解封失败! 返回值:" + result);
			}

			// 解除社区登录限制
			if (account.getSqlogin() == 2) {
				try {
					int res = websgsLoginService.enableLogin(account.getUsername(), account.getPid());
					if (res == 1) {
						logger.info("帐号 [" + account.getUsername() + "],解除社区登录限制成功!");
					} else {
						logger.error("帐号 [" + account.getUsername() + "],解除社区登录限制失败!");
					}
				} catch (Exception e) {
					logger.error("帐号 [" + account.getUsername() + "],解除社区登录限制失败!");
				}
			}
		} catch (UniformInterfaceException e) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
			}
			logger.error("解封帐号失败!连接xhttpservice服务连接异常,重试...");
			throw e;
		} catch (ClientHandlerException e) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
			}
			logger.error("解封帐号失败!连接xhttpservice服务连接异常,重试...");
			throw e;
		} catch (JSONException e) {
			logger.error("忽略,参数异常!请求:" + source, e);
		} catch (Exception e) {
			logger.error("解封帐号失败! 请求" + source, e);
		}
	}
}
