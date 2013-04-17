/****************************************************************
 *  系统名称  ： '业务日志监控-预警任务系统'
 *  文件名    ： BizMonitorLog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.task.bean;
/**
 * 类说明
 * @简述： 业务监控日志
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-8-30 下午4:38:20
 */
public class RptMonitorLog {
	
	private String ttime;	//请求时间，当日期代号为day时，类型为date
	private String tname;	//(类型代号)	 varchar(20)	 
	private String ttype;	//类型	 varchar(20)	 
	private int tsucess;	//成功请求条数	 int(11)	 
	private int tfailure;//失败request_msg;
	
	
	public String getTtime() {
		return ttime;
	}
	public void setTtime(String ttime) {
		this.ttime = ttime;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTtype() {
		return ttype;
	}
	public void setTtype(String ttype) {
		this.ttype = ttype;
	}
	public int getTsucess() {
		return tsucess;
	}
	public void setTsucess(int tsucess) {
		this.tsucess = tsucess;
	}
	public int getTfailure() {
		return tfailure;
	}
	public void setTfailure(int tfailure) {
		this.tfailure = tfailure;
	}

}
