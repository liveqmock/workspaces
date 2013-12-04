/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityRegisInfoQueryActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.query.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityRegisInfoQueryView;
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityRegisInfoQueryView.SecurityRegisInfoQueryPresenter;
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityRegisInfoQueryViewImpl;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityRegisInfoQueryService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-27 上午10:30:01
 */
public class SecurityRegisInfoQueryActivity extends AbstractActivity implements SecurityRegisInfoQueryPresenter{
	
	private final int initPageSize = 35;
	private final boolean isLoadOnAttach=false;
	private  String productId = ApplicationContext.getCurrentProductId();
	private SecurityRegisInfoQueryView securityRegisInfoQueryView;
	
	public SecurityRegisInfoQueryActivity(){
		
	}
	@Override
	public void goTo(Place place) {
		
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		BaseFilterPagingLoadConfig bl = (BaseFilterPagingLoadConfig) loadConfig;
		bl.set("pid", productId);
		if(bl.get("options")==null){
			bl.set("options", "username");
		}
		SecurityRegisInfoQueryService.Util.get().loadAccounts(bl, callback);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		securityRegisInfoQueryView = new SecurityRegisInfoQueryViewImpl(initPageSize,isLoadOnAttach,productId,this);
		panel.setWidget(securityRegisInfoQueryView);
	}

}
