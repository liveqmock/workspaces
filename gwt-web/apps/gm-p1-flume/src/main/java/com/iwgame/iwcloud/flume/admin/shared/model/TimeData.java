/**      
 * TimeData.java Create on 2012-4-17     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @ClassName: TimeData
 * @Description: 时段监控数据
 * @author 吴江晖
 * @date 2012-4-17 下午02:18:45
 * @Version 1.0
 * 
 */
public class TimeData implements Serializable, IsSerializable {

	private static final long serialVersionUID = 4796304019385109931L;
	private Date time;
	private int fetcherCount;
	private int sinkCount;
	private int costTime;

	public Date getTime() {
		return time;
	}

	public void setTime(final Date time) {
		this.time = time;
	}

	public int getFetcherCount() {
		return fetcherCount;
	}

	public void setFetcherCount(final int fetcherCount) {
		this.fetcherCount = fetcherCount;
	}

	public int getSinkCount() {
		return sinkCount;
	}

	public void setSinkCount(final int sinkCount) {
		this.sinkCount = sinkCount;
	}

	public int getCostTime() {
		return costTime;
	}

	public void setCostTime(final int sink_cost_time) {
		this.costTime = sink_cost_time;
	}

}
