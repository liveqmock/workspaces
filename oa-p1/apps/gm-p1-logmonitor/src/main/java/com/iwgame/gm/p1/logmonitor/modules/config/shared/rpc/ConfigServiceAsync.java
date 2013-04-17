/**      
 * ConfigServiceAsync.java Create on 2012-9-5     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.config.shared.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.logmonitor.modules.config.shared.model.WarningConfigModelBean;
import com.iwgame.ui.core.client.data.PagingLoadConfig;

/**
 * @ClassName: ConfigServiceAsync
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-5 下午01:44:31
 * @Version 1.0
 * 
 */
public interface ConfigServiceAsync {

	void getWarningConfigs(String productId, PagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void updateWarningConfig(String productId, WarningConfigModelBean bean, AsyncCallback<Void> callback);

}
