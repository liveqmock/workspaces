/**      
* SecurityDangerIdCardService.java Create on 2012-11-19     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.shared.rpc;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.security.common.shared.model.DangerIdCard;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/** 
 * @简述: 高危身份证服务接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-19 下午02:46:50 
 */
public interface SecurityDangerIdCardService extends RemoteService{
	/**
	 * @简述:加载高危身份证页面数据
	 * @param loadConfig
	 * @return grid Json String
	 * @throws AccessDeniedException
	 */
	public String loadDangerIdCardData(String productId,BaseFilterPagingLoadConfig loadConfig) throws Exception;
	
	/**
	 * @简述:根据id获取
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DangerIdCard getDangerIdCardById(String productId,Integer id) throws Exception;
	

	public boolean updateDangerIdCard(String productId,DangerIdCard dangerIdCard) throws Exception;
	
	public boolean addDangerIdCard(String productId,List<DangerIdCard> idCards) throws Exception;
	
	
	public boolean deleteDangerIdCard(String productId,List<Integer> ids) throws Exception;
	
	// 工具类
	public static class Util {
		private static SecurityDangerIdCardServiceAsync instance;

		public static SecurityDangerIdCardServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(SecurityDangerIdCardService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
