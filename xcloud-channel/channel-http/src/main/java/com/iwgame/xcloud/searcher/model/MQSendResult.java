/****************************************************************
 *  文件名     ： MQSendResult.java
 *  日期         :  2012-9-7
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.sf.json.JSONObject;

/**
 * 
 * @类名:   MQSendResult 
 * @描述:  	返回值,javaBean
 * 
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24上午11:00:11
 * @版本:   1.0
 */
@XmlRootElement(name = "result")
public class MQSendResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8422074553359633051L;
	
	
	
	private int result;
	
	/**
	 * 
	 */
	public MQSendResult() {
	}

	/**
	 * 
	 * @说明: 结果值
	 * @return: int
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
