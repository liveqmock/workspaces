/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LandingService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.landing.shared.rpc;

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
public interface LandingService extends RemoteService {

	public String getLandingList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	int addLanding(String productId, Map<String, Object> parameter) throws AccessDeniedException;

	int updateLanding(String productId, Map<String, Object> parameter, Map<String, Object> lodBase) throws AccessDeniedException;

	public static class Util {
		private static LandingServiceAsync instance;

		public static LandingServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(LandingService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
