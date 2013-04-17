/**      
* SecurityIwResultTrackServiceAsync.java Create on 2012-11-27     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.shared.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/** 
 * @ClassName: SecurityIwResultTrackServiceAsync 
 * @Description: TODO(...) 
 * @author Administrator
 * @date 2012-11-27 上午11:08:48 
 * @Version 1.0
 * 
 */
public interface SecurityIwResultTrackServiceAsync {

	void loadRecords(String productID, BaseFilterPagingLoadConfig loadConfig,
			AsyncCallback<String> callback);

}
