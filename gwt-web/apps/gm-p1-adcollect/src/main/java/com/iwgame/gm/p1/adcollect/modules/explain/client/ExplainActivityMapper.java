/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： ForceoutMgrActivityMapper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.explain.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.gm.p1.adcollect.modules.explain.client.activity.DictionaryMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.explain.client.ui.DictionaryMgrView;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-2 下午05:50:45
 */
public class ExplainActivityMapper extends AbstractModuleActivityMapper {
	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public ExplainActivityMapper(final PlaceController placeController, final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> queryParams) {
		if ("dictionary".equals(action)) {
			return new DictionaryMgrActivity( new DictionaryMgrView());
		}
		return super.onGetActivity(action, params, queryParams);
	}
}
