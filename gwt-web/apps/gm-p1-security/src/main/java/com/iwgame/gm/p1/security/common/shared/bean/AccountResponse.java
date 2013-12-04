/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： AccountResponse.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-27 下午12:30:28
 */
public class AccountResponse implements Serializable{
	private static final long serialVersionUID = -2972379024272394210L;
	private List<AccountDocs> docsList;
	private int numFound;
	private int start;
	
	public List<AccountDocs> getDocsList() {
		return docsList;
	}
	public void setDocsList(List<AccountDocs> docsList) {
		this.docsList = docsList;
	}
	public int getNumFound() {
		return numFound;
	}
	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
}
