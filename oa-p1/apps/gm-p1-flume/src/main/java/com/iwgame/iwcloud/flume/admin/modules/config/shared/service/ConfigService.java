/**      
 * ConfigService.java Create on 2012-5-30     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.config.shared.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeChannel;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeEtcConfig;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeNode;
import com.iwgame.ui.core.client.data.PagingLoadConfig;
import com.iwgame.xmvp.shared.service.ServiceHelper;

/**
 * @ClassName: ConfigService
 * @Description: 监控历史数据服务
 * @author 吴江晖
 * @date 2012-5-30 下午03:01:39
 * @Version 1.0
 * 
 */
public interface ConfigService extends RemoteService {

	public String retrieveFlumeNodes(String productId);
	
	public String getEtcConfig(String productId);

	public List<FlumeNode> retrieveFlumeNodes(String productId, String type);
	
	public List<FlumeEtcConfig> retrieveEtcConfig(String productId);

	public String retrieveFlumeChannels(PagingLoadConfig loadConfig);

	public String retrieveAllFlumeChannels(String productId);
	
	public String createChannel(FlumeChannel channel);

	public int updateChannel(FlumeChannel channel);
	
	public int deleteChannel(String productId,String channelId);

	/**
	 * 关闭Anode配置
	 * 
	 * @param configJson
	 */
	public String unconfigAnode(String configJson);

	/**
	 * 配置Anode(新配置，或者错误情况下的重置）
	 * 
	 * @param configJson
	 */
	public String configAnode(String configJson);

	/**
	 * 关闭Cnode配置
	 * 
	 * @param configJson
	 */
	public String unconfigCnode(String configJson);

	/**
	 * 配置Cnode(新配置，或者错误情况下的重置）
	 * 
	 * @param configJson
	 */
	public String configCnode(String configJson);

	/**
	 * 开启通道
	 * 
	 * @param channelId
	 * @param physicalAnode
	 * @param physicalCnode
	 */
	public String openChannel(String channelId, String physicalAnode,
			String physicalCnode);

	/**
	 * 关闭通道，（一般合服的时候使用， 在redis中删除key，执行unconfig操作，数据库更改通道状态)
	 * 
	 * @param channelId
	 * @param physicalAnode
	 * @param physicalCnode
	 */
	public String closeChannel(String channelId, String physicalAnode,
			String physicalCnode);

	/**
	 * 获取下一个推荐的端口号
	 * 
	 * @param productId
	 * @param channelId
	 * @return
	 */
	public int getNextRecommendPort(String productId, String channelId);

	public String printScriptCommand(String productId);

	public static class Util {
		private static ConfigServiceAsync instance;

		public static ConfigServiceAsync get() {
			if (instance == null) {
				instance = GWT.create(ConfigService.class);
				ServiceHelper.registerServiceEntryPoint(instance);
			}
			return instance;
		}
	}

}
