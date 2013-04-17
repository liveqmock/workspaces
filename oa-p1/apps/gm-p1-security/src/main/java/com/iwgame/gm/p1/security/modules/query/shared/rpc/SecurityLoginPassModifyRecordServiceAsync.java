/**      
* SecurityLoginPassModifyServiceAsync.java Create on 2012-11-20     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.security.common.shared.model.LoginPassModifyRecord;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/** 
 * @简述: 账号改密记录查询异步回调接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-20 下午03:17:17 
 */
public interface SecurityLoginPassModifyRecordServiceAsync {

	void loadRecords(String productId, BaseFilterPagingLoadConfig loadConfig,
			AsyncCallback<String> callback);

	void loadRecords4Export(String productId, Map<String, String> parameters,
			AsyncCallback<List<LoginPassModifyRecord>> callback);

}
