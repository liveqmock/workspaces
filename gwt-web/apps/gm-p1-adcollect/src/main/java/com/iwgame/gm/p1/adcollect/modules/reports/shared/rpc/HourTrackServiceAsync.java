/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： HourTrackServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-29 上午10:00:56
 */
public interface HourTrackServiceAsync {

	void getHourTrackListById(String productId, BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void getHourTrackListByTxt(String productId, BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void getRaceListByKeyName(String productId, String keyName, AsyncCallback<List<AdvertisementInfo>> callback);
}
