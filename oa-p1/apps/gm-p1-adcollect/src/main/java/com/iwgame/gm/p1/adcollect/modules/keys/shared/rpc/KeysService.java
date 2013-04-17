/**      
 * KeysService.java Create on 2012-8-20     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.modules.keys.shared.rpc;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.adcollect.shared.model.Media;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * @ClassName: KeysService
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-8-20 上午10:03:43
 * @Version 1.0
 * 
 */
public interface KeysService extends RemoteService {

	public String queryAdInfo(BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	public Integer insertAdInfo(Map<String, Object> paramMap) throws AccessDeniedException;

	public Integer updateAdInfo(Map<String, Object> paramMap) throws AccessDeniedException;

	public List<Media> getMedia(String productId) throws AccessDeniedException;

	public String getLog(BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	// public Integer addLog(Map<String, Object> paramMap) throws
	// AccessDeniedException;

	public static class Util {
		private static KeysServiceAsync instance;

		public static KeysServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(KeysService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
