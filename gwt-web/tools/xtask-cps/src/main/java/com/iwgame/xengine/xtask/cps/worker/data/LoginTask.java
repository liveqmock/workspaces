/**      
 * LoginTask.java Create on 2012-5-23     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.worker.data;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.cps.model.CacheUser;
import com.iwgame.xengine.xtask.cps.service.ContextService;
import com.iwgame.xengine.xtask.cps.service.XtaskService;
import com.iwgame.xengine.xtask.cps.util.IPTools;

/**
 * @ClassName: LoginTask
 * @Description: 登录数据任务处理器
 * @author 吴江晖
 * @date 2012-5-23 上午09:16:07
 * @Version 1.0
 * 
 */
public class LoginTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(LoginTask.class);

	private ContextService contextService;

	private XtaskService xtaskService;

	@Autowired
	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	@Autowired
	public void setXtaskService(final XtaskService xtaskService) {
		this.xtaskService = xtaskService;
	}

	protected boolean checkData(final JSONObject json) {
		long accountId = Long.valueOf(json.getString("accountid"));
		CacheUser user = contextService.getCacheUser(accountId);
		if (user != null) {
			if (user.getLoginTime() == null) {
				return true;
			} else {
				logger.info("不是首次登录，CPS忽略该数据");
				return false;
			}
		} else {
			logger.info("不是CPS用户，忽略该数据");
			return false;
		}
	}

	public void handleMessage(final String text) {
		logger.info("收到登录数据请求：" + text);
		try {
			JSONObject json = JSONObject.fromObject(text);
			String eventMsg = json.getString("eventMsg");
			JSONObject msg = JSONObject.fromObject(eventMsg);
			if (checkData(msg)) {
				DateTime logintime = new DateTime(Long.valueOf(msg
						.getString("logintime")) * 1000L);
				String ip = IPTools.longToIP(Long.valueOf(msg.getString("ip")));
				Long accountId = Long.valueOf(msg.getString("accountid"));
				logger.info("记录用户[" + accountId + "]首次登录时间："
						+ logintime.toString("yyyy-MM-dd HH:mm:ss") + " 和IP："
						+ ip);
				xtaskService.writeUserLoginTimeAndIp(accountId,
						logintime.toDate(), ip);
				contextService.updateCacheUserLoginTime(accountId,
						logintime.toDate());
			}
		} catch (Exception e) {
			logger.error("登录数据计算异常。" + e.getMessage(), e);
		}
	}
}
