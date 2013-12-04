package com.iwgame.xengine.xtask.game.worker.itemcard;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.iwgame.pconf.exception.PconfNotFoundException;
import com.iwgame.xconnector.exeception.DBConnectFailedException;
import com.iwgame.xconnector.exeception.GMConnectException;
import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.game.model.ItemCardBean;
import com.iwgame.xengine.xtask.game.service.impl.ItemCardXtaskServiceImpl;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;
import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.tools.XtaskTools;
import com.iwgame.xvalidators.Xvalidator;

/**
 * 
 * 类说明
 * 
 * @简述： 道具卡激活入口
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-4-24 下午05:58:14
 */
public class ItemCardTask extends AbstractMQWorker {

	private final Logger logger = Logger.getLogger("itemcard");

	@Resource
	private XtaskTools xtaskTools;

	@Resource
	private ItemCardXtaskServiceImpl cardXtaskServiceImpl;

	/**
	 * 道具卡激活方法入口
	 * 
	 * @param source
	 */
	public void handleMessage(String source) {

		logger.info("道具卡激活=>收到请求资源: " + source);
		
		final String pid = ConfigProperties.getString("PID");
		SupportLog monitorlog = new SupportLog();
		monitorlog.setBizcode(21);
		monitorlog.setResource(source);
		monitorlog.setProductid(pid);
		

		try {
			ItemCardBean itemCard = new ItemCardBean(source);
			monitorlog.setAppname(itemCard.getAppname());
			monitorlog.setVal1(itemCard.getUsername());
			monitorlog.setVal2(itemCard.getGuid());
			boolean flag = Xvalidator.getInstance().validate(itemCard);
			if (flag) {
				cardXtaskServiceImpl.itemCardActivate(itemCard);
				monitorlog.setLogtype(0);
				monitorlog.setLognote("[道具卡激活]成功！");
			} else {
				logger.error("[道具卡激活]失败,请求的必要参数为空或者NULL,忽略此条消息...");
				monitorlog.setLognote("[道具卡激活]失败,请求的必要参数为空或者NULL,忽略此条消息...");
			}
		} catch (BadSqlGrammarException e) {
			monitorlog.setLognote("[道具卡激活]失败,发送到备份通道,连接异常"+e.getMessage());
			cardXtaskServiceImpl.reSendMQBackupItemCardActivate(source);
		} catch (GMConnectException e) {
			monitorlog.setLognote("[道具卡激活]失败,发送到备份通道,连接异常"+e.getMessage());
			cardXtaskServiceImpl.reSendMQBackupItemCardActivate(source);
		} catch (CannotGetJdbcConnectionException e) {
			monitorlog.setLognote("[道具卡激活]失败,发送到备份通道,连接异常"+e.getMessage());
			cardXtaskServiceImpl.reSendMQBackupItemCardActivate(source);
		} catch (PconfNotFoundException e) {
			monitorlog.setLognote("[道具卡激活]失败,发送到备份通道,连接异常"+e.getMessage());
			cardXtaskServiceImpl.reSendMQBackupItemCardActivate(source);
		} catch (DBConnectFailedException e) {
			monitorlog.setLognote("[道具卡激活]失败,发送到备份通道,连接异常"+e.getMessage());
			cardXtaskServiceImpl.reSendMQBackupItemCardActivate(source);
		} catch (Exception e) {
			monitorlog.setLognote("[道具卡激活]失败,忽略,其他异常"+e.getMessage());
			logger.error("[忽略]道具卡激活失败,当前请求资源：[" + source + "],异常" + e);
		}finally{
			try {
				xtaskTools.saveXtaskLogInfo(monitorlog);
			} catch (Exception ex) {
				logger.info("[xtask-support][道具卡激活]存储日志异常！");
			}
		}
	}
}
