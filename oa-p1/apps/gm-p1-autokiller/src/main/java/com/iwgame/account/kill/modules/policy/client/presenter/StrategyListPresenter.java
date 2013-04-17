/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： StrategyListPresenter.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.presenter;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.account.kill.modules.policy.shared.model.DropDownListData;
import com.iwgame.account.kill.modules.policy.shared.model.KillPolicy;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-3 上午10:08:38
 */
public interface StrategyListPresenter extends SchemaGridViewPresenter {
	
	void getWhyDropDownListValue(AsyncCallback<List<DropDownListData>> callback);
	
	void addPolicy(KillPolicy killPolicy,AsyncCallback<Integer> callback);

	void checkIsEnabled(String mac,AsyncCallback<Boolean> callback);
	
	void stop(int id, String username,String mac,AsyncCallback<Integer> callback);

	void enabled(int id, String username,String mac,AsyncCallback<Integer> callback);
	
	void checkTitle(String title, AsyncCallback<Boolean> callback);
	
	void delay(int id,String username,String mac,AsyncCallback<Integer> callback);
	
	void updateKillPolicy(KillPolicy killPolicy,AsyncCallback<Integer> callback);
	
	void checkIsUpdateMac (String mac,AsyncCallback<Boolean> callback);

}
