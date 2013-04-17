/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DayServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.data.RpcMap;

/**
 * 类说明
 * 
 * @简述： 百度关键字日表(流水)
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-29 下午04:14:17
 */
public interface DayServiceAsync {

	void getDayListById(String productId, BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void getDayListByTxt(String productId, BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void getAggregateDayByTxt(String productId, BaseFilterPagingLoadConfig loadConfig, AsyncCallback<RpcMap> callback);

	void getAggregateDayById(String productId, BaseFilterPagingLoadConfig loadConfig, AsyncCallback<RpcMap> callback);

}
