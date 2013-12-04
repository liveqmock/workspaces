/****************************************************************
 *  文件名     ： TimeScopeSelectorEvent.java
 *  日期         :  2012-10-23
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.event;

import com.google.gwt.event.shared.GwtEvent;

/** 
 * @ClassName:    TimeScopeSelectorEvent 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-23下午12:26:17
 * @Version:      1.0 
 */
public class TimeScopeSelectorEvent extends GwtEvent<TimeScopeSelectorHandler>{
	
	private final String selectIndex;
	
	public static final GwtEvent.Type<TimeScopeSelectorHandler> TYPE = new GwtEvent.Type<TimeScopeSelectorHandler>();

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<TimeScopeSelectorHandler> getAssociatedType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(TimeScopeSelectorHandler handler) {
		handler.onSelected(this);
	}

	/**
	 * 
	 */
	public TimeScopeSelectorEvent(String selectIndex) {
		super();
		this.selectIndex = selectIndex;
	}

	public String getSelectIndex() {
		return selectIndex;
	}
}
