/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： RoleBean.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.bean;

import java.io.Serializable;

/**
 * 类说明
 * @简述：角色信息
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-22 上午09:28:59
 */
public class RoleBean implements Serializable{
	private static final long serialVersionUID = -4726554869896471032L;
	private int rc;
	private RoleResponse response;
	public int getRc() {
		return rc;
	}
	public void setRc(int rc) {
		this.rc = rc;
	}
	public RoleResponse getResponse() {
		return response;
	}
	public void setResponse(RoleResponse response) {
		this.response = response;
	}
	
	
}
