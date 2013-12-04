/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： FlumeConfig.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.shared.model;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

/**
 * 类说明
 * 
 * @简述： 通道配置对象
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-25 下午9:05:56
 */
public class FlumeCnodeConfig {

	private String cnodeId;
	private String flowId;
	private String source;
	private String sink;

	private static FlumeCnodeConfig fCnodeConfig;

	private FlumeCnodeConfig() {
	};

	public static FlumeCnodeConfig make(final String configJsonInfo) {
		fCnodeConfig = new FlumeCnodeConfig();
		try {
			JSONObject jsonObject = JSONParser.parseStrict(configJsonInfo)
					.isObject();
			fCnodeConfig.cnodeId = jsonObject.get("cnodeId").isString()
					.stringValue();
			fCnodeConfig.flowId = jsonObject.get("flowId").isString()
					.stringValue();
			fCnodeConfig.source = jsonObject.get("source").isString()
					.stringValue();
			fCnodeConfig.sink = jsonObject.get("sink").isString().stringValue();
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
