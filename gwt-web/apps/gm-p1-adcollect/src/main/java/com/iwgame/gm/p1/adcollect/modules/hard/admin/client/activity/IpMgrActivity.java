/**      
 * IpMgrActivity.java Create on 2012-11-8 下午12:02:40    
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.IpMarPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.IpMarView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: 网吧对应IP 控制类
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-8 下午12:02:40
 * @Version 1.0
 * 
 */
public class IpMgrActivity extends AbstractActivity implements IpMarPresenter{

	private final String productId = ApplicationContext.getCurrentProductId();
	
	private IpMarView view;
	/** 媒体ID */
	final String id ;
	
	public IpMgrActivity(IpMarView marView, String mediaId){
		super();
		view = marView;
		this.id = mediaId;
	}
	
	/* (non-Javadoc)
	 * @see com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#goTo(com.google.gwt.place.shared.Place)
	 */
	@Override
	public void goTo(Place place) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client.ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
	 */
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"mediaName\",\"header\":\"网吧媒体\",\"width\":150},"
				+ "{\"index\":\"ip\",\"header\":\"IP\",\"width\":150},"
				+ "{\"index\":\"ipArea\",\"header\":\"对应区域\",\"width\":300}"
				+ "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(true);
	}

	/* (non-Javadoc)
	 * @see com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#loadData(java.lang.Object, com.iwgame.ui.core.client.AsyncCallbackEx)
	 */
	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		BaseFilterPagingLoadConfig _loadConfig = (BaseFilterPagingLoadConfig) loadConfig;
		if (null != this.id) {
			_loadConfig.set("mediaId", this.id);
		}
	    AdminMgrService.Util.get().getNetbarIpList(productId, (BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.IpMarPresenter#getMedia(int, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public void getMedia(int type,
		AsyncCallback<List<DropDownListData>> callback) {
	    AdminMgrService.Util.get().getMedia(productId, type, callback);	    
	}

	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.IpMarPresenter#addNetbarIp(java.lang.String, java.lang.String, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public void addNetbarIp(String mediaId, String ip,
		AsyncCallback<Integer> callback) {
	    AdminMgrService.Util.get().addNetbarIp(productId, mediaId, ip, callback);	    
	}

	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.IpMarPresenter#delNetbarIps(java.lang.String, com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public void delNetbarIps(String ids, AsyncCallback<Integer> callback) {
	    AdminMgrService.Util.get().delNetbarIps(productId, ids, callback);
	}

	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.IpMarPresenter#batchAddNetbarIp(com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public void chenkbatchAddNetbarIpAuthority(AsyncCallback<Void> callback) {
	    AdminMgrService.Util.get().chenkbatchAddNetbarIpAuthority(callback);
	}

}
