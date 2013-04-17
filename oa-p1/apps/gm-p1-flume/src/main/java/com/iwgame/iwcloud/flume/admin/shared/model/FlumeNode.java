/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： FlumeNodes.java
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
 * @简述： 采集节点对象
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-25 下午2:37:40
 */
public class FlumeNode  implements IsSerializable, Serializable {
	private static final long serialVersionUID = -4868874769738049181L;
	
	private String nodeId;
	private String nodeName;
	private String nodeType;
	private String nodeIpInfo;
	
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getNodeIpInfo() {
		return nodeIpInfo;
	}
	public void setNodeIpInfo(String nodeIpInfo) {
		this.nodeIpInfo = nodeIpInfo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
