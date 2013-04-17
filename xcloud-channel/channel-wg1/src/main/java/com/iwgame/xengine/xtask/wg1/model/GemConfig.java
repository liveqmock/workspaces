/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： GemConfig.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.model;

import java.io.Serializable;

/**
 * 类说明
 * @简述： 宝石赠送策略
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-10 下午11:45:11
 */
public class GemConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6330418162352361447L;
	
	private String g_pay_amount; //充值金币	
	private String base_id;  //宝石ID
	private String base_name; //宝石名称
	private String base_num;  //宝石数量
	
	public String getG_pay_amount() {
		return g_pay_amount;
	}
	public void setG_pay_amount(String g_pay_amount) {
		this.g_pay_amount = g_pay_amount;
	}
	public String getBase_id() {
		return base_id;
	}
	public void setBase_id(String base_id) {
		this.base_id = base_id;
	}
	public String getBase_name() {
		return base_name;
	}
	public void setBase_name(String base_name) {
		this.base_name = base_name;
	}
	public String getBase_num() {
		return base_num;
	}
	public void setBase_num(String base_num) {
		this.base_num = base_num;
	}

}
