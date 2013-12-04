/****************************************************************
 *  系统名称  ： '广告系统-[xportal-task]'
 *  文件名    ： CustomReportJob.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.process;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.task.service.CustomReportService;
import com.iwgame.gm.p1.task.util.Constant;

/**
 * 类说明
 * @简述： 定制报表task
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-11-13 下午03:49:04
 */
public class CustomReportJob {
	private static Logger logger = Logger.getLogger(Constant.LOG_CUSTOM_REPORT);
	private CustomReportService customReportService;
	
	@Autowired
	public void setCustomReportService(CustomReportService customReportService) {
		this.customReportService = customReportService;
	}

	/**
     * 生成定制报表任务
     */
	public void work() {
		long startTime = System.currentTimeMillis();
		logger.info("开始本次定制报表任务...");
		try {
			customReportService.createCustomReport();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[CustomReportJob]" + e+"  错误原因为:"+e.getCause());
		}
		long endTime = System.currentTimeMillis();
		long costTime = (endTime - startTime)/1000;
		logger.info("结束本次定制报表任务,耗时 【"+costTime+"】 秒...\n");
	}



}
