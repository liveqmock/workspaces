/****************************************************************
 *  文件名     ： CommonChannelService.java
 *  日期         :  2012-10-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.AppSource;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.BizDict;
import com.iwgame.ui.core.client.data.PagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/** 
 * @ClassName:    CommonChannelService 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-19上午11:05:37
 * @Version:      1.0 
 */
public interface CommonChannelService extends RemoteService{
	
	/**
	 * 获取来源列表,下拉框
	 * @return
	 */
	public List<AppSource> retrieveAppSource();
	
	
	/**
	 * 获取业务类型列表,下拉框
	 * @return
	 */
	public List<BizDict> retrieveBizDict();
	
	
	public String getLogreport(String productid,PagingLoadConfig loadConfig);
	
	
	public String getAllLogData(String productid,PagingLoadConfig loadConfig);
	
	
	
	public static class Util{
		private static CommonChannelServiceAsync instance;
		
		public static CommonChannelServiceAsync get(){
			if(instance == null){
				instance = GWT.create(CommonChannelService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}

}
