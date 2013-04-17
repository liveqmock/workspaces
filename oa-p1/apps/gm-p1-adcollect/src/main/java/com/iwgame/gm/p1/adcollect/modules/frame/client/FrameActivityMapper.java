/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ForceoutMgrActivityMapper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.gm.p1.adcollect.modules.frame.client.activity.FrameMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.frame.client.activity.UseMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.frame.client.ui.FrameMgrView;
import com.iwgame.gm.p1.adcollect.modules.frame.client.ui.UseMgrView;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 2.1
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-2 下午05:50:45
 */
public class FrameActivityMapper extends AbstractModuleActivityMapper {
	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public FrameActivityMapper(final PlaceController placeController, final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> queryParams) {
		if (action.equals("manage")) {
			return new FrameMgrActivity(new FrameMgrView());
		} else if (action.equals("use")) {
			String name = null;
			try {
				if(queryParams.containsKey("name")){
					name = queryParams.get("name");
				}
			} catch (final Exception e) {
			}
			return new UseMgrActivity(name, new UseMgrView(name));
		}
		return super.onGetActivity(action, params, queryParams);
	}
}
