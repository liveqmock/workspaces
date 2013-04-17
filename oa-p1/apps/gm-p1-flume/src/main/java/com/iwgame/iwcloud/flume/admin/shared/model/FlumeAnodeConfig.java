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
public class FlumeAnodeConfig {

	private String anodeId;
	private String flowId;
	private String source;
	private String sink;

	private static FlumeAnodeConfig fAnodeConfig;

	private FlumeAnodeConfig() {
	};

	public static FlumeAnodeConfig make(final String configJsonInfo) {
		fAnodeConfig = new FlumeAnodeConfig();
		try {
			System.out.println(configJsonInfo);
//			String s = "{\"anodeId\":\"\", \"flowId\":\"\", \"source\":\"\", \"sink\":\"\"}";
			JSONObject jsonObject = JSONParser.parseStrict(configJsonInfo)
					.isObject();
			fAnodeConfig.anodeId = jsonObject.get("anodeId").isString()
					.stringValue();
			fAnodeConfig.flowId = jsonObject.get("flowId").isString()
					.stringValue();
			fAnodeConfig.source = jsonObject.get("source").isString()
					.stringValue();
			fAnodeConfig.sink = jsonObject.get("sink").isString().stringValue();
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
