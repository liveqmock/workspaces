/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ADDataService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc;

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
 * @创建时间：2012-9-13 下午12:27:11
 */
public interface ADDataService extends RemoteService {

	/** 广告ID检索 */
	public String getADDataListById(String productId, BaseFilterPagingLoadConfig loadConfig);

	public static class Util {
		private static ADDataServiceAsync instance;

		public static ADDataServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(ADDataService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
