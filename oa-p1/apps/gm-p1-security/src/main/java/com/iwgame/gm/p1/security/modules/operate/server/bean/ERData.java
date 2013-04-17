/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： ERData.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.server.bean;
/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-10-15 下午05:10:10
 */
public class ERData {
	/**
	 * 交换机名称
	 */
	private String exchangeName;
	/**
	 * 路由名称
	 */
	private String routinKeyName;

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getRoutinKeyName() {
		return routinKeyName;
	}

	public void setRoutinKeyName(String routinKeyName) {
		this.routinKeyName = routinKeyName;
	}
}
