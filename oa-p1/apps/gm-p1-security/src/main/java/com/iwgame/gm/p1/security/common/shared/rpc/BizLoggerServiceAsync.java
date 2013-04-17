/**      
* BuinessLoggerServiceAsync.java Create on 2012-11-26     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.common.shared.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/** 
 * @ClassName: BuinessLoggerServiceAsync 
 * @Description: TODO(...) 
 * @author Administrator
 * @date 2012-11-26 下午06:49:52 
 * @Version 1.0
 * 
 */
public interface BizLoggerServiceAsync {

	void getLogs(BaseFilterPagingLoadConfig loadConfig,
			AsyncCallback<String> callback);

}
