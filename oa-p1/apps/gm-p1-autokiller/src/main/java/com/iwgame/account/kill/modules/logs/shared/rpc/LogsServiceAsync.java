/****************************************************************
 *  系统名称  ： '[killer]'
 *  文件名    ： LogsServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.logs.shared.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-11 下午06:11:46
 */
public interface LogsServiceAsync {

	void loadLogsList(String productId,BaseFilterPagingLoadConfig loadConfig,AsyncCallback<String> callback);
}
