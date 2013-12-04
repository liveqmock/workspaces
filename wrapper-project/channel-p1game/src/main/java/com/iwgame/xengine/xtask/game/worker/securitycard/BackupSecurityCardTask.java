package com.iwgame.xengine.xtask.game.worker.securitycard;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.game.model.SecurityCardBean;
import com.iwgame.xengine.xtask.game.service.impl.SecurityCardTaskServiceImpl;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;
import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.tools.XtaskTools;

public class BackupSecurityCardTask extends AbstractMQWorker {

	private final Logger logger = Logger.getLogger("game");
	
	@Resource
	private SecurityCardTaskServiceImpl securityCardTaskServiceImpl;
	
	
	@Resource
	private XtaskTools xtaskTools;

	/**
	 * 密保卡
	 * 
	 * @param source
	 * @throws Exception 
	 */
	public void handleMessage(String source) throws Exception {

		try {
			logger.info("密保卡=>[备份通道]收到请求资源:" + source);
			SecurityCardBean securityCardBean = new SecurityCardBean(source);
			securityCardTaskServiceImpl.securityCardActivity(securityCardBean);
			logger.info("密保卡=>[备份通道]激活或绑定完成:" + source);
			try {
				final String pid = ConfigProperties.getString("PID");
				SupportLog monitorlog = new SupportLog();
				monitorlog.setBizcode(22);
				monitorlog.setResource(source);
				monitorlog.setProductid(pid);
				monitorlog.setAppname(securityCardBean.getAppname());
				monitorlog.setVal1(securityCardBean.getName());
				monitorlog.setVal2(securityCardBean.getGuid());
				monitorlog.setLogtype(0);
				monitorlog.setLognote("[FROM备份通道][密保卡]绑定&解绑成功!");
				xtaskTools.saveXtaskLogInfo(monitorlog);
			} catch (Exception e) {
				
			}
		} catch (Exception e) {
			logger.error("[密保卡业务-备用通道发放异常,等待10秒]：当前请求资源：[" + source + "]异常:" + e);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			throw e;
		}
	}
}
