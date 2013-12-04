/****************************************************************
 *  系统名称  ： '[Security]'
 *  文件名    ： QueryActivityMapper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.query.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.gm.p1.security.modules.query.client.activity.SecurityIwResultTrackActivity;
import com.iwgame.gm.p1.security.modules.query.client.activity.SecurityKilledRecordActivity;
import com.iwgame.gm.p1.security.modules.query.client.activity.SecurityLoginPassModifyRecordActivity;
import com.iwgame.gm.p1.security.modules.query.client.activity.SecurityRegisInfoQueryActivity;
import com.iwgame.gm.p1.security.modules.query.client.activity.SecuritySafeModeRecordActivity;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

/**
 * 
 * @简述： 
 * @作者： Maven自动生成
 * @版本： 1.0
 */
public class QueryActivityMapper extends AbstractModuleActivityMapper {
	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public QueryActivityMapper(final PlaceController placeController, final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> queryParams) {
		if ("loginPassModifyRecord".equalsIgnoreCase(action)) {
			return new SecurityLoginPassModifyRecordActivity();
		}else if ("killedRecord".equalsIgnoreCase(action)) {
			return new SecurityKilledRecordActivity();
		}else if("regisInfoQuery".equalsIgnoreCase(action)){
			return new SecurityRegisInfoQueryActivity();
		}else if ("safeModeRecord".equalsIgnoreCase(action)) {
			return new SecuritySafeModeRecordActivity();
		}else if ("iwResultTrack".equalsIgnoreCase(action)) {
			return new SecurityIwResultTrackActivity();
		}
		return super.onGetActivity(action, params, queryParams);
	}
}
