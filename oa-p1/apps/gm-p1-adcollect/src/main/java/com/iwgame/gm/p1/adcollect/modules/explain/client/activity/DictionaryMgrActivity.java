/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： DictionaryMgrActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.explain.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.explain.client.presenter.DictionaryMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.explain.client.ui.DictionaryMgrView;
import com.iwgame.gm.p1.adcollect.modules.explain.shared.rpc.ExplainMgrService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @Description: 数据字典控制类
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-20 上午10:16:48
 */
public class DictionaryMgrActivity extends AbstractActivity implements DictionaryMgrPresenter{

	private final String productId = ApplicationContext.getCurrentProductId();
	private DictionaryMgrView view;

	public DictionaryMgrActivity(DictionaryMgrView mgrView){
		super();
		this.view = mgrView;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"name\",\"header\":\"名称\",\"width\":100},"
				+ "{\"index\":\"function\",\"header\":\"主要功能\",\"width\":150},"
				+ "{\"index\":\"dateTime\",\"header\":\"添加时间\",\"width\":120,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"pathShow\",\"header\":\"操作\",\"type\":\"button\"}" 
				+ "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(true);
	}
	
	@Override
	public void goTo(Place place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		ExplainMgrService.Util.get().getDictionaryLIst(productId, (BaseFilterPagingLoadConfig)loadConfig, callback);
	}

	@Override
	public void delDictionaryByIds(String ids, AsyncCallback<Integer> callback) {
		ExplainMgrService.Util.get().delDictionaryByIds(productId, ids, callback);
	}

	@Override
	public void chenkUploadAuthority(AsyncCallback<Void> callback) {
		ExplainMgrService.Util.get().chenkUploadAuthority(callback);
	}

	@Override
	public void chenkDownLoadAuthority(AsyncCallback<Void> callback) {
		ExplainMgrService.Util.get().chenkDownLoadAuthority(callback);
	}


}

