/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： ExplainMgrService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.explain.shared.rpc;

import org.springframework.security.access.AccessDeniedException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-20 下午3:00:29
 */
public interface ExplainMgrService extends RemoteService {

	/** 获取数据字典列表 */
	String getDictionaryLIst(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;
	
	/** 删除数据字典 */
	int delDictionaryByIds(String productId, String ids) throws AccessDeniedException;
	
	/** 上传数据字典 权限 */
	void chenkUploadAuthority() throws AccessDeniedException;
	
	/** 下载数据字典 权限 */
	void chenkDownLoadAuthority() throws AccessDeniedException;
	
	public static class Util {
		private static ExplainMgrServiceAsync instance;

		public static ExplainMgrServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(ExplainMgrService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}

