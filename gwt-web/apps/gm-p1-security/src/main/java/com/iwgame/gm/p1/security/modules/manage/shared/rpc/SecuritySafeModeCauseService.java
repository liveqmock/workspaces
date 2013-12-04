/**      
* SecuritySafeModeCauseService.java Create on 2012-11-16     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.shared.rpc;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeCauseConfig;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/** 
 * @简述: 安全模式备注服务接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期 2012-11-16 下午03:57:58 
 */
public interface SecuritySafeModeCauseService extends RemoteService {
	/**
	 * @简述:加载安全模式原因页面数据
	 * @param loadConfig
	 * @return grid Json String
	 * @throws AccessDeniedException
	 */
	public String loadSafeModeCauseData(String productId,BaseFilterPagingLoadConfig loadConfig) throws Exception;
	
	/**
	 * @简述:根据id获取
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SafeModeCauseConfig getSafeModeCauseById(String productId,Integer id) throws Exception;
	

	public boolean updateSafeModeCause(String productId,SafeModeCauseConfig safeModeCause) throws Exception;
	
	/**
	 * @简述:新增安全模式原因备注
	 * @param safeModeCause
	 * @return boolean true添加成功 false 失败
	 * @throws Exception
	 */
	public boolean addSafeModeCause(String productId,SafeModeCauseConfig safeModeCause) throws Exception;
	
	
	
	public boolean deleteSafeModeCause(String productId,List<Integer> ids) throws Exception;
	
	// 工具类
	public static class Util {
		private static SecuritySafeModeCauseServiceAsync instance;

		public static SecuritySafeModeCauseServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(SecuritySafeModeCauseService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
