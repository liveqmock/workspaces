/****************************************************************
 *  系统名称  ： '业务日志监控-预警任务系统'
 *  文件名    ： BizLogMonitorTrackJob.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.task.process;

import org.apache.log4j.Logger;

import com.iwgame.iwcloud.flume.task.util.SMSMessageCache;

/**
 * 类说明
 * 
 * @简述： 业务日志监控预警任务
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-8-30 下午4:02:33
 */
public class ClearSMSCacheJob {
	private static Logger logger = Logger.getLogger(ClearSMSCacheJob.class);

	public void work() {
		logger.info("清除短信发送缓存信息...");
		SMSMessageCache.clearMsgHashMap();
	}
}
