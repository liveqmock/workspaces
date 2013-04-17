/**      
* SecurityIwResultTrackService.java Create on 2012-11-27     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.shared.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/** 
 * @ClassName: SecurityIwResultTrackService 
 * @Description: TODO(...) 
 * @author Administrator
 * @date 2012-11-27 上午11:08:37 
 * @Version 1.0
 * 
 */
public interface SecurityIwResultTrackService extends RemoteService{

	public String loadRecords(String productID,BaseFilterPagingLoadConfig loadConfig) throws Exception;

	// 工具类
	public static class Util {
		private static SecurityIwResultTrackServiceAsync instance;

		public static SecurityIwResultTrackServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(SecurityIwResultTrackService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
