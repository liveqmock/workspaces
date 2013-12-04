/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DropDownField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.HasMediaSelectedHandlers;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedEvent;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedHandler;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.xmvp.client.utils.AppUtils;

/**
 * 类说明
 * 
 * @简述： 下拉列表Field
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-29 下午05:26:39
 */
public class DropDownField extends PlainObjectSelector<DropDownListData> implements HasMediaSelectedHandlers {

	public DropDownField(final String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public DropDownField(final String label, final String fieldName) {
		super(label, fieldName);
	}

	@Override
	public void fireEvent(final GwtEvent<?> event) {
		// TODO Auto-generated method stub

	}

	@Override
	public HandlerRegistration addMedisSelectedHandlers(final MediaSelectedHandler handler) {
		return AppUtils.EVENT_BUS.addHandler(MediaSelectedEvent.TYPE, handler);
	}

	@Override
	protected String getValue(final DropDownListData t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getLabel(final DropDownListData t) {
		// TODO Auto-generated method stub
		return null;
	}

}
