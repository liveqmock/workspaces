/**      
 * GroupDayData.java Create on 2012-4-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.shared.model;

/**
 * @ClassName: GroupDayData
 * @Description: 组日监控数据
 * @author 吴江晖
 * @date 2012-4-11 下午02:02:00
 * @Version 1.0
 * 
 */
public class GroupDayData extends DayData {

	private static final long serialVersionUID = 6022791873372313138L;
	/**
	 * 组ID
	 */
	private String groupId;
	/**
	 * 组名
	 */
	private String groupName;
	/**
	 * 组状态
	 */
	private String status;

	private Long monitorStatus;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(final String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 计算组状态<br/>
	 * 分别查看采集状态和收集状态,只要其中一个不为ACTIVE即为异常
	 */
	public String getStatus() {
		if ("ACTIVE".equalsIgnoreCase(getFetcherStatus()) && "ACTIVE".equalsIgnoreCase(getSinkStatus())) {
			status = "ACTIVE";
		} else {
			status = "EXCEPTION";
		}
		return status;
	}

	public Long getMonitorStatus() {
		return monitorStatus;
	}

	public void setMonitorStatus(final Long monitorStatus) {
		this.monitorStatus = monitorStatus;
	}

}
