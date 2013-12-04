/**      
 * ConfigServiceAsync.java Create on 2012-5-30     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.history.shared.service;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @ClassName: ConfigServiceAsync
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-30 下午03:06:42
 * @Version 1.0
 * 
 */
public interface HistoryServiceAsync {

	/**
	 * 
	 * @see com.iwgame.iwcloud.flume.admin.modules.history.shared.service.HistoryService#getChannelDatas(java.lang.String,
	 *      java.lang.String, java.util.Date)
	 */
	void getChannelDatas(String productId, String channelId, Date date,
			AsyncCallback<String> callback);

	/**
	 * 
	 * @see com.iwgame.iwcloud.flume.admin.modules.history.shared.service.HistoryService#getChannelTimeDatas(java.lang.String,
	 *      java.util.Date)
	 */
	void getChannelTimeDatas(String productId, String channelId, Date date,
			AsyncCallback<String> callback);

	/**
	 * 
	 * @see com.iwgame.iwcloud.flume.admin.modules.history.shared.service.HistoryService#getGroupDatas(java.lang.String,
	 *      java.util.Date)
	 */
	void getGroupDatas(String productId, String filter, Date date,
			AsyncCallback<String> callback);

	/**
	 * 
	 * @see com.iwgame.iwcloud.flume.admin.modules.history.shared.service.HistoryService#getGroupTimeDatas(java.lang.String,
	 *      java.util.Date)
	 */
	void getGroupTimeDatas(String productId, String groupId, Date date,
			AsyncCallback<String> callback);

	void getDataIntegrityReport(String productId, String zone, Date Date, AsyncCallback<String> callback);

}
