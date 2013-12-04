/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： PropertyConfig.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.model;
/**
 * 类说明
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-4-25 下午07:59:31
 */
public class PropertyConfig {
	private String g_pay_amount;  //充值金额
	private String g_reward_gold;   // 金币
	private String g_silver;   // 银币
	private String g_glory;   // 荣誉
	private String g_renown;  // 声望
	private String g_food;   // 食物
	private String g_wood;   // 木材
	private String g_updatetime;   // 更新时间
	
	
	
	public String getG_reward_gold() {
		return g_reward_gold;
	}
	public void setG_reward_gold(String g_reward_gold) {
		this.g_reward_gold = g_reward_gold;
	}
	public String getG_updatetime() {
		return g_updatetime;
	}
	public void setG_updatetime(String g_updatetime) {
		this.g_updatetime = g_updatetime;
	}
	private String g_operater;   // 操作人
	
	public String getG_operater() {
		return g_operater;
	}
	public void setG_operater(String g_operater) {
		this.g_operater = g_operater;
	}
	public String getG_pay_amount() {
		return g_pay_amount;
	}
	public void setG_pay_amount(String g_pay_amount) {
		this.g_pay_amount = g_pay_amount;
	}
	public String getG_silver() {
		return g_silver;
	}
	public void setG_silver(String g_silver) {
		this.g_silver = g_silver;
	}
	public String getG_glory() {
		return g_glory;
	}
	public void setG_glory(String g_glory) {
		this.g_glory = g_glory;
	}
	public String getG_renown() {
		return g_renown;
	}
	public void setG_renown(String g_renown) {
		this.g_renown = g_renown;
	}
	public String getG_food() {
		return g_food;
	}
	public void setG_food(String g_food) {
		this.g_food = g_food;
	}
	public String getG_wood() {
		return g_wood;
	}
	public void setG_wood(String g_wood) {
		this.g_wood = g_wood;
	}
}
