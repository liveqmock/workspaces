/**      
 * ConfigService.java Create on 2012-5-30     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.history.shared.service;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * @ClassName: ConfigService
 * @Description: 监控历史数据服务
 * @author 吴江晖
 * @date 2012-5-30 下午03:01:39
 * @Version 1.0
 * 
 */
public interface HistoryService extends RemoteService {

	/**
	 * 查询历史组日监控数据
	 * 
	 * @param productId
	 *            产品id
	 * @param date
	 *            查询日期
	 */
	public String getGroupDatas(String productId, String filter, Date date);
	
	/**
	 * 查询历史数据完整性报表 
	 * @param productId
	 * @param zone
	 * @param Date
	 * @return
	 */
	public String getDataIntegrityReport(String productId,String zone,Date Date);

	/**
	 * 查询历史通道日监控数据
	 * 
	 * @param productId
	 *            产品ID
	 * @param channelId
	 *            通道ID
	 * @param date
	 *            查询日期
	 */
	public String getChannelDatas(String productId, String channelId, Date date);

	/**
	 * 查询历史通道时段监控数据
	 * 
	 * @param channelId
	 *            通道ID
	 * @param date
	 *            查询日期
	 */
	public String getChannelTimeDatas(String productId, String channelId,Date date);

	/**
	 * 查询历史组时段监控数据
	 * 
	 * @param groupId
	 *            组ID
	 * @param date
	 *            查询日期
	 */
	public String getGroupTimeDatas(String productId, String groupId, Date date);

	public static class Util {
		private static HistoryServiceAsync instance;

		public static HistoryServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(HistoryService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}
}
