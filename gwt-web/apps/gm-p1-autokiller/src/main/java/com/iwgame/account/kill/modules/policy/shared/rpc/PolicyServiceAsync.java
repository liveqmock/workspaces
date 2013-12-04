/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： PolicyServiceAsync.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.shared.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.account.kill.modules.policy.shared.model.DropDownListData;
import com.iwgame.account.kill.modules.policy.shared.model.KillPolicy;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-4 上午10:44:50
 */
public interface PolicyServiceAsync {

	void getWhyDropDownListValue(String productId, AsyncCallback<List<DropDownListData>> callback);

	void getPolicyListData(String productId,BaseFilterPagingLoadConfig loadConfig, AsyncCallback<String> callback);
	
	void addKillPolicy(String productId,KillPolicy killPolicy, AsyncCallback<Integer> callback);
	
	void checkIsEnabled(String productId, String mac,AsyncCallback<Boolean> callback);
	
	void stop(String productId,int id,String username, String mac, AsyncCallback<Integer> callback);

	void enabled(String productId,int id,String username, String mac, AsyncCallback<Integer> callback);
	
	void checkTitle(String productId,String title, AsyncCallback<Boolean> callback);
	
	void delay(String productId,int id,String username, String mac, AsyncCallback<Integer> callback);
	
	void updateKillPolicy(String productId,KillPolicy killPolicy, AsyncCallback<Integer> callback);
	
	void checkIsUpdateMac(String productId,String mac, AsyncCallback<Boolean> callback);
}
