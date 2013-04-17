/**      
* SecurityLoginPassModifyService.java Create on 2012-11-20     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.security.common.shared.model.LoginPassModifyRecord;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/** 
 * @简述: 账号改密记录查询接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-20 下午03:17:06 
 */
public interface SecurityLoginPassModifyRecordService extends RemoteService{
	
	public String loadRecords(String productId,BaseFilterPagingLoadConfig loadConfig) throws Exception;
	
	public List<LoginPassModifyRecord> loadRecords4Export(String productId,Map<String, String> parameters) throws Exception;
	// 工具类
	public static class Util {
		private static SecurityLoginPassModifyRecordServiceAsync instance;

		public static SecurityLoginPassModifyRecordServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(SecurityLoginPassModifyRecordService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
