/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： GameResponse.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.jjl.sq.webservice.impl;
/**
 * 类说明
 * @简述： 游戏研发方 WEBSERVICE返回对象
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午06:05:51
 */
public class GameResponse {
	private int returnCode; // 返回码
	private String contentType; // 内容类型;这里都设为json
	private String content;// 发放失败的用户列表
	
	
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
