/**      
* KilledCause.java Create on 2012-11-15     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 
package com.iwgame.gm.p1.security.modules.manage.client.ui.combobox;
/** 
 * @简述: 封杀原因分类
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 */
public enum SecurityKilledCauseType {
	/**
	 * 外挂-1
	 */
	hackTool("1","外挂"),
	/**
	 * 盗号-2
	 */
	stealAccount("2","盗号"),
	/**
	 * 账户安全-3
	 */
	accountSecurity("3","账号安全"),
	/**
	 * 其他-4
	 */
	other("4","其他");
	private String value;
	private String title;
	public String getValue()
	{
		return this.value;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	private SecurityKilledCauseType(String value,String title)
	{
		this.value = value;
		this.title = title;
	}
}
