/****************************************************************
 *  系统名称  ： '[Security]'
 *  文件名    ： QueryActivityMapper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.manage.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.gm.p1.security.modules.manage.client.activity.SecurityDangerIdCardActivity;
import com.iwgame.gm.p1.security.modules.manage.client.activity.SecurityKilledCauseActivity;
import com.iwgame.gm.p1.security.modules.manage.client.activity.SecuritySafeModeCauseActivity;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

/**
 * 
 * @简述： 
 * @作者： Maven自动生成
 * @版本： 1.0
 */
public class ManageActivityMapper extends AbstractModuleActivityMapper {
	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public ManageActivityMapper(final PlaceController placeController, final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> queryParams) {
		if ("killedCause".equalsIgnoreCase(action)) {
			return new SecurityKilledCauseActivity();
		}else if ("safeModeCause".equalsIgnoreCase(action)) {
			return new SecuritySafeModeCauseActivity();
		}else if ("dangerIdCard".equalsIgnoreCase(action)) {
			return new SecurityDangerIdCardActivity();
		}
		
		return super.onGetActivity(action, params, queryParams);
	}
}
