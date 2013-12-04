/**      
 * BusinessService.java Create on 2012-9-3     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.model.SourceWarningViewBean;
import com.iwgame.ui.core.client.data.PagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * @ClassName: BusinessService
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-3 下午03:41:34
 * @Version 1.0
 * 
 */
public interface BusinessService extends RemoteService {

	public String getLogData(String productId, String tableType, String dateType, int threshold, String filter);

	public List<SourceWarningViewBean> getWaringData(String productId, Date date);

	public String getWarningData(String productId);

	public String getLogDetailDatas(String productId, PagingLoadConfig loadConfig);

	public static class Util {
		private static BusinessServiceAsync instance;

		public static BusinessServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(BusinessService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
