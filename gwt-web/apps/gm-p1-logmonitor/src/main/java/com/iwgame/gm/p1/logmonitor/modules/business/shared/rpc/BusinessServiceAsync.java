/**      
 * BusinessServiceAsync.java Create on 2012-9-3     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.model.SourceWarningViewBean;
import com.iwgame.ui.core.client.data.PagingLoadConfig;

/**
 * @ClassName: BusinessServiceAsync
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-3 下午03:41:39
 * @Version 1.0
 * 
 */
public interface BusinessServiceAsync {

	void getLogData(String productId, String tableType, String dateType, int threshold, String filter,
			AsyncCallback<String> callback);

	void getLogDetailDatas(String productId, PagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void getWarningData(String productId, AsyncCallback<String> callback);

	void getWaringData(String productId, Date date, AsyncCallback<List<SourceWarningViewBean>> callback);

}
