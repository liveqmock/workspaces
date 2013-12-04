/****************************************************************
 *  系统名称  ： '[LogMonitor]'
 *  文件名    ： ConfigActivityMapper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.business.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.gm.p1.logmonitor.modules.business.client.activity.BusinessLogDetailsActivity;
import com.iwgame.gm.p1.logmonitor.modules.business.client.activity.BusinessLogMonitorActivity;
import com.iwgame.gm.p1.logmonitor.modules.business.client.activity.BusinessWarningActivity;
import com.iwgame.gm.p1.logmonitor.modules.business.client.ui.BusinessLogDetailsView;
import com.iwgame.gm.p1.logmonitor.modules.business.client.ui.BusinessLogMonitorView;
import com.iwgame.gm.p1.logmonitor.modules.business.client.ui.WarningPortalView;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

/**
 * 
 * @简述：
 * @作者： Maven自动生成
 * @版本： 1.0
 */
public class BusinessActivityMapper extends AbstractModuleActivityMapper {
	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public BusinessActivityMapper(final PlaceController placeController, final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> queryParams) {
		if (action.equals("ip") || action.equals("source") || action.equals("account")) {
			return new BusinessLogMonitorActivity(new BusinessLogMonitorView(), action);
		}
		if (action.equals("detail")) {
			return new BusinessLogDetailsActivity(new BusinessLogDetailsView(), queryParams);
		}
		if (action.equals("warning")) {
			return new BusinessWarningActivity(new WarningPortalView());
		}
		return super.onGetActivity(action, params, queryParams);
	}
}
