/****************************************************************
 *  系统名称  ： '广告系统任务处理'
 *  文件名    ： ReportParam.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.bean;

import java.io.Serializable;

/**
 * 类说明
 * @简述： 报表参数		
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱：lipan@iwgame.com
 * @修改时间：2012-11-7 下午05:37:15
 */
public class ReportParam implements Serializable{
	private static final long serialVersionUID = 8024624840825662014L;
	
	private String report_type ; //充值类型 1:留存，2：充值,
	private String report_starttime ; //开始时间
	private String report_endtime ; //结束时间
	private String report_range ; //天数 不填为至今
	private String summary_type ; //1：按广告汇总，2：按IP地址汇总(暂不实现)
	private String media_type; //媒体类型
	private String media_id; //媒体
	private String contract_id; //合同ID
	private String ad_id; //广告ID
	private String schedule_id; //排期码
	
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	public String getReport_starttime() {
		return report_starttime;
	}
	public void setReport_starttime(String report_starttime) {
		this.report_starttime = report_starttime;
	}
	public String getReport_endtime() {
		return report_endtime;
	}
	public void setReport_endtime(String report_endtime) {
		this.report_endtime = report_endtime;
	}
	public String getReport_range() {
		return report_range;
	}
	public void setReport_range(String report_range) {
		this.report_range = report_range;
	}
	public String getSummary_type() {
		return summary_type;
	}
	public void setSummary_type(String summary_type) {
		this.summary_type = summary_type;
	}
	public String getMedia_type() {
		return media_type;
	}
	public void setMedia_type(String media_type) {
		this.media_type = media_type;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getContract_id() {
		return contract_id;
	}
	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}

}
