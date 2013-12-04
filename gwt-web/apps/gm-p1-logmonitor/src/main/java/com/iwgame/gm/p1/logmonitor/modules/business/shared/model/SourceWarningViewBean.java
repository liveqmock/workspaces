/**      
 * SourceWarningViewBean.java Create on 2012-9-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.shared.model;

import java.io.Serializable;

/**
 * @ClassName: SourceWarningViewBean
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-11 上午11:13:19
 * @Version 1.0
 * 
 */
public class SourceWarningViewBean implements Serializable {

	private static final long serialVersionUID = -6093115000374776102L;

	private String name;

	private int registDayTotal;

	private int registHourTotal;

	private int registHourSuccess;

	private int registHourFailed;

	private int loginDayTotal;

	private int loginHourTotal;

	private int loginHourSuccess;

	private int loginHourFailed;

	private int accountDayTotal;

	private int accountHourTotal;

	private int accountHourSuccess;

	private int accountHourFailed;

	private int threshold;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getRegistDayTotal() {
		return registDayTotal;
	}

	public void setRegistDayTotal(final int registDayTotal) {
		this.registDayTotal = registDayTotal;
	}

	public int getRegistHourTotal() {
		return registHourTotal;
	}

	public void setRegistHourTotal(final int registHourTotal) {
		this.registHourTotal = registHourTotal;
	}

	public int getLoginDayTotal() {
		return loginDayTotal;
	}

	public void setLoginDayTotal(final int loginDayTotal) {
		this.loginDayTotal = loginDayTotal;
	}

	public int getLoginHourTotal() {
		return loginHourTotal;
	}

	public void setLoginHourTotal(final int loginHourTotal) {
		this.loginHourTotal = loginHourTotal;
	}

	public int getAccountDayTotal() {
		return accountDayTotal;
	}

	public void setAccountDayTotal(final int accountDayTotal) {
		this.accountDayTotal = accountDayTotal;
	}

	public int getAccountHourTotal() {
		return accountHourTotal;
	}

	public void setAccountHourTotal(final int accountHourTotal) {
		this.accountHourTotal = accountHourTotal;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(final int threshold) {
		this.threshold = threshold;
	}

	public int getRegistHourSuccess() {
		return registHourSuccess;
	}

	public void setRegistHourSuccess(final int registHourSuccess) {
		this.registHourSuccess = registHourSuccess;
	}

	public int getRegistHourFailed() {
		return registHourFailed;
	}

	public void setRegistHourFailed(final int registHourFailed) {
		this.registHourFailed = registHourFailed;
	}

	public int getLoginHourSuccess() {
		return loginHourSuccess;
	}

	public void setLoginHourSuccess(final int loginHourSuccess) {
		this.loginHourSuccess = loginHourSuccess;
	}

	public int getLoginHourFailed() {
		return loginHourFailed;
	}

	public void setLoginHourFailed(final int loginHourFailed) {
		this.loginHourFailed = loginHourFailed;
	}

	public int getAccountHourSuccess() {
		return accountHourSuccess;
	}

	public void setAccountHourSuccess(final int accountHourSuccess) {
		this.accountHourSuccess = accountHourSuccess;
	}

	public int getAccountHourFailed() {
		return accountHourFailed;
	}

	public void setAccountHourFailed(final int accountHourFailed) {
		this.accountHourFailed = accountHourFailed;
	}

}
