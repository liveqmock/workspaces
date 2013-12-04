/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： FlumeConfig.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.shared.model;

/**
 * 类说明
 * 
 * @简述： 通道配置对象
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-25 下午9:05:56
 */
public class FlumeAnodeConfigForServer {

	private String anodeId;
	private String flowId;
	private String source;
	private String sink;

	private static FlumeAnodeConfigForServer fAnodeConfig;

	private FlumeAnodeConfigForServer() {
	};

	public static FlumeAnodeConfigForServer make(final String configJsonInfo) {
		fAnodeConfig = new FlumeAnodeConfigForServer();
		try {
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(configJsonInfo);
			fAnodeConfig.anodeId = jsonObject.getString("anodeId");
			fAnodeConfig.flowId = jsonObject.getString("flowId");
			fAnodeConfig.source = jsonObject.getString("source");
			fAnodeConfig.sink = jsonObject.getString("sink");
			return fAnodeConfig;
		} catch (Exception e) {
			return null;
		}

	}

	public String getAnodeId() {
		return anodeId;
	}

	public String getFlowId() {
		return flowId;
	}

	public String getSource() {
		return source;
	}

	public String getSink() {
		return sink;
	}

	public void setAnodeId(final String anodeId) {
		this.anodeId = anodeId;
	}

	public void setFlowId(final String flowId) {
		this.flowId = flowId;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public void setSink(final String sink) {
		this.sink = sink;
	}

	public String produceAnodeConfigCommand() {
		return "exec config '" + anodeId + "' '" + flowId + "' '" + source
				+ "' '" + sink + "'";
	}

}
