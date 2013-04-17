/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MaterialHasMenuSelectedEvent.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.landing.client.ui.events;

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
public class MaterialSelectedEvent extends GwtEvent<MaterialSelectedHandler> {

	public static final GwtEvent.Type<MaterialSelectedHandler> TYPE = new Type<MaterialSelectedHandler>();

	private final String name;
	private final String id;

	/**
	 * @return 获取 name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return 获取 id
	 */
	public String getId() {
		return id;
	}

	public MaterialSelectedEvent(String name, String id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MaterialSelectedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(MaterialSelectedHandler handler) {
		handler.selectMaterial(this);
	}
}
