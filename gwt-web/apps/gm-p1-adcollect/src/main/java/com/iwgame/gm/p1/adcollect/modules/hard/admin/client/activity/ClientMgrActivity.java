/**      
 * ClientMgrActivity.java Create on 2012-11-12 上午9:42:38    
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
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.ClientMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.ClientMgrView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: 客户端识别码控制器
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-12 上午9:42:38
 * @Version 1.0
 * 
 */
public class ClientMgrActivity extends AbstractActivity implements
	ClientMgrPresenter {

    private final String productId = ApplicationContext.getCurrentProductId();

    private ClientMgrView view;

    public ClientMgrActivity(ClientMgrView mgrView) {
	super();
	view = mgrView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#goTo(com.google
     * .gwt.place.shared.Place)
     */
    @Override
    public void goTo(Place place) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client
     * .ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
		+ "{\"index\":\"mediaName\",\"header\":\"网吧媒体\",\"width\":150},"
		+ "{\"index\":\"version\",\"header\":\"游戏客户端版本\",\"width\":150},"
		+ "{\"index\":\"code\",\"header\":\"客户端识别码\",\"width\":300},"
		+ "{\"index\":\"addTime\",\"header\":\"添加日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
		+ "{\"index\":\"lastUpdateTime\",\"header\":\"修改日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"}"
		+ "]}}");
	view.setPresenter(this);
	panel.setWidget(view);
	view.load(true);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#loadData(java.
     * lang.Object, com.iwgame.ui.core.client.AsyncCallbackEx)
     */
    @Override
    public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
	AdminMgrService.Util.get().getNetbarClientList(productId,
		(BaseFilterPagingLoadConfig) loadConfig, callback);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.
     * ClientMgrPresenter#getMedia(int,
     * com.google.gwt.user.client.rpc.AsyncCallback)
     */
    @Override
    public void getMedia(int type,
	    AsyncCallback<List<DropDownListData>> callback) {
	AdminMgrService.Util.get().getMedia(productId, type, callback);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.
     * ClientMgrPresenter#addClent(java.lang.String, java.lang.String,
     * java.lang.String, com.google.gwt.user.client.rpc.AsyncCallback)
     */
    @Override
    public void addClent(String mediaId, String version, String code,
	    AsyncCallback<Integer> callback) {
	AdminMgrService.Util.get().addClent(productId, mediaId, version, code,
		callback);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.
     * ClientMgrPresenter#updateClent(java.lang.String, java.lang.String,
     * java.lang.String, int, com.google.gwt.user.client.rpc.AsyncCallback)
     */
    @Override
    public void updateClent(String mediaId, String version, String code,
	    int id, AsyncCallback<Integer> callback) {
	AdminMgrService.Util.get().updateClent(productId, mediaId, version,
		code, id, callback);
    }

}
