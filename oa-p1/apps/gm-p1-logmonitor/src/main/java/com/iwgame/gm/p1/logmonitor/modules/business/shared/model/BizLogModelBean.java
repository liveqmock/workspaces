/**      
 * BizLogModelBean.java Create on 2012-9-3     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: BizLogModelBean
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-3 下午04:15:23
 * @Version 1.0
 * 
 */
public class BizLogModelBean implements Serializable {

	private static final long serialVersionUID = -7760704628160062980L;

	private Integer id;

	private Date date;

	private String source;

	private String type;

	private int successCount;

	private int failedCount;

	private int totalCount;

	private int threshold;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(final int successCount) {
		this.successCount = successCount;
	}

	public int getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(final int failedCount) {
		this.failedCount = failedCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(final int threshold) {
		this.threshold = threshold;
	}

}
