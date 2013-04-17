/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityRegisInfoQueryServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.query.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.security.common.shared.bean.AccountDocs;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-27 上午10:38:41
 */
public interface SecurityRegisInfoQueryServiceAsync {

	void loadAccounts(BaseFilterPagingLoadConfig loadConfig,
			AsyncCallback<String> callback);

	void loadRoles(BaseFilterPagingLoadConfig loadConfig,
			AsyncCallback<String> callback);
	
	void loadAccounts4Export(Map<String, String> paramater,
			AsyncCallback<List<AccountDocs>> callback);

	void getAccountDocsByUserName(String userName,String productId,
			AsyncCallback<AccountDocs> callback);

}
