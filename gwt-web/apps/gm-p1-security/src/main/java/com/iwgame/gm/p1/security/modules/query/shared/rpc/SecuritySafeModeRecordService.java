/**      
* SecuritySafeModeRecordService.java Create on 2012-11-23     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeRecord;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/** 
 * @ClassName: SecuritySafeModeRecordService 
 * @Description: TODO(...) 
 * @author Administrator
 * @date 2012-11-23 下午05:20:16 
 * @Version 1.0
 * 
 */
public interface SecuritySafeModeRecordService extends RemoteService{
	public String loadRecords(String productID,BaseFilterPagingLoadConfig loadConfig) throws Exception;
	
	public List<SafeModeRecord> loadRecords4Export(String productID,Map<String, String> parameters) throws Exception;
	// 工具类
	public static class Util {
		private static SecuritySafeModeRecordServiceAsync instance;

		public static SecuritySafeModeRecordServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(SecuritySafeModeRecordService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}	
