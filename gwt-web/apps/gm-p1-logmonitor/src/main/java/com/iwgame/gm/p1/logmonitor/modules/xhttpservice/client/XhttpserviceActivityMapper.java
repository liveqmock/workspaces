/****************************************************************
 *  文件名     ： XhttpserviceModuleInject.java
 *  日期         :  2012-10-18
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.activity.CommonChannelLogDetailActivity;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.activity.CommonChannelReportActivity;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.ui.CommonChannelLogDetailView;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.ui.CommonChannelReportView;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

/** 
 * @ClassName:    XhttpserviceModuleInject 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-18下午02:52:15
 * @Version:      1.0 
 */
public class XhttpserviceActivityMapper extends AbstractModuleActivityMapper{

	protected PlaceController placeController;
	
	protected EventBus eventBus;
	
	@Inject
	public XhttpserviceActivityMapper(final PlaceController placeController,final EventBus eventBus){
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}
	
	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> queryParams) {
		if (action.equals("logdetail")) {
			return new CommonChannelLogDetailActivity(new CommonChannelLogDetailView(),queryParams);
		}
		
		if (action.equals("logreport")) {
			return new CommonChannelReportActivity(new CommonChannelReportView());
		}
		
		return super.onGetActivity(action, params, queryParams);
	}
	
	
}
