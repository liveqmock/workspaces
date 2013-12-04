/**      
 * LoginServiceAsync.java Create on 2012-8-31     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.game.shared.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.logmonitor.modules.game.shared.model.LoginModelBean;
import com.iwgame.ui.core.client.data.PagingLoadConfig;

/**
 * @ClassName: LoginServiceAsync
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-8-31 下午03:34:57
 * @Version 1.0
 * 
 */
public interface LoginServiceAsync {

	void getRealtimeLoginDatas(String productId, Integer errorTimes, String zoneId, Integer limit,
			AsyncCallback<String> callback);

	void getHistoryLoginDatas(String productId, PagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void getWarningData(String productId, AsyncCallback<List<LoginModelBean>> callback);

}
