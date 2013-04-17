/****************************************************************
 *  文件名     ： UnSafeModelTask.java
 *  日期         :  2012-11-16
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.worker.unsafemodel;

import javax.annotation.Resource;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iwgame.gm.p1.task.bean.SafeModelBean;
import com.iwgame.gm.p1.task.service.XhttpService;
import com.iwgame.gm.p1.task.util.Constant;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

/**
 * @描述: 解除安全模式
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-11-16下午03:01:47
 * @版本: v1.0
 */
@Component
public class UnSafeModelTask {

	private final Logger logger = Logger.getLogger(Constant.LOG_SAFE_MODEL);

	@Resource
	private XhttpService xhttpService;

	public void handleMessage(String source) {

		logger.info("收到解除安全模式请求:" + source);

		SafeModelBean safemodel = null;
		try {
			safemodel = new SafeModelBean(source);
			if (null != safemodel) {
				int result = xhttpService.sendUnSafeModelByHttpService(safemodel);
				if (result == 0) {
					logger.info("解除安全模式成功!角色ID:" + safemodel.getDbid());
				} else {
					logger.error("解除安全模式失败!返回值:" + result + "角色ID:" + safemodel.getDbid());
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
			logger.error("解除安全模式异常!", e);
		}
	}
}
