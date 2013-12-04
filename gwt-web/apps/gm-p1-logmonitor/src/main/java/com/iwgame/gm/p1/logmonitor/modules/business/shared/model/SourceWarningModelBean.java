/**      
 * SourceWarningModelBean.java Create on 2012-9-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.shared.model;

import java.io.Serializable;

/**
 * @ClassName: SourceWarningModelBean
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-11 上午11:09:12
 * @Version 1.0
 * 
 */
public class SourceWarningModelBean implements Serializable {

	private static final long serialVersionUID = 3827927041853604572L;

	private String name;

	private String type;

	private int hourSuccess;

	private int hourFailed;

	private int hourTotal;

	private int dayTotal;

	private int threshold;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public int getHourTotal() {
		return hourTotal;
	}

	public void setHourTotal(final int hourTotal) {
		this.hourTotal = hourTotal;
	}

	public int getDayTotal() {
		return dayTotal;
	}

	public void setDayTotal(final int dayTotal) {
		this.dayTotal = dayTotal;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(final int threshold) {
		this.threshold = threshold;
	}

	public int getHourSuccess() {
		return hourSuccess;
	}

	public void setHourSuccess(final int hourSuccess) {
		this.hourSuccess = hourSuccess;
	}

	public int getHourFailed() {
		return hourFailed;
	}

	public void setHourFailed(final int hourFailed) {
		this.hourFailed = hourFailed;
	}

}
