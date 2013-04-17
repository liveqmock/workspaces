/**      
* SecurityDangerIdCardServiceAsync.java Create on 2012-11-19     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.shared.rpc;

import java.util.List;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.security.common.shared.model.DangerIdCard;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/** 
 * @ClassName: SecurityDangerIdCardServiceAsync 
 * @Description: TODO(...) 
 * @author Administrator
 * @date 2012-11-19 下午02:47:07 
 * @Version 1.0
 * 
 */
public interface SecurityDangerIdCardServiceAsync {

	void loadDangerIdCardData(String productId,
			BaseFilterPagingLoadConfig loadConfig,
			AsyncCallback<String> callback);

	void getDangerIdCardById(String productId, Integer id,
			AsyncCallback<DangerIdCard> callback);

	void updateDangerIdCard(String productId, DangerIdCard dangerIdCard,
			AsyncCallback<Boolean> callback);

	void addDangerIdCard(String productId, List<DangerIdCard> idCards,
			AsyncCallback<Boolean> callback);

	void deleteDangerIdCard(String productId, List<Integer> ids,
			AsyncCallback<Boolean> callback);
	
}
