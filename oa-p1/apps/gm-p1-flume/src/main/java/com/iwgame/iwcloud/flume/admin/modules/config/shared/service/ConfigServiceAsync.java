/**      
 * ConfigServiceAsync.java Create on 2012-5-30     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.config.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeChannel;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeEtcConfig;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeNode;
import com.iwgame.ui.core.client.data.PagingLoadConfig;

/**
 * @ClassName: ConfigServiceAsync
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-30 下午03:06:42
 * @Version 1.0
 * 
 */
public interface ConfigServiceAsync {

	void retrieveFlumeNodes(String productId, AsyncCallback<String> callback);

	void retrieveFlumeChannels(PagingLoadConfig loadConfig, AsyncCallback<String> callback);

	void retrieveFlumeNodes(String productId, String type, AsyncCallback<List<FlumeNode>> callback);

	void retrieveEtcConfig(String productId, AsyncCallback<List<FlumeEtcConfig>> callback);
	
	void getEtcConfig(String productId, AsyncCallback<String> callback);

	void createChannel(FlumeChannel channel, AsyncCallback<String> callback);

	void updateChannel(FlumeChannel channel, AsyncCallback<Integer> callback);

	void unconfigAnode(String configJson, AsyncCallback<String> callback);

	void configAnode(String configJson, AsyncCallback<String> callback);

	void unconfigCnode(String configJson, AsyncCallback<String> callback);

	void configCnode(String configJson, AsyncCallback<String> callback);

	void getNextRecommendPort(String productId, String channelId, AsyncCallback<Integer> callback);

	void openChannel(String channelId, String physicalAnode, String physicalCnode, AsyncCallback<String> callback);
	
	void deleteChannel(String productId,String channelId,AsyncCallback<Integer> callback);

	void closeChannel(String channelId, String physicalAnode, String physicalCnode, AsyncCallback<String> callback);

	void retrieveAllFlumeChannels(String productId, AsyncCallback<String> callback);

	void printScriptCommand(String productId, AsyncCallback<String> callback);

}
