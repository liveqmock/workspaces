/**      
 * ConfigServiceImpl.java Create on 2012-5-30     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.sever;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService;
import com.iwgame.iwcloud.flume.admin.sever.service.FlumeManageService;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeAnodeConfigForServer;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeChannel;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeCnodeConfigForServer;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeEtcConfig;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeNode;
import com.iwgame.ui.core.client.data.PagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @ClassName: ConfigServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-30 下午05:24:37
 * @Version 1.0
 * 
 */
@NeedAuthorization
@SuppressWarnings("unchecked")
public class ConfigServiceImpl implements ConfigService {

	private final Logger logger = Logger.getLogger("FlumeAdmin", "GM");

	private DBConnection dbConnectorConnection;

	private FlumeManageService flumeManageService;

	@Autowired
	public void setFlumeManageService(final FlumeManageService flumeManageService) {
		this.flumeManageService = flumeManageService;
	}

	@Autowired
	public void setDbConnection(final DBConnection dbConnectorConnection) {
		this.dbConnectorConnection = dbConnectorConnection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService
	 * #retrieveFlumeNodes()
	 */
	@Override
	public String retrieveFlumeNodes(final String productId) {
		SqlSessionTemplate sqlSessionTemplate = getSqlSessionTemplate(productId);

		List<FlumeNode> datas = (List<FlumeNode>) sqlSessionTemplate.selectList("flume-monitor.queryFlumeNode");
		PagingLoadResult<FlumeNode> result = new PagingLoadResult<FlumeNode>(datas.size(), 0, 0);
		result.setRows(datas);
		logger.writeSuccessfullyLog("FlumeConfig", "查询Flume节点列表");
		return GridHelper.buildResults(result);
	}
	
	@Override
	public List<FlumeEtcConfig> retrieveEtcConfig(final String productId) {
		SqlSessionTemplate sqlSessionTemplate = getSqlSessionTemplate(productId);
		List<FlumeEtcConfig> datas = (List<FlumeEtcConfig>) sqlSessionTemplate.selectList("flume-monitor.queryFlumeEtcConfig");
		logger.writeSuccessfullyLog("getEtc_config", "查询getEtc_config节点列表");
		return datas;
	}
	
	/* (non-Javadoc)
	 * @see com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService#getEtcConfig(java.lang.String)
	 */
	@Override
	public String getEtcConfig(String productId) {
		SqlSessionTemplate sqlSessionTemplate = getSqlSessionTemplate(productId);
		List<FlumeEtcConfig> datas = (List<FlumeEtcConfig>) sqlSessionTemplate.selectList("flume-monitor.getAllFlumeEtcConfig");
		PagingLoadResult<FlumeEtcConfig> result = new PagingLoadResult<FlumeEtcConfig>(datas.size(), 0, 0);
		result.setRows(datas);
		logger.writeSuccessfullyLog("getAllEtc_config", "查询getAllEtc_config节点列表");
		return GridHelper.buildResults(result);
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService
	 * #retrieveFlumeChannels(java.lang.String)
	 */
	@Override
	public String retrieveFlumeChannels(final PagingLoadConfig loadConfig) {

		boolean checkConfig = false;
		if (loadConfig.getPropertyNames().contains("checkConfig")) {
			checkConfig = loadConfig.<Boolean> get("checkConfig").booleanValue();
		}

		String productId = loadConfig.get("productId");
		Integer offset = loadConfig.getOffset();
		Integer limit = loadConfig.getLimit();

		SqlSessionTemplate sqlSessionTemplate = getSqlSessionTemplate(productId);

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("productId", productId);
		parameter.put("offset", offset);
		parameter.put("limit", limit);
		if (loadConfig.getPropertyNames().contains("channelStatus")) {
			parameter.put("channelStatus", loadConfig.get("channelStatus"));
		}
		if (loadConfig.getPropertyNames().contains("channelKey")) {
			parameter.put("channelKey", loadConfig.get("channelKey"));
		}
		if (loadConfig.getPropertyNames().contains("channelNodeStatus")) {
			parameter.put("channelNodeStatus", loadConfig.get("channelNodeStatus"));
		}
		if (loadConfig.getPropertyNames().contains("channelConfig")) {
			parameter.put("channelConfig", loadConfig.get("channelConfig"));
		}
		if (loadConfig.getPropertyNames().contains("anoderoom")) {
			parameter.put("anoderoom", loadConfig.get("anoderoom"));
		}
		List<FlumeChannel> datas = (List<FlumeChannel>) sqlSessionTemplate.selectList(
				"flume-monitor.queryFlumeChannel", parameter);
		int total = (Integer) sqlSessionTemplate.selectOne("flume-monitor.queryTotalFlumeChannel", parameter);
		if (checkConfig) {
			List<FlumeChannel> _datas = new ArrayList<FlumeChannel>();
			for (FlumeChannel flumeChannel : datas) {
				int port = flumeChannel.getPort();
				if (port == 0) {
					_datas.add(flumeChannel);
					continue;
				}
				if (flumeChannel.getaNodeConfig() == null) {
					_datas.add(flumeChannel);
					continue;
				}
				if (flumeChannel.getcNodeConfig() == null) {
					_datas.add(flumeChannel);
					continue;
				}
				FlumeCnodeConfigForServer cconfig = FlumeCnodeConfigForServer.make(flumeChannel.getcNodeConfig());
				if (cconfig == null) {
					_datas.add(flumeChannel);
					continue;
				}
				if (!cconfig.getSource().contains("" + port)) {
					_datas.add(flumeChannel);
				}
			}

			PagingLoadResult<FlumeChannel> result = new PagingLoadResult<FlumeChannel>(total, offset, limit);
			result.setRows(_datas);
			return GridHelper.buildResults(result);
		} else {

			PagingLoadResult<FlumeChannel> result = new PagingLoadResult<FlumeChannel>(total, offset, limit);
			result.setRows(datas);
			// logger.writeSuccessfullyLog("FlumeConfig", "查询Flume通道列表");
			return GridHelper.buildResults(result);
		}
	}

	@Override
	public List<FlumeNode> retrieveFlumeNodes(final String productId, final String type) {
		SqlSessionTemplate sqlSessionTemplate = getSqlSessionTemplate(productId);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("type", type);
		return (List<FlumeNode>) sqlSessionTemplate.selectList("flume-monitor.queryFlumeNodeByType", parameter);
	}

	@Override
	public String createChannel(final FlumeChannel channel) {
		SqlSessionTemplate sqlSessionTemplate = getSqlSessionTemplate(channel.getProductId());
		sqlSessionTemplate.insert("flume-monitor.createChannel", channel);
		return channel.getChannelId();
	}

	@Override
	public int updateChannel(final FlumeChannel channel) {
		SqlSessionTemplate sqlSessionTemplate = getSqlSessionTemplate(channel.getProductId());
		return sqlSessionTemplate.update("flume-monitor.updateChannel", channel);
	}

	/**
	 * @param productId
	 * @return
	 */
	protected SqlSessionTemplate getSqlSessionTemplate(final String productId) {
		String targetId = productId + "-FlumeMonitor";
		SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId, targetId,
				null);
		return sqlSessionTemplate;
	}

	@Override
	public String unconfigAnode(final String configJson) {
		return flumeManageService.unconfigAnode(configJson);
	}

	@Override
	public String configAnode(final String configJson) {
		return flumeManageService.configAnode(configJson);
	}

	@Override
	public String unconfigCnode(final String configJson) {
		return flumeManageService.unconfigCnode(configJson);
	}

	@Override
	public String configCnode(final String configJson) {
		return flumeManageService.configCnode(configJson);
	}

	@Override
	public String closeChannel(final String channelId, final String physicalAnode, final String physicalCnode) {
		return flumeManageService.closeChannel(channelId, physicalAnode, physicalCnode);
	}

	@Override
	public int getNextRecommendPort(final String productId, final String channelId) {
		return flumeManageService.getNextRecommendPort(productId, channelId);
	}

	@Override
	public String openChannel(final String channelId, final String physicalAnode, final String physicalCnode) {
		return flumeManageService.openChannel(channelId, physicalAnode, physicalCnode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService
	 * #retrieveAllFlumeChannels(java.lang.String)
	 */
	@Override
	public String retrieveAllFlumeChannels(final String productId) {
		List<FlumeChannel> results = (List<FlumeChannel>) getSqlSessionTemplate(productId).selectList(
				"flume-monitor.queryAllFlumeChannel", productId);
		StringBuffer sb = new StringBuffer();
		for (FlumeChannel flumeChannel : results) {

			try {
				String anodeconfig = flumeChannel.getaNodeConfig();
				String cnodeconfig = flumeChannel.getcNodeConfig();
				int port = flumeChannel.getPort();
				if (!((anodeconfig.contains(port + "") && cnodeconfig.contains(port + "")))) {
					sb.append(flumeChannel.getChannelId()).append("<br/>");
				}
			} catch (Exception e) {
			}

		}
		String result = sb.toString();
		if (result.isEmpty()) {
			return "数据配置均正常！";
		} else {
			return sb.toString();
		}
	}

	@Override
	public String printScriptCommand(final String productId) {
		List<FlumeChannel> results = (List<FlumeChannel>) getSqlSessionTemplate(productId).selectList(
				"flume-monitor.queryAllOpenedFlumeChannel", productId);
		StringBuffer sba = new StringBuffer();
		sba.append("#==========anode==========\n");
		StringBuffer sbc = new StringBuffer();
		sbc.append("#==========cnode==========\n");
		StringBuffer sbm = new StringBuffer();
		sbm.append("#==========map==========\n");

		for (FlumeChannel flumeChannel : results) {

			try {
				String anodeconfig = flumeChannel.getaNodeConfig();
				sba.append(FlumeAnodeConfigForServer.make(anodeconfig).produceAnodeConfigCommand()).append("\n");
				String cnodeconfig = flumeChannel.getcNodeConfig();
				sbc.append(FlumeCnodeConfigForServer.make(cnodeconfig).produceAnodeConfigCommand()).append("\n");
				sbm.append(flumeChannel.getMapConfig());
			} catch (Exception e) {
			}

		}
		return sba.append("\n\n").toString() + sbc.append("\n\n").toString() + sbm.toString();
	}

	/* (non-Javadoc)
	 * @see com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService#deleteChannel(java.lang.String)
	 */
	@Override
	public int deleteChannel(String productId,String channelId) {
		SqlSessionTemplate sqlSessionTemplate = getSqlSessionTemplate(productId.toUpperCase());
		Map<String, String> map = new HashMap<String, String>();
		map.put("channelId", channelId);
		int reslut = sqlSessionTemplate.delete("flume-monitor.deleteChannel", map);
		return reslut;
	}


}
