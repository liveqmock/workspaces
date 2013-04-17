/**      
 * HistoryServiceImpl.java Create on 2012-5-30     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.sever;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.iwcloud.flume.admin.modules.history.shared.service.HistoryService;
import com.iwgame.iwcloud.flume.admin.shared.model.ChannelDayData;
import com.iwgame.iwcloud.flume.admin.shared.model.DataIntegrityReport;
import com.iwgame.iwcloud.flume.admin.shared.model.DataIntegrityReportForSQ;
import com.iwgame.iwcloud.flume.admin.shared.model.GroupDayData;
import com.iwgame.iwcloud.flume.admin.shared.model.TimeData;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @ClassName: HistoryServiceImpl
 * @Description: 历史监控数据查询服务实现
 * @author 吴江晖
 * @date 2012-5-30 下午03:31:12
 * @Version 1.0
 * 
 */
@NeedAuthorization
public class HistoryServiceImpl implements HistoryService {

	private final Logger logger = Logger.getLogger("FlumeAdmin", "GM");

	private DBConnection dbConnectorConnection;

	@Autowired
	public void setDbConnection(final DBConnection dbConnectorConnection) {
		this.dbConnectorConnection = dbConnectorConnection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.iwcloud.flume.admin.modules.monitor.shared.service.HistoryService
	 * #getGroupDatas(java.lang.String, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@AccessResource(name = "flumeadmin-history-group-query")
	public String getGroupDatas(final String productId, final String filter,
			final Date date) {

		String targetId = productId + "-FlumeMonitor";
		SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection
				.getClient(TargetType.GAME, productId, targetId, null);

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("productId", productId);
		DateTime datetime = new DateTime(date);
		parameter.put("day", datetime.toString("yyyyMMdd"));
		parameter.put("filter", filter);
		List<GroupDayData> datas = (List<GroupDayData>) sqlSessionTemplate
				.selectList("flume-monitor.queryGroupHistoryDatas", parameter);
		PagingLoadResult<GroupDayData> result = new PagingLoadResult<GroupDayData>(
				datas.size(), 0, 0);
		result.setRows(datas);
		logger.writeSuccessfullyLog("HistoryData", "查询组监控历史数据");
		return GridHelper.buildResults(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.iwcloud.flume.admin.modules.monitor.shared.service.HistoryService
	 * #getChannelDatas(java.lang.String, java.lang.String, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@AccessResource(name = "flumeadmin-history-channel-query")
	public String getChannelDatas(final String productId,
			final String channelId, final Date date) {
		String targetId = productId + "-FlumeMonitor";
		SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection
				.getClient(TargetType.GAME, productId, targetId, null);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("channelPattern",
				channelId.substring(0, channelId.indexOf(".") + 1) + "%"
						+ productId.toLowerCase());

		DateTime datetime = new DateTime(date);
		parameter.put("day", datetime.toString("yyyyMMdd"));
		List<ChannelDayData> datas = (List<ChannelDayData>) sqlSessionTemplate
				.selectList("flume-monitor.queryChannelHistoryDatas", parameter);
		PagingLoadResult<ChannelDayData> result = new PagingLoadResult<ChannelDayData>(
				datas.size(), 0, 0);
		result.setRows(datas);
		logger.writeSuccessfullyLog("HistoryData", "查询通道监控历史数据");
		return GridHelper.buildResults(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.iwcloud.flume.admin.modules.monitor.shared.service.HistoryService
	 * #getChannelTimeDatas(java.lang.String, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@AccessResource(name = "flumeadmin-history-channel-time-query")
	public String getChannelTimeDatas(final String productId,
			final String channelId, final Date date) {
		String targetId = productId + "-FlumeMonitor";
		SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection
				.getClient(TargetType.GAME, productId, targetId, null);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("channelId", channelId);
		DateTime datetime = new DateTime(date);
		parameter.put("day", datetime.toString("yyyyMMdd"));
		List<TimeData> datas = (List<TimeData>) sqlSessionTemplate.selectList(
				"flume-monitor.queryChannelTimeHistoryDatas", parameter);
		PagingLoadResult<TimeData> result = new PagingLoadResult<TimeData>(
				datas.size(), 0, 0);
		result.setRows(datas);
		logger.writeSuccessfullyLog("HistoryData", "查询组监控分时历史数据");
		return GridHelper.buildResults(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.iwcloud.flume.admin.modules.monitor.shared.service.HistoryService
	 * #getGroupTimeDatas(java.lang.String, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@AccessResource(name = "flumeadmin-history-group-time-query")
	public String getGroupTimeDatas(final String productId,
			final String groupId, final Date date) {
		String targetId = productId + "-FlumeMonitor";
		SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection
				.getClient(TargetType.GAME, productId, targetId, null);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("groupId", groupId);
		DateTime datetime = new DateTime(date);
		parameter.put("day", datetime.toString("yyyyMMdd"));
		List<TimeData> datas = (List<TimeData>) sqlSessionTemplate.selectList(
				"flume-monitor.queryGroupTimeHistoryDatas", parameter);
		PagingLoadResult<TimeData> result = new PagingLoadResult<TimeData>(
				datas.size(), 0, 0);
		result.setRows(datas);
		logger.writeSuccessfullyLog("HistoryData", "查询通道监控分时历史数据");
		return GridHelper.buildResults(result);
	}

	/* (non-Javadoc)
	 * @see com.iwgame.iwcloud.flume.admin.modules.history.shared.service.HistoryService#getDataIntegrityReport(java.lang.String, java.lang.String, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@AccessResource(name = "flume-dataintegrity-monitor")
	public String getDataIntegrityReport(String productId, String zone, Date date) {
		String targetId = "WG1-DATACENTER";
		if("P-P1".equalsIgnoreCase(productId) || "P-P1.5".equalsIgnoreCase(productId)){
			targetId = productId + "-OA";
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId, targetId, null);
			Map<String, Object> parameter = new HashMap<String, Object>();
			DateTime datetime = new DateTime(date);
			parameter.put("date", datetime.toString("yyyy-MM-dd"));
			List<DataIntegrityReport> datas = (List<DataIntegrityReport>) sqlSessionTemplate.selectList("flume-monitor.getDataIntegrityReport",parameter);
			PagingLoadResult<DataIntegrityReport> result = new PagingLoadResult<DataIntegrityReport>(datas.size(),0,0);
			result.setRows(datas);
			logger.writeSuccessfullyLog("DataIntegrityReport", productId+"查询数据完整性报表成功");
			return GridHelper.buildResults(result);
		}else{
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId, targetId, null);
			Map<String, Object> parameter = new HashMap<String, Object>();
			DateTime datetime = new DateTime(date);
			parameter.put("date", datetime.toString("yyyy-MM-dd"));
			List<DataIntegrityReportForSQ> datas = (List<DataIntegrityReportForSQ>) sqlSessionTemplate.selectList("flume-monitor.getDataIntegrityReportForSQ",parameter);
			PagingLoadResult<DataIntegrityReportForSQ> result = new PagingLoadResult<DataIntegrityReportForSQ>(datas.size(),0,0);
			result.setRows(datas);
			logger.writeSuccessfullyLog("DataIntegrityReport", productId + "查询数据完整性报表成功");
			return GridHelper.buildResults(result);
		}
		
	}

}
