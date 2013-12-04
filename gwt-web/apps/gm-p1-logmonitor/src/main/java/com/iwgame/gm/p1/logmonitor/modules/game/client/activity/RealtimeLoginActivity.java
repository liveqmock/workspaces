/**      
 * RealtimeLoginActivity.java Create on 2012-8-31     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.game.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.logmonitor.modules.game.client.ui.RealtimeLoginView;
import com.iwgame.gm.p1.logmonitor.modules.game.shared.rpc.LoginService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BasePagingLoadConfig;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: RealtimeLoginActivity
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-8-31 下午03:51:03
 * @Version 1.0
 * 
 */
public class RealtimeLoginActivity extends AbstractActivity implements SchemaGridViewPresenter {

	private final RealtimeLoginView view;

	/**
	 * 
	 */
	public RealtimeLoginActivity(final RealtimeLoginView view) {
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
				+ "{\"index\":\"loginTime\",\"header\":\"登录时间\",\"width\":150,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"zoneId\",\"header\":\"大区\",\"width\":150},"
				+ "{\"index\":\"username\",\"header\":\"帐号\",\"width\":100},"
				+ "{\"index\":\"errorTimes\",\"header\":\"错误次数\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"loginIp\",\"header\":\"登录IP\",\"width\":60},"
				+ "{\"index\":\"loginMac\",\"header\":\"登录MAC\",\"width\":80}" + "]}}");
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
		// TODO Auto-generated method stub

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
		BasePagingLoadConfig config = (BasePagingLoadConfig) loadConfig;
		String zone = config.get("zone");
		Number errTimes = config.get("errTimes");
		LoginService.Util.get().getRealtimeLoginDatas(ApplicationContext.getCurrentProduct().getName(),
				errTimes == null ? null : errTimes.intValue(), zone, 100, callback);
	}

}
