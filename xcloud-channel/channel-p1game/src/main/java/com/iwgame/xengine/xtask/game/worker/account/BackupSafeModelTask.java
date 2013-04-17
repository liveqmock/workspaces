/****************************************************************
 *  文件名     ： BackupSafeModelTask.java
 *  日期         :  2012-11-27
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.game.worker.account;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.game.model.SafeModelBean;
import com.iwgame.xengine.xtask.game.service.impl.GameTaskServiceImpl;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;
import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.tools.XtaskTools;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-11-27下午02:48:00
 * @版本: v1.0
 */
public class BackupSafeModelTask extends AbstractMQWorker {

	private final Logger logger = Logger.getLogger("game");

	@Resource
	private XtaskTools xtaskTools;

	@Resource
	private GameTaskServiceImpl gameTaskServiceImpl;

	public void handleMessage(String source) throws Exception {

		logger.info("[备用通道]解除安全模式收到请求资源: " + source);
		try {
			SafeModelBean safeModelBean = new SafeModelBean(source);
			gameTaskServiceImpl.addSafeModel(safeModelBean);
			try {
				final String pid = ConfigProperties.getString("PID");
				SupportLog monitorlog = new SupportLog();
				monitorlog.setBizcode(28);
				monitorlog.setResource(source);
				monitorlog.setProductid(pid);
				monitorlog.setAppname("oasecurity");
				monitorlog.setVal1(safeModelBean.getRolename());
				monitorlog.setVal2(safeModelBean.getGuid());
				monitorlog.setLogtype(0);
				monitorlog.setLognote("[FROM备用通道][添加安全模式]成功！");
				xtaskTools.saveXtaskLogInfo(monitorlog);
			} catch (Exception e) {

			}
		} catch (Exception e) {
			logger.error("[安全模式-备用通道发放异常,等待10秒]：当前请求资源：[" + source + "] 异常:" + e);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
			}
			throw e;
		}

	}

}
