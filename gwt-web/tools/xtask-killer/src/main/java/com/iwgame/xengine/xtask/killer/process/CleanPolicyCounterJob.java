/**      
 * CheckPolicyDueJob.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.process;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.killer.service.ContextService;

/**
 * @ClassName: CheckPolicyDueJob
 * @Description: 执行封杀定时任务
 * @author 吴江晖
 * @date 2012-7-11 上午11:24:39
 * @Version 1.0
 * 
 */
public class CleanPolicyCounterJob {

	private static final Logger logger4j = Logger.getLogger(CleanPolicyCounterJob.class);

	private ContextService contextService;

	@Autowired
	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	public void work() {
		contextService.cleanCounter();
		logger4j.info("所有活跃封杀策略计数器已清零！");
	}
}
