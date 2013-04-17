/**      
 * MonitorServiceAsync.java Create on 2012-4-10     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.monitor.shared.service;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/**
 * @ClassName: MonitorServiceAsync
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-4-10 下午04:23:08
 * @Version 1.0
 * 
 */
public interface MonitorServiceAsync {

	void getGroupDatas(String productId, String filters, Date date, AsyncCallback<String> callback);

	void getChannelDatas(String productId, String channelId, Date date, AsyncCallback<String> callback);

	void getChannelTimeDatas(final String channelId, Date date, AsyncCallback<String> callback);

	void getGroupTimeDatas(String groupId, Date date, AsyncCallback<String> callback);

	void getFLumeNodes(BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void validateChannel(String productId, AsyncCallback<Void> callback);

	void unlockChannelGroupWarning(String groupId, AsyncCallback<Void> callback);

	void unlockChannelWarning(String channelId, AsyncCallback<Void> callback);

}
