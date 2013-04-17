/**      
 * PolicyTask.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.worker.context;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.killer.model.PolicyMeta;
import com.iwgame.xengine.xtask.killer.service.ContextService;

/**
 * @ClassName: PolicyTask
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-11 下午03:02:35
 * @Version 1.0
 * 
 */
public class PolicyTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(PolicyTask.class);

	private ContextService contextService;

	@Autowired
	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	public void handleMessage(final String text) {
		logger.info("监听到策略更新数据：" + text);
		// 1 启用 2 延时 3 停用
		// {"id":1,"modifier":"maiqi","type":2}
		PolicyMeta bean = (PolicyMeta) JSONObject.toBean(JSONObject.fromObject(text), PolicyMeta.class);
		if (bean.getType() == 1) {
			contextService.loadActivedKillPolicy(bean);
		} else if (bean.getType() == 2) {
			contextService.prolongActivedKillPolicy(bean);
		} else if (bean.getType() == 3) {
			contextService.deactiveKillPolicy(bean);
		}
		contextService.cleanCounter(bean.getMac());
	}
}
