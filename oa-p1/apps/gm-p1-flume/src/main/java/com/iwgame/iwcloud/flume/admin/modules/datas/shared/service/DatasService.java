/**      
 * DatasService.java Create on 2012-4-10     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.datas.shared.service;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * @ClassName: DatasService
 * @Description: 监控服务
 * @author 吴江晖
 * @date 2012-4-10 下午04:23:03
 * @Version 1.0
 * 
 */
public interface DatasService extends RemoteService {

	/**
	 * 查询Redis中组日监控数据
	 * 
	 * @param productId
	 *            产品id
	 * @param date
	 *            查询日期
	 * @return
	 * @throws FlumeAdminException
	 */
	public String getGroupDatas(String productId, String filters, Date date) throws FlumeAdminException;

	/**
	 * 查询Redis中通道日监控数据
	 * 
	 * @param productId
	 *            产品ID
	 * @param channelId
	 *            通道ID
	 * @param date
	 *            查询日期
	 * @return
	 * @throws FlumeAdminException
	 */
	public String getChannelDatas(String productId, String channelId, Date date) throws FlumeAdminException;

	/**
	 * 查询Redis中通道时段监控数据
	 * 
	 * @param channelId
	 *            通道ID
	 * @param date
	 *            查询日期
	 * @return
	 * @throws FlumeAdminException
	 */
	public String getChannelTimeDatas(String channelId, Date date) throws FlumeAdminException;

	/**
	 * 查询Redis中组时段监控数据
	 * 
	 * @param groupId
	 *            组ID
	 * @param date
	 *            查询日期
	 * @return
	 * @throws FlumeAdminException
	 */
	public String getGroupTimeDatas(String groupId, Date date) throws FlumeAdminException;

	public void confirmData(String channelId, String date);

	/**
	 * 取得Flume 节点列表
	 * 
	 * @return
	 * @throws FlumeAdminException
	 */
	public String getFLumeNodes(BaseFilterPagingLoadConfig loadConfig) throws FlumeAdminException;

	public static class Util {
		private static DatasServiceAsync instance;

		public static DatasServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(DatasService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}

}
