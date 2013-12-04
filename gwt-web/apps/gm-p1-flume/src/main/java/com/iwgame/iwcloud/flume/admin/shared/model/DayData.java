/**      
 * DayData.java Create on 2012-4-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.shared.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @ClassName: DayData
 * @Description: 日监控数据对象
 * @author 吴江晖
 * @date 2012-4-11 下午05:02:52
 * @Version 1.0
 * 
 */
public class DayData implements IsSerializable, Serializable {

	private static final long serialVersionUID = -4868874769738049181L;
	/**
	 * 采集汇总数
	 */
	private int fetcherCount;
	/**
	 * 采集最后更新时间
	 */
	private long fetcherLastTime;
	/**
	 * 采集状态
	 */
	private String fetcherStatus;
	/**
	 * 收集汇总数
	 */
	private int sinkCount;
	/**
	 * 收集最后更新时间
	 */
	private long sinkLastTime;
	/**
	 * 收集状态
	 */
	private String sinkStatus;
	/**
	 * 完成率
	 */
	private String complete;
	/**
	 * 日期：yyyyMMdd
	 */
	private String day;
	/**
	 * 采集耗时
	 */
	private int sinkCostTime;

	/**
	 * 完成率百分比格式化
	 */
	private static NumberFormat nFormat = NumberFormat
			.getPercentInstance(Locale.CHINA);

	/**
	 * 构造函数
	 */
	public DayData() {
		super();
		nFormat.setMaximumFractionDigits(2);// 最多小数位
		nFormat.setMinimumFractionDigits(2);// 最少小数位
	}

	public int getFetcherCount() {
		return fetcherCount;
	}

	public void setFetcherCount(final int fetcherCount) {
		this.fetcherCount = fetcherCount;
	}

	public long getFetcherLastTime() {
		return fetcherLastTime;
	}

	public void setFetcherLastTime(final long fetcherLastTime) {
		this.fetcherLastTime = fetcherLastTime;
	}

	public String getFetcherStatus() {
		return fetcherStatus;
	}

	public void setFetcherStatus(final String fetcherStatus) {
		this.fetcherStatus = fetcherStatus;
	}

	public int getSinkCount() {
		return sinkCount;
	}

	public void setSinkCount(final int sinkCount) {
		this.sinkCount = sinkCount;
	}

	public long getSinkLastTime() {
		return sinkLastTime;
	}

	public void setSinkLastTime(final long sinkLastTime) {
		this.sinkLastTime = sinkLastTime;
	}

	public String getSinkStatus() {
		return sinkStatus;
	}

	public void setSinkStatus(final String sinkStatus) {
		this.sinkStatus = sinkStatus;
	}

	public String getDay() {
		return day;
	}

	public void setDay(final String day) {
		this.day = day;
	}

	public int getSinkCostTime() {
		return sinkCostTime;
	}

	public void setSinkCostTime(final int sinkCostTime) {
		this.sinkCostTime = sinkCostTime;
	}

	/**
	 * 计算完成率<br/>
	 * 完成率=(收集汇总数/采集汇总数)×100%
	 */
	public String getComplete() {
		if (getFetcherCount() == 0) {
			complete = nFormat.format(0);
		} else {
			complete = nFormat
					.format(((double) getSinkCount() / getFetcherCount()));
		}
		return complete;
	}
}
