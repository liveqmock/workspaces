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
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityKilledRecordView;
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityKilledRecordViewImpl;
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityKilledRecordView.KilledRecordPresenter;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityKilledRecordService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/** 
 * @简述: 封杀记录查询
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期： 2012-11-21 下午15:04:45 
 * 
 */
public class SecurityKilledRecordActivity extends AbstractActivity implements KilledRecordPresenter{

	private final int initPageSize = 35;
	private final boolean isLoadOnAttach=false;
	private  String productId = ApplicationContext.getCurrentProductId();
	private SecurityKilledRecordView killedRecordView;
	@Inject
	public SecurityKilledRecordActivity()
	{
		
	}
	
	@Override
	public void goTo(Place place) {
		
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		SecurityKilledRecordService.Util.get().loadRecords(getProductId(),(BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.killedRecordView = new SecurityKilledRecordViewImpl(this, initPageSize, isLoadOnAttach,productId);
		panel.setWidget(killedRecordView);
	}

	@Override
	public String getProductId() {
		return productId;
	}
}
