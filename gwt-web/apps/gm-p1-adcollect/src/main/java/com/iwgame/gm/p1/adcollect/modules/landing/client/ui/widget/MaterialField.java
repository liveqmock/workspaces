/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MaterialField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.landing.client.ui.widget;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.iwgame.gm.p1.adcollect.modules.landing.client.ui.events.HasMaterialSelectedHandlers;
import com.iwgame.gm.p1.adcollect.modules.landing.client.ui.events.MaterialSelectedEvent;
import com.iwgame.gm.p1.adcollect.modules.landing.client.ui.events.MaterialSelectedHandler;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.AppUtils;

/**
 * 类说明
 * 
 * @简述： 素材 Field
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-25 下午05:40:45
 */
public class MaterialField extends TextField implements HasMaterialSelectedHandlers {

	public MaterialField(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public MaterialField(String label, String string) {
		super(label, string);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		// TODO Auto-generated method stub

	}

	@Override
	public HandlerRegistration addMaterialSelectedHandlers(MaterialSelectedHandler handler) {
		// TODO Auto-generated method stub
		return AppUtils.EVENT_BUS.addHandler(MaterialSelectedEvent.TYPE, handler);
	}

}
