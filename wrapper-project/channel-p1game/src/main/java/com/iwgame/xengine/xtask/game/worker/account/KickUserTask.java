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
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.iwgame.pconf.exception.PconfNotFoundException;
import com.iwgame.xconnector.exeception.DBConnectFailedException;
import com.iwgame.xconnector.exeception.GMConnectException;
import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.game.model.KickBean;
import com.iwgame.xengine.xtask.game.service.impl.GameTaskServiceImpl;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;
import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.tools.XtaskTools;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 
 * 类说明
 * 
 * @简述： 踢人
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-4-24 下午02:53:24
 */
public class KickUserTask extends AbstractMQWorker {

	private final Logger logger = Logger.getLogger("game");
	
	@Resource
	private GameTaskServiceImpl gameTaskServiceImpl;
	
	@Resource
	private XtaskTools xtaskTools;


	public void handleMessage(String source) {

		logger.info("踢人=>收到请求资源: " + source);
		String pid = ConfigProperties.getString("PID");
		SupportLog monitorlog = new SupportLog();
		monitorlog.setBizcode(27);
		monitorlog.setResource(source);
		monitorlog.setProductid(pid);
		
		
		KickBean kickBean = null;
		try {
			kickBean = new KickBean(source);

			monitorlog.setAppname(kickBean.getAppname());
			monitorlog.setVal1(kickBean.getUsername());
			monitorlog.setVal2(kickBean.getGuid());
			
			//验证必要参数
			boolean checking = Xvalidator.getInstance().validate(kickBean);
			if(checking){
				gameTaskServiceImpl.kickUserActivity(kickBean);
				monitorlog.setLogtype(0);
				monitorlog.setLognote("[踢人命令]执行成功！");
			}else{
				monitorlog.setLognote("请求的必要参数为空或者NULL,忽略此条消息...");
			}
		} catch (BadSqlGrammarException e) {
			monitorlog.setLognote("[踢人失败]失败,发送到备份通道,连接异常："+e.getMessage());
			gameTaskServiceImpl.reSendMQBackupKickUserActivate(source);
		} catch (GMConnectException e) {
			monitorlog.setLognote("[踢人失败]失败,发送到备份通道,连接异常："+e.getMessage());
			gameTaskServiceImpl.reSendMQBackupKickUserActivate(source);
		} catch (CannotGetJdbcConnectionException e) {
			monitorlog.setLognote("[踢人失败]失败,发送到备份通道,连接异常："+e.getMessage());
			gameTaskServiceImpl.reSendMQBackupKickUserActivate(source);
		} catch (PconfNotFoundException e) {
			monitorlog.setLognote("[踢人失败]失败,发送到备份通道,连接异常："+e.getMessage());
			gameTaskServiceImpl.reSendMQBackupKickUserActivate(source);
		} catch (DBConnectFailedException e) {
			monitorlog.setLognote("[踢人失败]失败,发送到备份通道,连接异常："+e.getMessage());
			gameTaskServiceImpl.reSendMQBackupKickUserActivate(source);
		} catch (Exception e) {
			logger.error("[忽略]其他异常信息,请求资源"	+ source, e);
			monitorlog.setLognote("[忽略]失败,其他异常信息："+e.getMessage());
		}finally{
			try {
				xtaskTools.saveXtaskLogInfo(monitorlog);
			} catch (Exception ex) {
				logger.info("[xtask-support][踢人失败]存储日志异常！",ex);
			}
		}
	}
}
