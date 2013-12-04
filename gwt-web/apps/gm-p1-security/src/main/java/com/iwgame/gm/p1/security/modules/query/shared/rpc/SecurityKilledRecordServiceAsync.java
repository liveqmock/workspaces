/**      
* SecurityKilledRecordServiceAsync.java Create on 2012-11-21     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.security.common.shared.model.KilledRecord;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/** 
 * @ClassName: SecurityKilledRecordServiceAsync 
 * @Description: TODO(...) 
 * @author Administrator
 * @date 2012-11-21 下午02:46:28 
 * @Version 1.0
 * 
 */
public interface SecurityKilledRecordServiceAsync {

	void loadRecords(String productID, BaseFilterPagingLoadConfig loadConfig,
			AsyncCallback<String> callback);

	void loadRecords4Export(String productID, Map<String, String> parameters,
			AsyncCallback<List<KilledRecord>> callback);

}
