/****************************************************************
 *  系统名称  ： '业务日志监控-预警任务系统'
 *  文件名    ： LogMonitorServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.task.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.iwcloud.flume.task.bean.MonitorPolicy;
import com.iwgame.iwcloud.flume.task.bean.RptMonitorLog;
import com.iwgame.iwcloud.flume.task.dao.LogMonitorDao;
import com.iwgame.iwcloud.flume.task.service.LogMonitorService;
import com.iwgame.iwcloud.flume.task.util.SMSMessageCache;

/**
 * 类说明
 * @简述： 接口服务实现类
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-8-30 下午3:47:07
 */
public class LogMonitorServiceImpl implements LogMonitorService {
	private static Logger logger = Logger.getLogger(LogMonitorServiceImpl.class);
	
	private String smsProduct;
	private LogMonitorDao logMonitorDao;
	private RabbitTemplate smsRabbitTemplate;
	
	@Autowired
	public void setSmsRabbitTemplate(RabbitTemplate smsRabbitTemplate) {
		this.smsRabbitTemplate = smsRabbitTemplate;
	}

	@Autowired
	public void setLogMonitorDao(LogMonitorDao logMonitorDao) {
		this.logMonitorDao = logMonitorDao;
	}
	
	@Autowired
	public void setSmsProduct(String smsProduct) {
		this.smsProduct = smsProduct;
	}

	//业务日志预警，通过轮询数据库，把超过预警的数据写入到预警独立中
	public void trackMonitorLog() {
		try {
			//查询策略
			 List<MonitorPolicy> monitorPolicies = logMonitorDao.getLogMonitorPolicy();
			 if (monitorPolicies == null || monitorPolicies.size() <= 0) {
				 logger.info("[业务日志预警]未找到预警的报警策略信息，忽略预警!");
			 } else {
				 //轮询待检查的类别，分别进行检查
				 for(MonitorPolicy monitorPolicy : monitorPolicies) {
					 logger.info("[业务日志预警]" + monitorPolicy.gettMonitorName() + " ,最大警戒值：" + monitorPolicy.gettMaxCount());
					 checkMonitorData(monitorPolicy);
				 }
			}
		} catch (Exception e) {
			logger.error(e);
		}

	}
	
	/**
	 * 添加到预警列表
	 * @param rptMonitorLog
	 */
	private void add2WarningTable(String policyName,String monitorDate,int maxCount,RptMonitorLog rptMonitorLog) {
		try {
			//构建参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("monitorDate", monitorDate);
			paramMap.put("monitorType", policyName + "| {" + rptMonitorLog.getTname()+"-"+rptMonitorLog.getTtype() +"}");
			paramMap.put("successCount", rptMonitorLog.getTsucess());
			paramMap.put("failedCount", rptMonitorLog.getTfailure());
			paramMap.put("maxCount", maxCount);
			
			//保存数据
			logMonitorDao.saveMonitorWarningLog(paramMap);
		} catch (Exception e) {
			logger.error("[添加到预警列表]" + e);
		}
	}
	
	/**
	 * 按类型检查数据是否超过警戒值(按小时分表)
	 * @param type
	 * @param maxCount
	 */
	private void checkMonitorData(MonitorPolicy monitorPolicy) {
		try {
			Calendar calendar = Calendar.getInstance();
			String monitorDate = new SimpleDateFormat("yyyyMMddHH").format(calendar.getTime());
			String monitorHourTime = new SimpleDateFormat("yyyyMMddHHmm").format(calendar.getTime());
			String hourStart = new SimpleDateFormat("yyyy-MM-dd HH:00:00").format(calendar.getTime());
			String hourEnd = new SimpleDateFormat("yyyy-MM-dd HH:59:59").format(calendar.getTime());

			
			//构建参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("type", monitorPolicy.gettMonitorType());
			paramMap.put("hourStart", hourStart);
			paramMap.put("hourEnd", hourEnd);
			
			//查询数据
			List<RptMonitorLog> reMonitorLogs = logMonitorDao.getRptMonitorLog(paramMap);
			if (reMonitorLogs == null || reMonitorLogs.size() <= 0) {
				logger.info("[业务日志预警]时间:" + hourStart + ",没有数据!");
			} else {
				//检测是否超过预警
				for (RptMonitorLog rptMonitorLog: reMonitorLogs) {
					String msg = "";
					try {
						//如果超过警戒值，则报警
						if (rptMonitorLog.getTfailure() >= monitorPolicy.gettMaxCount() && ifNeedSendMessage(rptMonitorLog,monitorDate)) {
							msg = "["+smsProduct+"]预警提示:业务日志["+rptMonitorLog.getTname() + "-" + rptMonitorLog.getTtype() +"]在"+monitorDate+"小时内,成功处理"+
									rptMonitorLog.getTsucess()+"，失败处理"+rptMonitorLog.getTfailure()+
									"，超过警戒值"+(rptMonitorLog.getTfailure()-monitorPolicy.gettMaxCount())+
									"(警戒值:"+monitorPolicy.gettMaxCount()+"),请登录新OA平台检查!";
							
							//短信通知，
							sendSMSNotice(monitorPolicy.gettRevMobile(), msg);
							//记录到预警表
							add2WarningTable(monitorPolicy.gettMonitorName(),monitorHourTime,monitorPolicy.gettMaxCount(),rptMonitorLog);
						}
					} catch (Exception e) {
						logger.error("[业务日志预警]" + e);
					} finally {
						msg = null;
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("[业务日志预警]" + e);
		}
	}
	
	/**
	 * 短信通知
	 * @param revMobiles
	 * @param msg
	 */
	private void sendSMSNotice(String revMobiles,String smsMessage) {
		try {
			if (revMobiles != null && revMobiles.length() > 0 ) {
				String[] arr = revMobiles.split("\\,");
				for (int i = 0; i < arr.length; i++) {
					smsRabbitTemplate.convertAndSend("common.sms.exchange", "sms3722", "{\"phone\":\""+arr[i]+"\",\"message\":\""+smsMessage+"\"}");
					logger.info("-[发短信通知]：" + arr[i] + ", " + smsMessage);
				}
			}
		} catch (Exception e) {
			logger.error("[短信通知]"+e);
		}
	}

	/**
	 * 判断是否需要发送短信
	 * @param rptMonitorLog
	 * @param monitorDate
	 * @return
	 */
	private boolean ifNeedSendMessage(RptMonitorLog rptMonitorLog,String monitorDate) {
		//规则1,相同小时内，如果间隔时间内的失败记录没有超过100，则不重复发送短信
		String id = rptMonitorLog.getTname() + "-" + rptMonitorLog.getTtype()+"-"+monitorDate;
		int oldCount = SMSMessageCache.getCountById(id);
		if (rptMonitorLog.getTfailure() - oldCount >= 300) {
			SMSMessageCache.add2MsgHashMap(id, rptMonitorLog.getTfailure());
			return true;
		} else {
			return false;
		}
		
	}
}
