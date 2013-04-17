/**      
 * CacheUser.java Create on 2012-5-15     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.model;

import java.io.Serializable;
import java.util.Date;

import com.iwgame.xengine.xtask.cps.context.ProfitSharingPolicy;
import com.iwgame.xengine.xtask.cps.context.ProfitSharingPolicy.AwardPolicy;
import com.iwgame.xengine.xtask.cps.context.ProfitSharingPolicy.BounsPolicy;

/**
 * @ClassName: CacheUser
 * @Description: 缓存用户结构
 * @author 吴江晖
 * @date 2012-5-15 下午02:31:40
 * @Version 1.0
 * 
 */
public class CacheUser implements Serializable {

	private static final long serialVersionUID = -4093517166049118110L;
	private long accountId;
	private String name;
	private int mediaId;
	private int linkId;
	private Date createTime;
	private Date loginTime;

	private double bonus;
	private double levelTotalBonus;
	private double levelQuota;
	private double bonusTheory;

	private boolean blocked;
	private ProfitSharingPolicy profitSharingPolicy;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(final long accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(final int mediaId) {
		this.mediaId = mediaId;
	}

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(final int linkId) {
		this.linkId = linkId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(final Date loginTime) {
		this.loginTime = loginTime;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(final boolean blocked) {
		this.blocked = blocked;
	}

	public ProfitSharingPolicy getProfitSharingPolicy() {
		return profitSharingPolicy;
	}

	public void setProfitSharingPolicy(
			final ProfitSharingPolicy profitSharingPolicy) {
		this.profitSharingPolicy = profitSharingPolicy;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(final double bonus) {
		this.bonus = bonus;
	}

	public double getLevelTotalBonus() {
		return levelTotalBonus;
	}

	public void setLevelTotalBonus(final double levelTotalBonus) {
		this.levelTotalBonus = levelTotalBonus;
	}

	public double getBonusTheory() {
		return bonusTheory;
	}

	public void setBonusTheory(final double bonusTheory) {
		this.bonusTheory = bonusTheory;
	}

	public double getLevelQuota() {
		return levelQuota;
	}

	public void setLevelQuota(final double levelQuota) {
		this.levelQuota = levelQuota;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("accountid=").append(accountId).append(",");
		sb.append("name=").append(name).append(",");
		sb.append("blocked=").append(blocked).append(",");
		sb.append("createtime=").append(createTime).append(",");
		sb.append("totalBonus=").append(bonus).append(",");
		sb.append("levelTotalBonus=").append(levelTotalBonus).append(",");
		sb.append("bonusPolicy=[");
		for (BounsPolicy bp : profitSharingPolicy.getBounsPolicies()) {
			sb.append("[");
			sb.append("min=").append(bp.getMin()).append(",");
			sb.append("max=").append(bp.getMax()).append(",");
			sb.append("props=").append(bp.getProps()).append(",");
			sb.append("quota=").append(bp.getQuota());
			sb.append("]");
		}
		sb.append("],");
		sb.append("awardPolicy=[");
		for (AwardPolicy bp : profitSharingPolicy.getAwardPolicies()) {
			sb.append("[");
			sb.append("consume=").append(bp.getConsume()).append(",");
			sb.append("award=").append(bp.getAward());
			sb.append("]");
		}
		sb.append("]");
		return sb.toString();
	}
}
