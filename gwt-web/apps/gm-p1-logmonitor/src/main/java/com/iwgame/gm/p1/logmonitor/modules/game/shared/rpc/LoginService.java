/**      
 * LoginService.java Create on 2012-8-31     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.game.shared.rpc;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.logmonitor.modules.game.shared.model.LoginModelBean;
import com.iwgame.ui.core.client.data.PagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * @ClassName: LoginService
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-8-31 下午03:34:42
 * @Version 1.0
 * 
 */
public interface LoginService extends RemoteService {

	public String getRealtimeLoginDatas(String productId, Integer errorTimes, String zoneId, Integer limit);

	public String getHistoryLoginDatas(String productId, PagingLoadConfig loadConfig);

	public List<LoginModelBean> getWarningData(String productId);

	public static class Util {
		private static LoginServiceAsync instance;

		public static LoginServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(LoginService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
