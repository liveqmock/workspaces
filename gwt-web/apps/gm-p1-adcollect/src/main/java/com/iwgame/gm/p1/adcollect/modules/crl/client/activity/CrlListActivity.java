/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： SingleInputActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.crl.client.activity;

import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.iwgame.gm.p1.adcollect.modules.crl.client.ui.CrlListView;
import com.iwgame.gm.p1.adcollect.modules.crl.client.ui.CrlListView.Presenter;
import com.iwgame.gm.p1.adcollect.modules.crl.shared.rpc.CrlService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-8-20 下午02:12:25
 */
public class CrlListActivity extends AbstractActivity implements Presenter {

	private final CrlListView crlListView;
	private final int id;

	@Inject
	public CrlListActivity(int id, CrlListView crlListView) {
		this.crlListView = crlListView;
		this.id = id;
		this.crlListView.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(crlListView);
		crlListView.getSchemaGridView().setPresenter(this);
		if (-1 == id) {
			crlListView.getSchemaGridView().load(false);
		} else {
			crlListView.getSchemaGridView().load();
		}
	}

	@Override
	public void goTo(Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData(Object loadConfig, final AsyncCallbackEx<String> callback) {
		CrlService.Util.get().getBlackManageList(ApplicationContext.getCurrentProductId(),
				(BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void insertBlackManage(String productId, Map<String, Object> paramMap) {
		CrlService.Util.get().insertBlackManage(productId, paramMap, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				crlListView.insertResult(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}

}
