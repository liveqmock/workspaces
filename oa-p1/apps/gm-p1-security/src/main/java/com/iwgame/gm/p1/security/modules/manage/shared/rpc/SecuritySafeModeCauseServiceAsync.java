/**      
* SecuritySafeModeCauseServiceAsync.java Create on 2012-11-16     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.shared.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeCauseConfig;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/** 
 * @ClassName: SecuritySafeModeCauseServiceAsync 
 * @Description: TODO(...) 
 * @author Administrator
 * @date 2012-11-16 下午03:58:02 
 * @Version 1.0
 * 
 */
public interface SecuritySafeModeCauseServiceAsync {

	void loadSafeModeCauseData(String productId,
			BaseFilterPagingLoadConfig loadConfig,
			AsyncCallback<String> callback);

	void updateSafeModeCause(String productId,
			SafeModeCauseConfig safeModeCause, AsyncCallback<Boolean> callback);

	void getSafeModeCauseById(String productId, Integer id,
			AsyncCallback<SafeModeCauseConfig> callback);

	void addSafeModeCause(String productId, SafeModeCauseConfig safeModeCause,
			AsyncCallback<Boolean> callback);

	void deleteSafeModeCause(String productId, List<Integer> ids,
			AsyncCallback<Boolean> callback);

}
