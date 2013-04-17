/****************************************************************
 *  系统名称  ： 'P1项目-[gm-p1-analysis]'
 *  文件名    ： Zone.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.analysis.shared.model;

import java.io.Serializable;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-3-27 下午08:16:49
 */
public class Zone implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6867961053637651115L;
	
	private String zonename;  //大区名
    private String zonedesc;  //大区描述
	
    public String getZonename() {
		return zonename;
	}
	public void setZonename(String zonename) {
		this.zonename = zonename;
	}
	public String getZonedesc() {
		return zonedesc;
	}
	public void setZonedesc(String zonedesc) {
		this.zonedesc = zonedesc;
	}
}
