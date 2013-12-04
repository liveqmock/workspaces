/**      
 * IPCRL.java Create on 2012-5-15     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: IPCRL
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-15 上午10:12:27
 * @Version 1.0
 * 
 */
public class IPCRL implements Serializable {

	private static final long serialVersionUID = -6037074271317962183L;
	private String ip;// IP
	private Date endTime;// 解禁时间
	private short status;// 状态

	public String getIp() {
		return ip;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(final short status) {
		this.status = status;
	}

}
