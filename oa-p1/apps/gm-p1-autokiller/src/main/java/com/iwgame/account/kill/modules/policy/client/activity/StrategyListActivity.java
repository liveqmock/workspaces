/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： StrategyListActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.activity;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.account.kill.modules.policy.client.presenter.StrategyListPresenter;
import com.iwgame.account.kill.modules.policy.client.ui.StrategyListView;
import com.iwgame.account.kill.modules.policy.client.ui.StrategyListViewImpl;
import com.iwgame.account.kill.modules.policy.shared.model.DropDownListData;
import com.iwgame.account.kill.modules.policy.shared.model.KillPolicy;
import com.iwgame.account.kill.modules.policy.shared.rpc.PolicyService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 自动封停控制类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-3 上午10:08:58
 */
public class StrategyListActivity extends AbstractActivity implements StrategyListPresenter {

	private StrategyListView view;

	public StrategyListActivity(StrategyListViewImpl impl) {
		super();
		this.view = (StrategyListView) impl;
	}

	@Override
	public void goTo(Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		PolicyService.Util.get().getPolicyListData(ApplicationContext.getCurrentProductId(),
				(BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"title\",\"header\":\"策略名称\",\"width\":120},"
				+ "{\"index\":\"object\",\"header\":\"MAC地址\"},"
				+ "{\"index\":\"startDateShow\",\"header\":\"执行时间\",\"width\":140},"
				+ "{\"index\":\"endDateShow\",\"header\":\"结束时间\",\"width\":140},"
				+ "{\"index\":\"paidShow\",\"header\":\"充值记录过滤\",\"width\":100},"
				+ "{\"index\":\"levelShow\",\"header\":\"等级过滤\",\"width\":80},"
				+ "{\"index\":\"status\",\"header\":\"状态值\",\"type\":\"number\",\"width\":80,\"visiable\":false},"
				+ "{\"index\":\"creater\",\"header\":\"创建人\",\"width\":100},"
				+ "{\"index\":\"modifier\",\"header\":\"最后操作者\",\"width\":100},"
				+ "{\"index\":\"statusShow\",\"header\":\"状态\",\"width\":80},"
				+ "{\"index\":\"stop\",\"type\":\"button\",\"header\":\"\",\"width\":60},"
				+ "{\"index\":\"update\",\"type\":\"button\",\"header\":\"\",\"width\":60}" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load();
	}

	@Override
	public void getWhyDropDownListValue(AsyncCallback<List<DropDownListData>> callback) {
		PolicyService.Util.get().getWhyDropDownListValue(ApplicationContext.getCurrentProductId(),
				callback);
	}

	@Override
	public void addPolicy(KillPolicy killPolicy, AsyncCallback<Integer> callback) {
		PolicyService.Util.get().addKillPolicy(ApplicationContext.getCurrentProductId(),
				killPolicy, callback);

	}

	@Override
	public void checkIsEnabled(String mac, AsyncCallback<Boolean> callback) {
		PolicyService.Util.get().checkIsEnabled(ApplicationContext.getCurrentProductId(), mac,
				callback);
	}

	@Override
	public void stop(int id, String username, String mac, AsyncCallback<Integer> callback) {
		PolicyService.Util.get().stop(ApplicationContext.getCurrentProductId(), id, username, mac,
				callback);
	}

	@Override
	public void enabled(int id, String username, String mac, AsyncCallback<Integer> callback) {
		PolicyService.Util.get().enabled(ApplicationContext.getCurrentProductId(), id, username,
				mac, callback);
	}

	@Override
	public void checkTitle(String title, AsyncCallback<Boolean> callback) {
		PolicyService.Util.get().checkTitle(ApplicationContext.getCurrentProductId(), title,
				callback);
	}

	@Override
	public void delay(int id, String username, String mac, AsyncCallback<Integer> callback) {
		PolicyService.Util.get().delay(ApplicationContext.getCurrentProductId(), id, username, mac,
				callback);
	}

	@Override
	public void updateKillPolicy(KillPolicy killPolicy, AsyncCallback<Integer> callback) {
		PolicyService.Util.get().updateKillPolicy(ApplicationContext.getCurrentProductId(),
				killPolicy, callback);
	}

	@Override
	public void checkIsUpdateMac(String mac, AsyncCallback<Boolean> callback) {
		PolicyService.Util.get().checkIsUpdateMac(ApplicationContext.getCurrentProductId(), mac,
				callback);
	}
}
