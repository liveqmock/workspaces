/**      
 * WarningConfigModelBean.java Create on 2012-9-5     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.config.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: WarningConfigModelBean
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-5 下午12:13:36
 * @Version 1.0
 * 
 */
public class WarningConfigModelBean implements Serializable {

	private static final long serialVersionUID = 7545334353492653705L;

	private int id;

	private String monitorType;

	private String monitorName;

	private int status;

	private int maxCount;

	private String recevierMobile;

	private Date lastUpdatetime;

	private String lastUpdateuser;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(final String monitorType) {
		this.monitorType = monitorType;
	}

	public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(final String monitorName) {
		this.monitorName = monitorName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(final int maxCount) {
		this.maxCount = maxCount;
	}

	public String getRecevierMobile() {
		return recevierMobile;
	}

	public void setRecevierMobile(final String recevierMobile) {
		this.recevierMobile = recevierMobile;
	}

	public Date getLastUpdatetime() {
		return lastUpdatetime;
	}

	public void setLastUpdatetime(final Date lastUpdatetime) {
		this.lastUpdatetime = lastUpdatetime;
	}

	public String getLastUpdateuser() {
		return lastUpdateuser;
	}

	public void setLastUpdateuser(final String lastUpdateuser) {
		this.lastUpdateuser = lastUpdateuser;
	}

}
