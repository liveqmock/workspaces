/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： CustomreportActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.customreport.client.activity;

import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.iwgame.gm.p1.adcollect.modules.customreport.client.ui.CustomreportView;
import com.iwgame.gm.p1.adcollect.modules.customreport.client.ui.CustomreportView.Presenter;
import com.iwgame.gm.p1.adcollect.modules.customreport.shared.rpc.CustomreportService;
import com.iwgame.gm.p1.adcollect.shared.model.CustomReportParam;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-11-12 下午02:46:08
 */
public class CustomreportActivity extends AbstractActivity implements Presenter {

	private final CustomreportView customreportView;

	@Inject
	public CustomreportActivity(CustomreportView customreportView) {
		this.customreportView = customreportView;
		this.customreportView.setPresenter(this);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(customreportView);
		customreportView.getSchemaGridView().setPresenter(this);
		customreportView.getSchemaGridView().load();
	}

	@Override
	public void goTo(Place place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		CustomreportService.Util.get().getCustomReportList(ApplicationContext.getCurrentProductId(),
				(BaseFilterPagingLoadConfig) loadConfig, callback);
	}


	@Override
	public void delTask(String productId, Map<String, String> paramMap) {
		CustomreportService.Util.get().delCustomReport(productId, paramMap, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				customreportView.delResult(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void addTask(String productId, Map<String, Object> paramMap,CustomReportParam reportParam) {
		CustomreportService.Util.get().addCustomReport(productId, paramMap, reportParam,new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String result) {
				customreportView.addResult(result);
			}
		});
	}

}