package com.iwgame.xengine.xtask.game.worker.securitycard;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.iwgame.pconf.exception.PconfNotFoundException;
import com.iwgame.xconnector.exeception.DBConnectFailedException;
import com.iwgame.xconnector.exeception.GMConnectException;
import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.game.model.SecurityCardBean;
import com.iwgame.xengine.xtask.game.service.impl.SecurityCardTaskServiceImpl;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;
import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.tools.XtaskTools;
import com.iwgame.xvalidators.Xvalidator;

public class SecurityCardTask extends AbstractMQWorker {

	private final Logger logger = Logger.getLogger("game");
	
	@Resource
	private SecurityCardTaskServiceImpl securityCardTaskServiceImpl;
	
	@Resource
	private XtaskTools xtaskTools;

	/**
	 * 密保卡
	 * 
	 * @param source
	 */
	public void handleMessage(String source) {
		logger.info("密保卡=>收到请求资源: " + source);
		
		final String pid = ConfigProperties.getString("PID");
		SupportLog monitorlog = new SupportLog();
		monitorlog.setBizcode(22);
		monitorlog.setResource(source);
		monitorlog.setProductid(pid);

		try {
			SecurityCardBean securityCardBean = new SecurityCardBean(source);
			
			monitorlog.setAppname(securityCardBean.getAppname());
			monitorlog.setVal1(securityCardBean.getName());
			monitorlog.setVal2(securityCardBean.getGuid());
			
			boolean checking = Xvalidator.getInstance().validate(securityCardBean);
			
			if(checking){
				securityCardTaskServiceImpl.securityCardActivity(securityCardBean);
				monitorlog.setLogtype(0);
				monitorlog.setLognote("[密保卡]绑定&解绑成功!");
			}else{
				monitorlog.setLognote("[密保卡]请求的必要参数为空或者NULL,忽略此条消息...");
			}
		} catch (BadSqlGrammarException e) {
			monitorlog.setLognote("[密保卡]失败,发送到备份通道,连接异常"+e.getMessage());
			securityCardTaskServiceImpl.reSendMQBackupSecurityCardActivate(source);
		} catch (GMConnectException e) {
			monitorlog.setLognote("[密保卡]失败,发送到备份通道,连接异常"+e.getMessage());
			securityCardTaskServiceImpl.reSendMQBackupSecurityCardActivate(source);
		} catch (CannotGetJdbcConnectionException e) {
			monitorlog.setLognote("[密保卡]失败,发送到备份通道,连接异常"+e.getMessage());
			securityCardTaskServiceImpl.reSendMQBackupSecurityCardActivate(source);
		} catch (PconfNotFoundException e) {
			monitorlog.setLognote("[密保卡]失败,发送到备份通道,连接异常"+e.getMessage());
			securityCardTaskServiceImpl.reSendMQBackupSecurityCardActivate(source);
		} catch (DBConnectFailedException e) {
			monitorlog.setLognote("[密保卡]失败,发送到备份通道,连接异常"+e.getMessage());
			securityCardTaskServiceImpl.reSendMQBackupSecurityCardActivate(source);
		} catch (Exception e) {
			monitorlog.setLognote("[密保卡]失败,忽略,其他异常"+e.getMessage());
			logger.error("[忽略]密保卡业务发送失败：当前请求资源：[" + source + "]ExceptionInfo:" + e);
		}finally{
			try {
				xtaskTools.saveXtaskLogInfo(monitorlog);
			} catch (Exception ex) {
				logger.info("[xtask-support][密保卡]存储日志异常！");
			}
		}
	}
}
