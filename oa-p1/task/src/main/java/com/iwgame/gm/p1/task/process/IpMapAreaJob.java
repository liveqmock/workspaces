/****************************************************************
 *  系统名称  ： '广告系统-[xportal-task]'
 *  文件名    ： IpMapAreaJob.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.process;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.task.service.AdcollectMonitorService;
import com.iwgame.gm.p1.task.util.Constant;

/**
 * 类说明
 * @简述： 将IP地址翻译成物理地址
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-11-8 下午03:06:45
 */
public class IpMapAreaJob {
	private static Logger logger = Logger.getLogger(Constant.LOG_IP_TRANSLATE);
	private AdcollectMonitorService adcollectMonitorService;
   
	@Autowired
	public void setAdcollectMonitorService(AdcollectMonitorService adcollectMonitorService) {
		this.adcollectMonitorService = adcollectMonitorService;
	}
	
	/**
	 * IP地址翻译任务
	 */
	public void work() {
		long startTime = System.currentTimeMillis();
		logger.info("开始本次IP地址翻译成对应区域任务...");
		try {
			adcollectMonitorService.tanslateIp();
		} catch (Exception e) {
			logger.error("[IpMapAreaJob]" + e);
		}
		long endTime = System.currentTimeMillis();
		long costTime = (endTime - startTime)/1000;
		logger.info("结束本次IP地址翻译成对应区域任务,耗时 【"+costTime+"】 秒...\n");
	}


}
