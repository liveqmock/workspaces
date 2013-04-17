/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LandingServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.customreport.shared.rpc;

import java.util.Map;

import org.springframework.security.access.AccessDeniedException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.shared.model.CustomReportParam;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者：
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-24 上午09:07:44
 */
public interface CustomreportServiceAsync {

	void getCustomReportList(String productId, BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void delCustomReport(String productId, Map<String, String> paraMap, AsyncCallback<String> callback)throws AccessDeniedException;

	void addCustomReport(String productId, Map<String, Object> paraMap,CustomReportParam reportParam, AsyncCallback<String> callback)throws AccessDeniedException;
}
