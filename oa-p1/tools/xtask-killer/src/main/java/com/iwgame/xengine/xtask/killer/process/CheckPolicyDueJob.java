/**      
 * CheckPolicyDueJob.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.process;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.killer.context.Context;
import com.iwgame.xengine.xtask.killer.model.Policy;
import com.iwgame.xengine.xtask.killer.model.PolicyMeta;
import com.iwgame.xengine.xtask.killer.service.ContextService;

/**
 * @ClassName: CheckPolicyDueJob
 * @Description: 检查封杀策略是否过期的定时任务
 * @author 吴江晖
 * @date 2012-7-11 上午11:24:39
 * @Version 1.0
 * 
 */
public class CheckPolicyDueJob {

	private static final Logger logger4j = Logger.getLogger(CheckPolicyDueJob.class);

	private ContextService contextService;

	@Autowired
	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	public void work() {
		logger4j.debug("开始检查是否有过期的封杀策略");
		DateTime now = new DateTime(new Date());
		List<Policy> policies = Context.get().getPolicies();
		int i = 0;
		for (Policy policy : policies) {
			if (now.isAfter(policy.getEndDate().getTime())) {
				logger4j.info("封杀策略【" + policy + "】因过期被停用");
				Context.get().removePolicy(policy.getMac());
				PolicyMeta meta = new PolicyMeta();
				meta.setId(policy.getId());
				contextService.deactiveKillPolicy(meta);
				i++;
			}
		}
		logger4j.debug("检查是否有过期的封杀策略，共有" + i + "条封杀策略因过期被停用");
	}
}
