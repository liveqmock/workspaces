/****************************************************************
 *  文件名     ： SmsConfig.java
 *  日期         :  2012-9-20
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xtask.support.model;

/**
 * 
 * @类名:   SmsConfig 
 * @描述:  	短信发送的用户和密码配置
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-09-25上午09:54:03
 * @版本:   1.0
 */
public class SmsConfig {
	
	private String pid ;
	
	private String pwd ;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
