/**      
 * KeysServiceAsync.java Create on 2012-8-20     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.modules.keys.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.shared.model.Media;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/**
 * @ClassName: KeysServiceAsync
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-8-20 上午10:03:50
 * @Version 1.0
 * 
 */
public interface KeysServiceAsync {
	void queryAdInfo(BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void insertAdInfo(Map<String, Object> paramMap, AsyncCallback<Integer> callback);

	void updateAdInfo(Map<String, Object> paramMap, AsyncCallback<Integer> callback);

	void getMedia(String productId, AsyncCallback<List<Media>> callback);

	void getLog(BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	// void addLog(Map<String, Object> paramMap,AsyncCallback<Integer>
	// callback);
}
