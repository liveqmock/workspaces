/**      
 * UserCRL.java Create on 2012-5-15     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: UserCRL
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-15 上午10:08:38
 * @Version 1.0
 * 
 */
public class UserCRL implements Serializable {

	private static final long serialVersionUID = 1554544382582471806L;
	private long accountId;// 用户帐号ID
	private Date endTime;// 解禁时间
	private short status;// 状态

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(final long accountId) {
		this.accountId = accountId;
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
