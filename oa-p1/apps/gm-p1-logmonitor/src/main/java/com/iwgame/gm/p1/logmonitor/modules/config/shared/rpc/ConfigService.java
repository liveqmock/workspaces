/**      
 * ConfigService.java Create on 2012-9-5     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.config.shared.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.logmonitor.modules.config.shared.model.WarningConfigModelBean;
import com.iwgame.ui.core.client.data.PagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * @ClassName: ConfigService
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-5 下午01:44:25
 * @Version 1.0
 * 
 */
public interface ConfigService extends RemoteService {

	public String getWarningConfigs(String productId, PagingLoadConfig loadConfig);

	public void updateWarningConfig(String productId, WarningConfigModelBean bean);

	public static class Util {
		private static ConfigServiceAsync instance;

		public static ConfigServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(ConfigService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
