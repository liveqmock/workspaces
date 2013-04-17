/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： SingleInputActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.keys.client.activity;

import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.iwgame.gm.p1.adcollect.modules.keys.client.ui.KeysInputView;
import com.iwgame.gm.p1.adcollect.modules.keys.client.ui.KeysInputView.Presenter;
import com.iwgame.gm.p1.adcollect.modules.keys.shared.rpc.KeysService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * 类说明
 * 
 * @简述： 关键字录入控制类
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-8-20 下午02:12:25
 */
public class KeysInputActivity extends AbstractActivity implements Presenter {

	private final KeysInputView keysInputView;

	@Inject
	public KeysInputActivity(KeysInputView keysInputView) {
		this.keysInputView = keysInputView;
		this.keysInputView.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(keysInputView);
		keysInputView.getSchemaGridView().setPresenter(this);
		keysInputView.getSchemaGridView().load(false);
		keysInputView.getDetailsView().setPresenter(this);
		keysInputView.getDetailsView().load(false);
	}

	@Override
	public void goTo(Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData(Object loadConfig, final AsyncCallbackEx<String> callback) {

		try {
			if (((BaseModelData) loadConfig).<String> get("flag") != null) {
				KeysService.Util.get().getLog((BaseFilterPagingLoadConfig) loadConfig, new AsyncCallbackEx<String>() {

					@Override
					public void onSuccess(String result) {
						callback.onSuccess(result);
					}
				});
			} else {
				KeysService.Util.get().queryAdInfo((BaseFilterPagingLoadConfig) loadConfig,
						new AsyncCallbackEx<String>() {

							@Override
							public void onSuccess(String result) {
								callback.onSuccess(result);
							}
						});
			}
		} catch (final Exception e) {
			// loginhisQueryView.onFailureQuery(e.getMessage());
		}
	}

	@Override
	public void insertAdInfo(Map<String, Object> paramMap) {
		KeysService.Util.get().insertAdInfo(paramMap, new AsyncCallbackEx<Integer>() {

			@Override
			public void onSuccess(Integer result) {
				keysInputView.insertAdInfoResult(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void updateAdInfo(Map<String, Object> paramMap) {

		KeysService.Util.get().updateAdInfo(paramMap, new AsyncCallbackEx<Integer>() {

			@Override
			public void onSuccess(Integer result) {
				keysInputView.updateAdInfoResult(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}
}