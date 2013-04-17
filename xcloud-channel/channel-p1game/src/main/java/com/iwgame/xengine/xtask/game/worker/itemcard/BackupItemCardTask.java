package com.iwgame.xengine.xtask.game.worker.itemcard;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.game.model.ItemCardBean;
import com.iwgame.xengine.xtask.game.service.impl.ItemCardXtaskServiceImpl;
import com.iwgame.xengine.xtask.game.util.ConfigProperties;
import com.iwgame.xtask.support.model.SupportLog;
import com.iwgame.xtask.support.tools.XtaskTools;

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
public class BackupItemCardTask extends AbstractMQWorker {
	
	private final Logger logger = Logger.getLogger("itemcard");
	
	// @Resource
	// private XtaskWebService itemCardXtaskWebService;
	
	@Resource
	private XtaskTools xtaskTools;
	
	@Resource
	private ItemCardXtaskServiceImpl cardXtaskServiceImpl;
	
	/**
	 * 道具卡激活方法入口
	 * 
	 * @param source
	 * @throws Exception
	 */
	public void handleMessage(String source) throws Exception {
		logger.info("收到[道具卡激活-备用通道发放]请求: " + source);
		
		try {
			ItemCardBean itemCard = new ItemCardBean(source);
			cardXtaskServiceImpl.itemCardActivate(itemCard);
			logger.info("[道具卡激活-备用通道发放]操作完成!\n");
			try {
				SupportLog monitorlog = new SupportLog();
				final String pid = ConfigProperties.getString("PID");
				monitorlog.setBizcode(21);
				monitorlog.setResource(source);
				monitorlog.setProductid(pid);
				monitorlog.setAppname(itemCard.getAppname());
				monitorlog.setLogtype(0);
				monitorlog.setVal1(itemCard.getUsername());
				monitorlog.setVal2(itemCard.getGuid());
				monitorlog.setLognote("[FROM备用通道][道具卡激活]成功！");
				xtaskTools.saveXtaskLogInfo(monitorlog);
			} catch (Exception e) {
				
			}
		} catch (Exception e) {
			logger.error("[道具卡激活-备用通道发放异常,等待10秒]：当前请求资源：[" + source + "]ExceptionInfo:" + e);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// we does ...
			}
			throw e;
		}
	}
}
