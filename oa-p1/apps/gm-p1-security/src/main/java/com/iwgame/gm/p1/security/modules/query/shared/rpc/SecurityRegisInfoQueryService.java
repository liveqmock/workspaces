/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityRegisInfoQueryService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.query.shared.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.security.common.shared.bean.AccountDocs;
import com.iwgame.gm.p1.security.common.shared.bean.AccountParam;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-27 上午10:38:25
 */
public interface SecurityRegisInfoQueryService extends RemoteService{

	public String loadAccounts(BaseFilterPagingLoadConfig loadConfig) throws Exception;
	
	public List<AccountDocs> loadAccounts4Export(Map<String, String> paramater) throws Exception;
	
	public AccountDocs getAccountDocsByUserName(String userName,String productId) throws Exception;
	
	public String loadRoles(BaseFilterPagingLoadConfig loadConfig)throws Exception;
	public static class Util {
		private static SecurityRegisInfoQueryServiceAsync instance;

		public static SecurityRegisInfoQueryServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(SecurityRegisInfoQueryService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
