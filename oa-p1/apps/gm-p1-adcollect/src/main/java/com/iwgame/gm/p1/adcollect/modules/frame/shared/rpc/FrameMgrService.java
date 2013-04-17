/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： FrameMgrService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.shared.rpc;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.FrameDataBase;
import com.iwgame.gm.p1.adcollect.shared.model.UseFrameDataBase;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-13 下午2:08:45
 */
public interface FrameMgrService extends RemoteService {

	/**
	 * 添加框架
	 * 
	 * @param productId
	 * @param newBase
	 * @return
	 * @throws AccessDeniedException
	 */
	int addFrame(String productId, FrameDataBase newBase) throws AccessDeniedException;

	/**
	 * 获取框架列表
	 * 
	 * @param productId
	 * @param loadConfig
	 * @return
	 * @throws AccessDeniedException
	 */
	String getFrameList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;

	/**
	 * 验证框架名称是否可用
	 * 
	 * @param productId
	 * @param name
	 * @return
	 */
	boolean checkFrameName(String productId, String name) throws AccessDeniedException;

	/**
	 * 验证同媒体框架时间是否可用
	 * 
	 * @param productId
	 * @param name
	 * @return  	0  	添加时可用
	 * 					1  	修改是可用
	 */
	int checkFrameTime(String productId, Map<String, Object> parameter) throws AccessDeniedException;
	
	/**
	 * 修改框架
	 * @param productId
	 * @param newBase
	 * @return
	 * @throws AccessDeniedException
	 */
	int updateFrame(String productId, FrameDataBase newBase) throws AccessDeniedException;

	/**
	 * 刪除框架
	 * @param productId
	 * @param ids
	 * @return
	 * @throws AccessDeniedException
	 */
	int delFrameByIds(String productId, String ids) throws AccessDeniedException;
	
	List<DropDownListData> getFrameName(String productId,String mediaId) throws AccessDeniedException;
	
	/**
	 * 添加使用框架
	 * @param productId
	 * @param newBase
	 * @return
	 * @throws AccessDeniedException
	 */
	int addUseFrame(String productId, UseFrameDataBase newBase) throws AccessDeniedException;
	
	/**
	 * 获取使用框架列表
	 * 
	 * @param productId
	 * @param loadConfig
	 * @return
	 * @throws AccessDeniedException
	 */
	String getUseFrameList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException;
	
	/**
	 * 删除使用框架
	 * @param productId
	 * @param ids
	 * @return
	 * @throws AccessDeniedException
	 */
	int delUseFrameByIds(String productId, String ids) throws AccessDeniedException; 
	
	public static class Util {
		private static FrameMgrServiceAsync instance;

		public static FrameMgrServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(FrameMgrService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}

}
