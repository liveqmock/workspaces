/****************************************************************
 *  系统名称  ： '[LogMonitor]'
 *  文件名    ： ConfigActivityMapper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.game.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.gm.p1.logmonitor.modules.game.client.activity.HistoryLoginActivity;
import com.iwgame.gm.p1.logmonitor.modules.game.client.activity.RealtimeLoginActivity;
import com.iwgame.gm.p1.logmonitor.modules.game.client.ui.HistoryLoginView;
import com.iwgame.gm.p1.logmonitor.modules.game.client.ui.RealtimeLoginView;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

/**
 * 
 * @简述：
 * @作者： Maven自动生成
 * @版本： 1.0
 */
public class GameActivityMapper extends AbstractModuleActivityMapper {
	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public GameActivityMapper(final PlaceController placeController, final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> queryParams) {
		if (action.equals("realtimelogin")) {
			return new RealtimeLoginActivity(new RealtimeLoginView());
		}
		if (action.equals("historylogin")) {
			return new HistoryLoginActivity(new HistoryLoginView());
		}
		return super.onGetActivity(action, params, queryParams);
	}
}
