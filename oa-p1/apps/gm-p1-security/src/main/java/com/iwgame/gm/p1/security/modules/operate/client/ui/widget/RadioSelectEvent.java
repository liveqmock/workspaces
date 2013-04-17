/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： RadioSelectEvent.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client.ui.widget;

import com.google.gwt.event.shared.GwtEvent;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-23 下午03:15:30
 */
public class RadioSelectEvent extends GwtEvent<RadioSelectHandler> {
	
	private String selected;

	public static final GwtEvent.Type<RadioSelectHandler> TYPE=new Type<RadioSelectHandler>();
	
	public String getSelected() {
		return selected;
	}

	public RadioSelectEvent(String selected) {
		super();
		this.selected = selected;
	}

	@Override
	public Type<RadioSelectHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RadioSelectHandler handler) {
			handler.onSelect(this);	
	}

	
}
