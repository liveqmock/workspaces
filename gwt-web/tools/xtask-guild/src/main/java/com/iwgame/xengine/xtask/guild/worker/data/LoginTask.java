/**      
 * RegistGuildTask.java Create on 2012-6-21     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.worker.data;

import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.guild.model.CachedJoiner;
import com.iwgame.xengine.xtask.guild.service.ContextService;
import com.iwgame.xengine.xtask.guild.service.DataService;
import com.iwgame.xengine.xtask.guild.util.Tools;

/**
 * @ClassName: LoginTask
 * @Description: 参数玩家登录数据
 * @author 吴江晖
 * @date 2012-6-21 下午02:51:35
 * @Version 1.0
 * 
 */
public class LoginTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(LoginTask.class);

	private ContextService contextService;

	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	private DataService dataService;

	public void setDataService(final DataService dataService) {
		this.dataService = dataService;
	}

	private String encoding;

	public void setEncoding(final String encoding) {
		this.encoding = encoding;
	}

	private boolean transform;

	public void setTransform(final boolean transform) {
		this.transform = transform;
	}

	private String offset;

	public void setOffset(final String offset) {
		this.offset = offset;
	}

	public synchronized void handleMessage(final String text) {
		logger.info("######################    登录数据计算  开始  ######################");
		logger.info("从MQ收到登录数据");
		logger.info("数据为：" + text);

		try {
			JSONObject json = JSONObject.fromObject(text);
			logger.debug("解析后JSON串为：" + json);
			String productId = Tools.getProductId(json.getString("channelId"));
			logger.debug("游戏产品ID=" + productId);
			String eventMsg = json.getString("eventMsg");
			JSONObject msg = JSONObject.fromObject(eventMsg);
			logger.info("解析Msg：" + msg);

			String username = Tools.getUsername(msg.getString("user_name"), transform, encoding,
					Long.valueOf(offset, 16));

			CachedJoiner joiner = contextService.getCachedJoiner(username, false);
			if (joiner != null) {

				SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				String _loginTime = msg.getString("login_time");
				DateTime loginTime = new DateTime(dFormat.parse(_loginTime));
				DateTime logoutTime = null;
				String _logoutTime = msg.getString("logout_time");
				if ((_logoutTime == null) || "".equals(_logoutTime) || "null".equalsIgnoreCase(_logoutTime)) {
					logoutTime = loginTime.plusMinutes(20);
				} else {
					logoutTime = new DateTime(dFormat.parse(_logoutTime));
				}
				long onlineTime = (logoutTime.getMillis() - loginTime.getMillis()) / 1000;

				dataService.updateLogin(productId, username, loginTime.toDate(), onlineTime);
			} else {
				logger.info("抛弃数据！");
			}

		} catch (Exception e) {
			logger.error("登录数据计算错误。" + e.getMessage(), e);
		} finally {
			logger.info("######################    登录数据计算  结束  ######################");
		}

	}
}
