/**      
 * UpdateCRLTask.java Create on 2012-5-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.worker.context;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.cps.service.ContextService;

/**
 * @ClassName: UpdateProfitSharingSchemaTask
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-11 下午03:43:58
 * @Version 1.0
 * 
 */
public class UpdateProfitSharingSchemaTask extends AbstractMQWorker {

	private static final Logger logger = Logger
			.getLogger(UpdateProfitSharingSchemaTask.class);

	public enum Type {
		MEDIA, LINK;
	}

	private ContextService contextService;

	@Autowired
	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	public void handleMessage(final String text) {
		logger.info("从MQ中取得一条分成方案更新消息:" + text);
		JSONObject json = JSONObject.fromObject(text);
		int mediaId = json.getInt("mediaId");
		contextService.updateProfitSharingPolicy(mediaId);
	}
}
