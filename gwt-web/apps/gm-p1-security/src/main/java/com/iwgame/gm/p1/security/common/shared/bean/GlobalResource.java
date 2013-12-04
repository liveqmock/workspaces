/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： GlobalResoures.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.bean;
/**
 * 类说明
 * @简述： 常量设置
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-12-5 下午02:34:43
 */
public class GlobalResource {

	private int onlineMaxNum;
	private int mailnoticeMaxNum;
	private int fileMaxSize;
	private int fileMaxLine;
	private int exportMaxNum;
	
	public int getExportMaxNum() {
		return exportMaxNum;
	}
	public void setExportMaxNum(int exportMaxNum) {
		this.exportMaxNum = exportMaxNum;
	}
	public int getOnlineMaxNum() {
		return onlineMaxNum;
	}
	public void setOnlineMaxNum(int onlineMaxNum) {
		this.onlineMaxNum = onlineMaxNum;
	}
	public int getMailnoticeMaxNum() {
		return mailnoticeMaxNum;
	}
	public void setMailnoticeMaxNum(int mailnoticeMaxNum) {
		this.mailnoticeMaxNum = mailnoticeMaxNum;
	}
	public int getFileMaxSize() {
		return fileMaxSize;
	}
	public void setFileMaxSize(int fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}
	public int getFileMaxLine() {
		return fileMaxLine;
	}
	public void setFileMaxLine(int fileMaxLine) {
		this.fileMaxLine = fileMaxLine;
	}
}
