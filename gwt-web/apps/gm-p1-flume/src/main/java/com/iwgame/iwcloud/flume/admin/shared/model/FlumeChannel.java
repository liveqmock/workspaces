/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： FlumeChannel.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 类说明
 * 
 * @简述： 采集通道
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-25 下午2:41:15
 */
public class FlumeChannel implements IsSerializable, Serializable {
	private static final long serialVersionUID = -4868874769738049181L;

	private String channelId; // 通道ID，全局唯一的
	private String channelName; // 通道名称
	private int status; // 通道开启状态，0：正常；1：关闭（因为合服原因关闭）
	private String productId; // 产品编号，主要用于通过该字段来过滤通道

	private int port;
	private String anodeId;
	private String cnodeId;

	private String aNodeConfig; // Anode配置命令
	private String aNodeStatus; // Anode状态
	private String cNodeConfig; // Cnode配置命令
	private String cNodeStatus; // Cnode状态

	private String room; // 机房信息，flume_nodes表中的t_nodename+t_ip

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(final String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(final String channelName) {
		this.channelName = channelName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	public String getaNodeConfig() {
		return aNodeConfig;
	}

	public void setaNodeConfig(final String aNodeConfig) {
		this.aNodeConfig = aNodeConfig;
	}

	public String getaNodeStatus() {
		return aNodeStatus;
	}

	public void setaNodeStatus(final String aNodeStatus) {
		this.aNodeStatus = aNodeStatus;
	}

	public String getcNodeConfig() {
		return cNodeConfig;
	}

	public void setcNodeConfig(final String cNodeConfig) {
		this.cNodeConfig = cNodeConfig;
	}

	public String getcNodeStatus() {
		return cNodeStatus;
	}

	public void setcNodeStatus(final String cNodeStatus) {
		this.cNodeStatus = cNodeStatus;
	}

	public int getPort() {
		return port;
	}

	public void setPort(final int port) {
		this.port = port;
	}

	public String getAnodeId() {
		return anodeId;
	}

	public void setAnodeId(final String anodeId) {
		this.anodeId = anodeId;
	}

	public String getCnodeId() {
		return cnodeId;
	}

	public void setCnodeId(final String cnodeId) {
		this.cnodeId = cnodeId;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(final String room) {
		this.room = room;
	}

	public String getMapConfig() {
		StringBuffer sb = new StringBuffer();
		sb.append("exec map '" + anodeId + "' '" + channelId.replace("flow", "anode") + "'  \n");
		// sb.append("exec map '" + cnodeId + "' '"
		// + channelId.replace("flow", "cnode") + "'  \n");
		return sb.toString();
	}
}
