/**      
 * WarningPresenter.java Create on 2012-9-5     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.config.client.presenter;

import com.iwgame.gm.p1.logmonitor.modules.config.shared.model.WarningConfigModelBean;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * @ClassName: WarningPresenter
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-5 下午02:13:58
 * @Version 1.0
 * 
 */
public interface WarningPresenter extends SchemaGridViewPresenter {

	public void updateWarningConfig(WarningConfigModelBean bean, AsyncCallbackEx<Void> callback);
}
