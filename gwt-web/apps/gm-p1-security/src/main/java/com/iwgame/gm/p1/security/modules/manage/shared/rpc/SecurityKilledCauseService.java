/**      
* KilledCause.java Create on 2012-11-15     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/
package com.iwgame.gm.p1.security.modules.manage.shared.rpc;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;
/** 
 * @简述: 封杀原因配置接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 */
public interface SecurityKilledCauseService extends RemoteService{
	
	/**
	 * @简述:加载封杀理由配置页面数据
	 * @param loadConfig
	 * @return grid Json String
	 * @throws AccessDeniedException
	 */
	public String loadKilledCauseData(String productId,BaseFilterPagingLoadConfig loadConfig) throws Exception;
	
	/**
	 * @简述:根据id获取
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public KilledCauseConfig getKilledCauseById(String productId,Integer id) throws Exception;
	

	public boolean updateKilledCause(String productId,KilledCauseConfig killedCause) throws Exception;
	
	/**
	 * @简述:新增封杀理由配置
	 * @param killedCause
	 * @return boolean true添加成功 false 失败
	 * @throws Exception
	 */
	public boolean addKilledCause(String productId,KilledCauseConfig killedCause) throws Exception;
	
	
	
	public boolean deleteKilledCause(String productId,List<Integer> ids) throws Exception;
	
	// 工具类
	public static class Util {
		private static SecurityKilledCauseServiceAsync instance;

		public static SecurityKilledCauseServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(SecurityKilledCauseService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
