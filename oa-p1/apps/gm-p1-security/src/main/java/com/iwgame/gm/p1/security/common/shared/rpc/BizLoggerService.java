/**      
* BuinessLoggerService.java Create on 2012-11-26     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.common.shared.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/** 
 * @简述: 操作日志记录服务RPC接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期： 2012-11-26 下午07:20:41 
 */
public interface BizLoggerService extends RemoteService{
	
	public String getLogs(BaseFilterPagingLoadConfig loadConfig) throws Exception;
	// 工具类
	public static class Util {
		private static BizLoggerServiceAsync instance;

		public static BizLoggerServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(BizLoggerService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
