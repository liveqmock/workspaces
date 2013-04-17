/**      
 * ChannelDayData.java Create on 2012-4-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.shared.model;

/**
 * @ClassName: ChannelDayData
 * @Description: 通道日监控数据
 * @author 吴江晖
 * @date 2012-4-11 下午05:08:02
 * @Version 1.0
 * 
 */
public class ChannelDayData extends DayData {

	private static final long serialVersionUID = -3292171223705110002L;
	/**
	 * 通道ID
	 */
	private String channelId;
	/**
	 * 通道名
	 */
	private String channelName;
	/**
	 * 耗时
	 */
	private int costtime;

	private Long monitorStatus;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(final String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(final String channelName) {
		this.channelName = channelName;
	}

	public int getCosttime() {
		return costtime;
	}

	public void setCosttime(final int costtime) {
		this.costtime = costtime;
	}

	public Long getMonitorStatus() {
		return monitorStatus;
	}

	public void setMonitorStatus(final Long monitorStatus) {
		this.monitorStatus = monitorStatus;
	}

}
