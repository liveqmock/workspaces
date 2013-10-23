package com.iwgame.ioms.common;

import java.util.Date;


public class TaskCmd {
	//命令执行编号，每个脚本执行均需传递，唯一标识
	private String taskId;
	//执行命令的节点编号列表
	private String nodeIps;
	//执行命令详情
	private String req;
	//发起请求时间
	private Date reqDate;
	//获得响应完成时间
	private Date rspDate;
	//响应内容
	private String rsp;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getNodeIps() {
		return nodeIps;
	}
	public void setNodeIps(String nodeIps) {
		this.nodeIps = nodeIps;
	}
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
	public Date getReqDate() {
		return reqDate;
	}
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	public Date getRspDate() {
		return rspDate;
	}
	public void setRspDate(Date rspDate) {
		this.rspDate = rspDate;
	}
	public String getRsp() {
		return rsp;
	}
	public void setRsp(String rsp) {
		this.rsp = rsp;
	}
}
