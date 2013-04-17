/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： ADDataBaseMinute.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-19 下午3:14:55
 */
public class ADDataBaseMinute  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7940035940222280151L;

	private int counts;
	private List<ADDataBase> adDate;
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public List<ADDataBase> getAdDate() {
		return adDate;
	}
	public void setAdDate(List<ADDataBase> adDate) {
		this.adDate = adDate;
	}
	
}

