/**      
 * WarningActivity.java Create on 2012-9-5     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.config.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.logmonitor.modules.config.client.presenter.WarningPresenter;
import com.iwgame.gm.p1.logmonitor.modules.config.client.ui.WarningView;
import com.iwgame.gm.p1.logmonitor.modules.config.shared.model.WarningConfigModelBean;
import com.iwgame.gm.p1.logmonitor.modules.config.shared.rpc.ConfigService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: WarningActivity
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-5 下午12:10:37
 * @Version 1.0
 * 
 */
public class WarningActivity extends AbstractActivity implements WarningPresenter {

	private final WarningView view;

	public WarningActivity(final WarningView view) {
		super();
		this.view = view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client
	 * .ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
	 */
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"id\",\"header\":\"ID\",\"visiable\":false},"
				+ "{\"index\":\"monitorName\",\"header\":\"预警模块名\",\"width\":150},"
				+ "{\"index\":\"maxCount\",\"header\":\"报警阀值(按小时)\",\"width\":150,\"type\":\"number\"},"
				+ "{\"index\":\"status\",\"header\":\"是否启用\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"lastUpdatetime\",\"header\":\"最后修改时间\",\"width\":100,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"lastUpdateuser\",\"header\":\"最后修改人\",\"width\":80}" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#goTo(com.google
	 * .gwt.place.shared.Place)
	 */
	@Override
	public void goTo(final Place place) {
		// donothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#loadData(java.
	 * lang.Object, com.iwgame.ui.core.client.AsyncCallbackEx)
	 */
	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		ConfigService.Util.get().getWarningConfigs(ApplicationContext.getCurrentProductId(),
				(BaseFilterPagingLoadConfig) loadConfig, callback);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.logmonitor.modules.config.client.presenter.WarningPresenter
	 * #updateWarningConfig(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void updateWarningConfig(final WarningConfigModelBean bean, final AsyncCallbackEx<Void> callback) {
		ConfigService.Util.get().updateWarningConfig(ApplicationContext.getCurrentProductId(), bean, callback);
	}

}
