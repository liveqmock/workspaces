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
import com.iwgame.xengine.xtask.cps.context.CRL.Type;
import com.iwgame.xengine.xtask.cps.service.ContextService;

/**
 * @ClassName: UpdateCRLTask
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-11 下午03:43:58
 * @Version 1.0
 * 
 */
public class UpdateCRLTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(UpdateCRLTask.class);

	private ContextService contextService;

	@Autowired
	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	public void handleMessage(final String text) {
		JSONObject json = JSONObject.fromObject(text);
		if (json.containsKey("type")) {
			logger.info("从MQ中取得一条CPS更新黑名单消息:" + text);
			try {
				Type type = Type.valueOf(json.getString("type").toUpperCase());
				contextService.updateCRL(type);
			} catch (Exception e) {
				logger.error("黑名单类型" + json.getString("type") + "错误");
				logger.error(e.getMessage());
			}
		} else {
			logger.info("更新黑名单消息格式错误：" + text);
		}

	}
}
