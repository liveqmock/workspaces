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
public class FlumeCnodeConfigForServer {

	private String cnodeId;
	private String flowId;
	private String source;
	private String sink;

	private static FlumeCnodeConfigForServer fCnodeConfig;

	private FlumeCnodeConfigForServer() {
	};

	public static FlumeCnodeConfigForServer make(final String configJsonInfo) {
		fCnodeConfig = new FlumeCnodeConfigForServer();
		try {
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
					.fromObject(configJsonInfo);
			fCnodeConfig.cnodeId = jsonObject.getString("cnodeId");
			fCnodeConfig.flowId = jsonObject.getString("flowId");
			fCnodeConfig.source = jsonObject.getString("source");
			fCnodeConfig.sink = jsonObject.getString("sink");
			return fCnodeConfig;
		} catch (Exception e) {
			return null;
		}

	}

	public String getCnodeId() {
		return cnodeId;
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

	public String produceAnodeConfigCommand() {
		return "exec config '" + cnodeId + "' '" + flowId + "' '" + source
				+ "' '" + sink + "'";
	}

}
