/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： HourTrackService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-29 上午09:59:39
 */
public interface HourTrackService extends RemoteService {

	/** 按照广告ID检索 */
	public String getHourTrackListById(String productId, BaseFilterPagingLoadConfig loadConfig);

	/** 按照文本检索 */
	public String getHourTrackListByTxt(String productId, BaseFilterPagingLoadConfig loadConfig);

	public List<AdvertisementInfo> getRaceListByKeyName(String productId, String keyName);

	public static class Util {
		private static HourTrackServiceAsync instance;

		public static HourTrackServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(HourTrackService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
