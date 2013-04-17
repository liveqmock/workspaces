/**      
* SecuritySafeModeRecordServiceAsync.java Create on 2012-11-23     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeRecord;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/** 
 * @ClassName: SecuritySafeModeRecordServiceAsync 
 * @Description: TODO(...) 
 * @author Administrator
 * @date 2012-11-23 下午05:20:26 
 * @Version 1.0
 * 
 */
public interface SecuritySafeModeRecordServiceAsync {

	void loadRecords(String productID, BaseFilterPagingLoadConfig loadConfig,
			AsyncCallback<String> callback);

	void loadRecords4Export(String productID, Map<String, String> parameters,
			AsyncCallback<List<SafeModeRecord>> callback);

}
