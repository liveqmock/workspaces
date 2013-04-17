/**      
 * WarningModelBean.java Create on 2012-9-4     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.shared.model;

import java.io.Serializable;

/**
 * @ClassName: WarningModelBean
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-4 下午03:22:59
 * @Version 1.0
 * 
 */
public class WarningModelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8813745050613078728L;

	private int id;

	private String monitorDate;

	private String monitorType;

	private int successCount;

	private int failedCount;

	private int maxCount;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getMonitorDate() {
		return monitorDate;
	}

	public void setMonitorDate(final String monitorDate) {
		this.monitorDate = monitorDate;
	}

	public String getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(final String monitorType) {
		this.monitorType = monitorType;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(final int successCount) {
		this.successCount = successCount;
	}

	public int getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(final int failedCount) {
		this.failedCount = failedCount;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(final int maxCount) {
		this.maxCount = maxCount;
	}

}
