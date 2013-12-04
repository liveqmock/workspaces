/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： ForceOutLogsListActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.logs.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.account.kill.modules.logs.client.presenter.AutoLogsListPresenter;
import com.iwgame.account.kill.modules.logs.client.ui.AutoLogsListView;
import com.iwgame.account.kill.modules.logs.client.ui.AutoLogsListViewImpl;
import com.iwgame.account.kill.modules.logs.shared.rpc.LogsService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 封停日志控制类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-4 下午02:13:30
 */
public class AutoLogsListActivity extends AbstractActivity implements AutoLogsListPresenter {

	private AutoLogsListView view;

	public AutoLogsListActivity(AutoLogsListViewImpl impl) {
		super();
		this.view = (AutoLogsListView) impl;
	}

	@Override
	public void goTo(Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		LogsService.Util.get().loadLogsList(ApplicationContext.getCurrentProductId(),
				(BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"id\",\"header\":\"ID\",\"width\":80,\"type\":\"number\"},"
				+ "{\"index\":\"policyTitle\",\"header\":\"策略名称\",\"width\":100,\"type\":\"button\"},"//
				+ "{\"index\":\"accountid\",\"header\":\"游戏帐号ID\",\"width\":100,\"type\":\"number\",\"visiable\":false},"
				+ "{\"index\":\"accountName\",\"header\":\"游戏帐号\",\"width\":100},"
				+ "{\"index\":\"killTime\",\"header\":\"封杀时间\",\"width\":130,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm\"},"
				+ "{\"index\":\"killDaysShow\",\"header\":\"封杀天数\",\"width\":80,\"type\":\"number\"},"
				+ "{\"index\":\"object\",\"header\":\"MAC地址\"},"
				+ "{\"index\":\"remarkInfo\",\"header\":\"原因\",\"width\":110,\"type\":\"button\"}"
				+ "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load();
	}

}
