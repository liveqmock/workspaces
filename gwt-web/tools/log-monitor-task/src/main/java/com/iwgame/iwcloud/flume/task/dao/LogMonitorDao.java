/****************************************************************
 *  系统名称  ： '业务日志监控-预警任务系统'
 *  文件名    ： LogMonitorDao.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.task.dao;

import java.util.List;
import java.util.Map;

import com.iwgame.iwcloud.flume.task.bean.MonitorPolicy;
import com.iwgame.iwcloud.flume.task.bean.RptMonitorLog;

/**
 * 类说明
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-8-30 下午4:59:36
 */
public interface LogMonitorDao {

	 //获取监控预警的策略列表
	 public List<MonitorPolicy> getLogMonitorPolicy();
	 
	 //获取监控报告分时数据
	 public List<RptMonitorLog> getRptMonitorLog(Map<String, Object> paramMap);
	 
	 //添加到预警列表
	 public void saveMonitorWarningLog(Map<String, Object> paramMap);
}
