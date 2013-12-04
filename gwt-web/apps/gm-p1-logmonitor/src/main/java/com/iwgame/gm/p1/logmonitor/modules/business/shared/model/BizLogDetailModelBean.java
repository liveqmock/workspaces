/**      
 * BizLogDetailModelBean.java Create on 2012-9-4     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: BizLogDetailModelBean
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-4 上午11:59:16
 * @Version 1.0
 * 
 */
public class BizLogDetailModelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6350541750755853561L;

	private int id;

	private Date requestTime;

	private String requestIp;

	private String requestPid;

	private String requestUsername;

	private int requestStatus;

	private String requesetSource;

	private String requestType;

	private String requestMsg;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(final Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(final String requestIp) {
		this.requestIp = requestIp;
	}

	public String getRequestPid() {
		return requestPid;
	}

	public void setRequestPid(final String requestPid) {
		this.requestPid = requestPid;
	}

	public String getRequestUsername() {
		return requestUsername;
	}

	public void setRequestUsername(final String requestUsername) {
		this.requestUsername = requestUsername;
	}

	public int getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(final int requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getRequesetSource() {
		return requesetSource;
	}

	public void setRequesetSource(final String requesetSource) {
		this.requesetSource = requesetSource;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(final String requestType) {
		this.requestType = requestType;
	}

	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(final String requestMsg) {
		this.requestMsg = requestMsg;
	}

}
