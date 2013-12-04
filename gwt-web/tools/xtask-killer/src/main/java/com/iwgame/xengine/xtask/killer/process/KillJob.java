/**      
 * CheckPolicyDueJob.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.process;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.killer.service.KillService;

/**
 * @ClassName: CheckPolicyDueJob
 * @Description: 执行封杀定时任务
 * @author 吴江晖
 * @date 2012-7-11 上午11:24:39
 * @Version 1.0
 * 
 */
public class KillJob {

	private static final Logger logger4j = Logger.getLogger(KillJob.class);

	private KillService killService;

	@Autowired
	public void setKillService(final KillService killService) {
		this.killService = killService;
	}

	public void work() {
		logger4j.debug("开始检查是否有封杀事件");
		killService.kill(new Date());
		logger4j.debug("结束检查是否有封杀事件");
	}
}
