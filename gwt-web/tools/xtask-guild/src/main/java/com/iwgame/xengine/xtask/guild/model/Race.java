/**      
 * Race.java Create on 2012-7-1     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.model;

import java.util.Date;

/**
 * @ClassName: Race
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-1 下午03:29:30
 * @Version 1.0
 * 
 */
public class Race {

	private int id;
	private String formula;
	private Date startDate;
	private Date endDate;
	private Date serverStartDate;
	private Date serverEndDate;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(final String formula) {
		this.formula = formula;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public Date getServerStartDate() {
		return serverStartDate;
	}

	public void setServerStartDate(final Date serverStartDate) {
		this.serverStartDate = serverStartDate;
	}

	public Date getServerEndDate() {
		return serverEndDate;
	}

	public void setServerEndDate(final Date serverEndDate) {
		this.serverEndDate = serverEndDate;
	}

}
