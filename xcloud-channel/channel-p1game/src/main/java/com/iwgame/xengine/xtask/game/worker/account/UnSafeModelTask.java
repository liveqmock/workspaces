/****************************************************************
 *  文件名     ： SafeModelTask.java
 *  日期         :  2012-11-27
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
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
import com.iwgame.xengine.xtask.game.model.SafeModelBean;
import com.iwgame.xengine.xtask.game.service.impl.GameTaskServiceImpl;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;
import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.tools.XtaskTools;
import com.iwgame.xvalidators.Xvalidator;

/** 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-27下午02:47:46
 * @版本:   v1.0 
 */
public class UnSafeModelTask extends AbstractMQWorker {
	 
	private final Logger logger = Logger.getLogger("game");
	 
	@Resource
	private XtaskTools xtaskTools;
	
	@Resource
	private GameTaskServiceImpl gameTaskServiceImpl;
	
	
	public void handleMessage(String source){
		
		
		logger.info("解除安全模式收到请求资源: " + source);
		
		final String pid = ConfigProperties.getString("PID");
		
		SupportLog monitorlog = new SupportLog();
		monitorlog.setBizcode(29);
		monitorlog.setResource(source);
		monitorlog.setProductid(pid);
		
		try {
			SafeModelBean safeModelBean = new SafeModelBean(source);
			if(null != safeModelBean){
				monitorlog.setAppname("oasecurity");
				monitorlog.setVal1(safeModelBean.getRolename());
				monitorlog.setVal2(safeModelBean.getGuid());
				
				if(Xvalidator.getInstance().validate(safeModelBean)){
					gameTaskServiceImpl.unSafeModel(safeModelBean);
					monitorlog.setLogtype(0);
					monitorlog.setLognote("[解除安全模式]成功!");
				}else{
					monitorlog.setLognote("[解除安全模式]失败,请求的必要参数为空或者NULL,忽略此条消息...!");
				}
			}
		} catch (BadSqlGrammarException e) {
			monitorlog.setLognote("[解除安全模式]失败,发送到备份通道,连接异常"+e.getMessage());
			gameTaskServiceImpl.reSendMQBackupUnSafeModelActivate(source);
		} catch (GMConnectException e) {
			monitorlog.setLognote("[解除安全模式]失败,发送到备份通道,连接异常"+e.getMessage());
			gameTaskServiceImpl.reSendMQBackupUnSafeModelActivate(source);
		} catch (CannotGetJdbcConnectionException e) {
			monitorlog.setLognote("[解除安全模式]失败,发送到备份通道,连接异常"+e.getMessage());
			gameTaskServiceImpl.reSendMQBackupUnSafeModelActivate(source);
		} catch (PconfNotFoundException e) {
			monitorlog.setLognote("[解除安全模式]失败,发送到备份通道,连接异常"+e.getMessage());
			gameTaskServiceImpl.reSendMQBackupUnSafeModelActivate(source);
		} catch (DBConnectFailedException e) {
			monitorlog.setLognote("[解除安全模式]失败,发送到备份通道,连接异常"+e.getMessage());
			gameTaskServiceImpl.reSendMQBackupUnSafeModelActivate(source);
		} catch (Exception e) {
			monitorlog.setLognote("[解除安全模式]失败,忽略,其他异常:"+e.getMessage());
			logger.error("其他异常信息,请求资源" + source, e);
		} finally{
			try {
				xtaskTools.saveXtaskLogInfo(monitorlog);
			} catch (Exception ex) {
				logger.error("[xtask-support][解除安全模式]存储日志异常！",ex);
			}
		}
		
	}

}
