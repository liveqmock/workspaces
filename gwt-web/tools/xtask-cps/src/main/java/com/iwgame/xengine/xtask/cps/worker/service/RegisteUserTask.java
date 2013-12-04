/**      
 * RegisteUserTask.java Create on 2012-5-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.worker.service;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.cps.model.CacheUser;
import com.iwgame.xengine.xtask.cps.service.ContextService;
import com.iwgame.xengine.xtask.cps.service.XtaskService;

/**
 * @ClassName: RegisteUserTask
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-11 下午03:50:58
 * @Version 1.0
 * 
 */
public class RegisteUserTask extends AbstractMQWorker {

	private static final Logger logger = Logger
			.getLogger(RegisteUserTask.class);

	private XtaskService xtaskService;

	@Autowired
	public void setCpsTaskService(final XtaskService xtaskService) {
		this.xtaskService = xtaskService;
	}

	private ContextService contextService;

	@Autowired
	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	public void handleMessage(final String text) {
		logger.info("从MQ中取得一条CPS注册用户消息:" + text);
		JSONObject json = JSONObject.fromObject(text);

		long accountId = json.getLong("accountId");
		String gameId = json.getString("gameId");
		int mediaId = json.getInt("mediaId");
		int linkId = json.getInt("linkId");
		String username = json.getString("username");
		String ip = json.getString("ip");
		long atime = json.getLong("atime");

		int i = xtaskService.registeUser(gameId, mediaId, linkId, accountId,
				username, ip, atime);
		if (i > 0) {
			logger.info("在缓存中添加CPS用户");
			CacheUser user = new CacheUser();
			user.setAccountId(accountId);
			user.setMediaId(mediaId);
			user.setLinkId(linkId);
			user.setName(username);
			user.setCreateTime(new Date(atime));
			contextService.addCacheUser(user);
		}
	}
}
