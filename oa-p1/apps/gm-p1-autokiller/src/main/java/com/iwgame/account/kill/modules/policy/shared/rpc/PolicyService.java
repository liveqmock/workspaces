/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： PolicyService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.shared.rpc;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.account.kill.modules.policy.shared.model.DropDownListData;
import com.iwgame.account.kill.modules.policy.shared.model.KillPolicy;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * 类说明
 * 
 * @简述： 自动封停策略接口
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-4 上午10:44:35
 */
public interface PolicyService extends RemoteService {

	/** 获取封停原因原因 */
	List<DropDownListData> getWhyDropDownListValue(String productId);
	/** 加载策略列表*/
	String getPolicyListData(String productId,BaseFilterPagingLoadConfig loadConfig);
	/** 添加一条策略*/
	int addKillPolicy(String productId,KillPolicy killPolicy);
	/** 检查是否可以启动*/
	boolean checkIsEnabled(String productId,String mac);
	/** 停用*/
	int stop(String productId,int id,String username, String mac);
	/** 启用*/
	int enabled(String productId,int id,String username, String mac);
	/** 延时*/
	int delay(String productId,int id,String username, String mac);
	/** 验证策略名*/
	boolean checkTitle(String productId,String title);
	/** 修改策略*/
	int updateKillPolicy(String productId,KillPolicy killPolicy);
	
	boolean checkIsUpdateMac(String productId,String mac);
	
	public static class Util {
		private static PolicyServiceAsync instance;

		public static PolicyServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(PolicyService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
