/**      
 * CRL.java Create on 2012-5-15     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.iwgame.xengine.xtask.cps.model.IPCRL;
import com.iwgame.xengine.xtask.cps.model.UserCRL;

/**
 * @ClassName: CRL
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-15 上午10:43:32
 * @Version 1.0
 * 
 */
public class CRL {

	public enum Type {
		USER, IP;
	}

	private final Map<Long, UserCRL> userCRLs = new HashMap<Long, UserCRL>();
	private final Map<String, IPCRL> ipCRLs = new HashMap<String, IPCRL>();

	public void setUserCRLs(final List<UserCRL> userCRLs) {
		this.userCRLs.clear();
		for (UserCRL userCRL : userCRLs) {
			this.userCRLs.put(userCRL.getAccountId(), userCRL);
		}
	}

	public void setIpCRLs(final List<IPCRL> ipCRLs) {
		this.ipCRLs.clear();
		for (IPCRL ipCRL : ipCRLs) {
			this.ipCRLs.put(ipCRL.getIp(), ipCRL);
		}
	}

	/**
	 * 
	 * @param accountId
	 * @return
	 */
	public boolean blockUser(final long accountId) {
		if (userCRLs.containsKey(accountId)) {
			UserCRL crl = userCRLs.get(accountId);
			if (crl.getStatus() == 1) {
				return true;
			} else {
				DateTime endTime = new DateTime(crl.getEndTime().getTime());
				return endTime.isAfter(System.currentTimeMillis());
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param ip
	 * @return
	 */
	public boolean blockIP(final String ip) {
		if (ipCRLs.containsKey(ip)) {
			IPCRL crl = ipCRLs.get(ip);
			if (crl.getStatus() == 1) {
				return true;
			} else {
				DateTime endTime = new DateTime(crl.getEndTime().getTime());
				return endTime.isAfter(System.currentTimeMillis());
			}
		} else {
			return false;
		}
	}
}
