/**      
 * Context.java Create on 2012-5-15     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwgame.xengine.xtask.cps.model.IPCRL;
import com.iwgame.xengine.xtask.cps.model.UserCRL;

/**
 * @ClassName: Context
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-15 上午10:35:43
 * @Version 1.0
 * 
 */
public class Context {

	private final CRL crl;

	private final Map<Integer, ProfitSharingPolicy> profitSharingPolicyTable;

	private double maxBonus;
	private int maxDays;

	private final List<Integer> disableMedias;
	private final List<Integer> disableLinks;

	private static Context instance;

	public static Context get() {
		if (instance == null) {
			instance = new Context();
		}
		return instance;
	}

	private Context() {
		crl = new CRL();
		profitSharingPolicyTable = new HashMap<Integer, ProfitSharingPolicy>();
		disableMedias = new ArrayList<Integer>();
		disableLinks = new ArrayList<Integer>();
	}

	public CRL getCRL() {
		return crl;
	}

	public void updateUserCRLs(final List<UserCRL> userCRLs) {
		crl.setUserCRLs(userCRLs);
	}

	public void updateIPCRLs(final List<IPCRL> ipCRLs) {
		crl.setIpCRLs(ipCRLs);
	}

	public void updateProfitSharingPolicyTable(final Integer mediaId,
			final ProfitSharingPolicy policy) {
		profitSharingPolicyTable.put(mediaId, policy);
	}

	public ProfitSharingPolicy getProfitSharingPolicy(final Integer mediaId) {
		return profitSharingPolicyTable.get(mediaId);
	}

	public double getMaxBonus() {
		return maxBonus;
	}

	public void setMaxBonus(final double maxBonus) {
		this.maxBonus = maxBonus;
	}

	public int getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(final int maxDays) {
		this.maxDays = maxDays;
	}

	public void addDisableMedia(final int mediaId) {
		if (!disableMedias.contains(mediaId)) {
			disableMedias.add(mediaId);
		}
	}

	public void addDisableLink(final int linkId) {
		if (!disableLinks.contains(linkId)) {
			disableLinks.add(linkId);
		}
	}

	public void removeDisableMedia(final int mediaId) {
		if (disableMedias.contains(mediaId)) {
			disableMedias.remove(Integer.valueOf(mediaId));
		}
	}

	public void removeDisableLink(final int linkId) {
		if (disableLinks.contains(linkId)) {
			disableLinks.remove(Integer.valueOf(linkId));
		}
	}

	public boolean existDisableMedia(final int mediaId) {
		return disableMedias.contains(mediaId);
	}

	public boolean existDisableLink(final int linkId) {
		return disableLinks.contains(linkId);
	}
}
