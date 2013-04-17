/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DayService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.data.RpcMap;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * 类说明
 * 
 * @简述： 百度关键字日表(流水)
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-29 下午04:13:59
 */
public interface DayService extends RemoteService {

	/** 按照广告ID检索 */
	public String getDayListById(String productId, BaseFilterPagingLoadConfig loadConfig);

	/** 按照文本检索 */
	public String getDayListByTxt(String productId, BaseFilterPagingLoadConfig loadConfig);

	public RpcMap getAggregateDayByTxt(String productId, BaseFilterPagingLoadConfig loadConfig);

	public RpcMap getAggregateDayById(String productId, BaseFilterPagingLoadConfig loadConfig);

	public static class Util {
		private static DayServiceAsync instance;

		public static DayServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(DayService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
