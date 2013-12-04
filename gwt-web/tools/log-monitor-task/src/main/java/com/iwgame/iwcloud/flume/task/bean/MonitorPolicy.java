/****************************************************************
 *  系统名称  ： '业务日志监控-预警任务系统'
 *  文件名    ： MonitorPolicy.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.task.bean;
/**
 * 类说明
 * @简述： 监控策略文件
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-8-30 下午4:55:00
 */
public class MonitorPolicy {
	private String tMonitorType;	//策略标志号	 varchar(20)	 如：ip，account，btype
	private String tMonitorName;	//策略名	 varchar(20)	 如: 按IP，按帐号，按模块
	private int tStatus;	 		//开关	 int(11)	 0:表示开，1：表示关
	private int tMaxCount;	 	//警戒线数值	 int(11)	 
	private String tRevMobile;	 	//接收的手机联系人
	
	
	public String gettMonitorType() {
		return tMonitorType;
	}
	public void settMonitorType(String tMonitorType) {
		this.tMonitorType = tMonitorType;
	}
	public String gettMonitorName() {
		return tMonitorName;
	}
	public void settMonitorName(String tMonitorName) {
		this.tMonitorName = tMonitorName;
	}
	public int gettStatus() {
		return tStatus;
	}
	public void settStatus(int tStatus) {
		this.tStatus = tStatus;
	}
	public int gettMaxCount() {
		return tMaxCount;
	}
	public void settMaxCount(int tMaxCount) {
		this.tMaxCount = tMaxCount;
	}
	public String gettRevMobile() {
		return tRevMobile;
	}
	public void settRevMobile(String tRevMobile) {
		this.tRevMobile = tRevMobile;
	}
}
