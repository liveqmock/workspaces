/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LandingService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.crl.shared.rpc;

import java.util.Map;

import org.springframework.security.access.AccessDeniedException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-24 上午09:06:23
 */
public interface CrlService extends RemoteService {

	public String getBlackListRules(String productId, BaseFilterPagingLoadConfig loadConfig)
			throws AccessDeniedException;

	public String getBlackManageList(String productId, BaseFilterPagingLoadConfig loadConfig)
			throws AccessDeniedException;

	public String getAccountList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	public String updateStatus(String productId, String id, String status) throws AccessDeniedException;

	public Integer delAccount(String productId, String ids) throws AccessDeniedException;

	public String insertBlackManage(String productId, Map<String, Object> paramMap) throws AccessDeniedException;

	public static class Util {
		private static CrlServiceAsync instance;

		public static CrlServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(CrlService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
