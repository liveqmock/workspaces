/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MaterialHasMenuSelectedEvent.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-25 下午05:44:33
 */
public class MediaSelectedEvent extends GwtEvent<MediaSelectedHandler> {

	public static final GwtEvent.Type<MediaSelectedHandler> TYPE = new Type<MediaSelectedHandler>();

	private int type;

	/**
	 * @return 获取 type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            设置 type
	 */
	public void setType(final int type) {
		this.type = type;
	}

	public MediaSelectedEvent(final int type) {
		this.type = type;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MediaSelectedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(final MediaSelectedHandler handler) {
		handler.selectMedia(this);
	}
}
