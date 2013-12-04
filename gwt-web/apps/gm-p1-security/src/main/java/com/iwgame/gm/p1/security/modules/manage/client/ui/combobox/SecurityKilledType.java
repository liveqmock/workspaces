/**      
* KilledCause.java Create on 2012-11-15     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/
package com.iwgame.gm.p1.security.modules.manage.client.ui.combobox;
/** 
 * @简述: 封杀分类枚举
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 */
public enum SecurityKilledType {
	/**
	 * 封杀-1
	 */
	kill("1","封杀"),
	/**
	 * 冻结-2
	 */
	lock("2","冻结");
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
	
	private SecurityKilledType(String value,String title)
	{
		this.value = value;
		this.title = title;
	}
}
