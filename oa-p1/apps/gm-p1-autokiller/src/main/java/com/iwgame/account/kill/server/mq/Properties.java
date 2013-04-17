/**      
 * Properties.java Create on 2012-6-21     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.account.kill.server.mq;


/**
 * 类说明
 * 
 * @简述： MQ服务
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-11 上午15:14:35
 */
public class Properties {

	private String exchange;
	private String routingUpdatePolicy;

	/**
	 * @return the exchange
	 */
	public String getExchange() {
		return exchange;
	}

	/**
	 * @param exchange the exchange to set
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	/**
	 * @return the routingUpdatePolicy
	 */
	public String getRoutingUpdatePolicy() {
		return routingUpdatePolicy;
	}

	/**
	 * @param routingUpdatePolicy the routingUpdatePolicy to set
	 */
	public void setRoutingUpdatePolicy(String routingUpdatePolicy) {
		this.routingUpdatePolicy = routingUpdatePolicy;
	}


}
