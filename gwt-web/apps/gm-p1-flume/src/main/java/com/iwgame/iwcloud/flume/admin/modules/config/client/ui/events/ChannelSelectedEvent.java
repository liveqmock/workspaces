/****************************************************************
 *  文件名     ： ChannelSelectedEvent.java
 *  日期         :  2012-8-22
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.modules.config.client.ui.events;

import com.google.gwt.event.shared.GwtEvent;

/** 
 * @ClassName:    ChannelSelectedEvent 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-8-22上午10:32:28
 * @Version:      1.0 
 */
public class ChannelSelectedEvent extends GwtEvent<ChannelSelectedHandler> {
	
	public static final GwtEvent.Type<ChannelSelectedHandler> TYPE=new Type<ChannelSelectedHandler>();
	
	private String channelName;
	
	private String execStr;
	
	private String selectIndexOf;

	/**
	 * @return the execStr
	 */
	public String getExecStr() {
		return execStr;
	}

	/**
	 * @return the selectIndexOf
	 */
	public String getSelectIndexOf() {
		return selectIndexOf;
	}

	/**
	 * @param channelName
	 */
	public ChannelSelectedEvent(String channelName,String execStr,String selectIndexOf) {
		super();
		this.channelName = channelName;
		this.execStr = execStr;
		this.selectIndexOf = selectIndexOf;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ChannelSelectedHandler> getAssociatedType() {
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(ChannelSelectedHandler handler) {
		handler.buildChannelInfo(this);
	}

	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}

	
}
