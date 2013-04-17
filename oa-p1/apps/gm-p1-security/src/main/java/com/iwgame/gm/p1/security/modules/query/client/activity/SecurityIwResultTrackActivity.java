/**      
* SecurityDangerIdCardActivity.java Create on 2012-11-19     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.client.activity;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityIwResultTrackView;
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityIwResultTrackView.IwResultTrackPresenter;
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityIwResultTrackViewImpl;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityIwResultTrackService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/** 
 * @简述: 操作结果追踪
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期： 2012-11-27 上午11:26:55  
 * 
 */
public class SecurityIwResultTrackActivity extends AbstractActivity implements IwResultTrackPresenter{

	private final int initPageSize = 35;
	private final boolean isLoadOnAttach=false;
	private  String productId = ApplicationContext.getCurrentProductId();
	private SecurityIwResultTrackView resultTrackView;
	@Inject
	public SecurityIwResultTrackActivity()
	{
		
	}
	
	@Override
	public void goTo(Place place) {
		
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		SecurityIwResultTrackService.Util.get().loadRecords(getProductId(),(BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.resultTrackView = new SecurityIwResultTrackViewImpl(this, initPageSize, isLoadOnAttach,productId);
		panel.setWidget(resultTrackView);
	}

	@Override
	public String getProductId() {
		return productId;
	}
}
