/****************************************************************
 *  系统名称  ： '消息任务系统-蜀门&醉逍遥'
 *  文件名    ： XtaskServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.game.worker.account;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.game.model.TalkBean;
import com.iwgame.xengine.xtask.game.service.impl.GameTaskServiceImpl;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;
import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.tools.XtaskTools;

/**
 * 
 * 类说明
 * 
 * @简述：备份通道 解除禁言
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间：2012-4-24 下午02:53:24
 */
public class BackupNoTalkUserTask extends AbstractMQWorker {

	private final Logger logger = Logger.getLogger("game");

	@Resource
	private GameTaskServiceImpl gameTaskServiceImpl;

	@Resource
	private XtaskTools xtaskTools;

	public void handleMessage(String source) throws Exception {

		final String command = "禁言";

		logger.info(command + "=>[备份通道]收到请求资源: " + source);

		TalkBean talkBean = null;

		try {
			talkBean = new TalkBean(source);
			gameTaskServiceImpl.talkActivity(talkBean, command);
			logger.info("[备份通道][用户禁言]成功！");
			try {
				final String pid = ConfigProperties.getString("PID");
				SupportLog monitorlog = new SupportLog();
				monitorlog.setBizcode(25);
				monitorlog.setResource(source);
				monitorlog.setProductid(pid);
				monitorlog.setAppname(talkBean.getAppname());
				monitorlog.setVal1(talkBean.getRolename());
				monitorlog.setVal2(talkBean.getGuid());
				monitorlog.setLogtype(0);
				monitorlog.setLognote("[FROM备份通道][用户禁言]成功！");
				xtaskTools.saveXtaskLogInfo(monitorlog);
			} catch (Exception e) {
				
			}
		} catch (Exception e) {
			logger.error("[禁言-备用资源发放异常,间隔时间10秒]：当前请求资源：[" + source + "]ExceptionInfo:" + e);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			throw e;
		}
	}
}
