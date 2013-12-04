/**      
 * MonitorServiceImpl.java Create on 2012-4-10     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.sever;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.SortParameters.Order;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.data.redis.core.query.SortQueryBuilder;

import com.iwgame.iwcloud.flume.admin.modules.datas.shared.service.DatasService;
import com.iwgame.iwcloud.flume.admin.modules.monitor.shared.service.FlumeAdminException;
import com.iwgame.iwcloud.flume.admin.sever.service.FlumeManageService;
import com.iwgame.iwcloud.flume.admin.shared.model.ChannelDayData;
import com.iwgame.iwcloud.flume.admin.shared.model.Constant;
import com.iwgame.iwcloud.flume.admin.shared.model.FlumeNode;
import com.iwgame.iwcloud.flume.admin.shared.model.GroupDayData;
import com.iwgame.iwcloud.flume.admin.shared.model.TimeData;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xmvp.client.utils.DateWrapper;

/**
 * @ClassName: MonitorServiceImpl
 * @Description: 监控服务实现
 * @author 吴江晖
 * @date 2012-4-10 下午04:31:21
 * @Version 1.0
 * 
 */
public class DatasServiceImpl implements DatasService, Constant {

	private static final Logger log = Logger.getLogger(DatasServiceImpl.class);

	private StringRedisTemplate flumeRedisDataTemplate;

	private DBConnection dbConnectorConnection;

	@Autowired
	public void setDbConnection(final DBConnection dbConnectorConnection) {
		this.dbConnectorConnection = dbConnectorConnection;
	}

	@Autowired
	public void setFlumeRedisDataTemplate(final StringRedisTemplate flumeRedisDataTemplate) {
		this.flumeRedisDataTemplate = flumeRedisDataTemplate;
	}

	@Override
	public String getGroupDatas(String productId, final String filters, final Date date) {

		productId = productId.toLowerCase();
		log.info("Flume admin : ProductId : " + productId);

		String day = getDay(date);
		log.info("Flume admin : Query Date : " + day);

		MessageFormat dataGet = new MessageFormat(SORT_GROUP_DATA_KEY);

		// sort productId:group:list get # get groupid:*:info->name
		// get t:status:groupid:*:day:20120416->fetcher_count
		// get t:status:groupid:*:day:20120416->fetcher_last_time
		// get t:status:groupid:*:day:20120416->fetcher_status
		// get t:status:groupid:*:day:20120416->sink_count
		// get t:status:groupid:*:day:20120416->sink_last_time
		// get t:status:groupid:*:day:20120416->sink_count
		// get t:status:groupid:*:day:20120416->monitor_status
		// alpha

		SortQuery<String> sortQuery = SortQueryBuilder
				.sort(new MessageFormat(GROUP_LIST_KEY).format(new String[] { productId })).get("#")
				.get(new MessageFormat(SORT_GROUP_INFO_KEY).format(new String[] { "name" }))
				.get(dataGet.format(new String[] { day, FETCHER_COUNT }))
				.get(dataGet.format(new String[] { day, FETCHER_LAST_TIME }))
				.get(dataGet.format(new String[] { day, FETCHER_STATUS }))
				.get(dataGet.format(new String[] { day, SINK_COUNT }))
				.get(dataGet.format(new String[] { day, SINK_LAST_TIME }))
				.get(dataGet.format(new String[] { day, SINK_STATUS }))
				.get(dataGet.format(new String[] { day, MONITOR_STATUS })).alphabetical(true).build();
		List<String> groups = flumeRedisDataTemplate.sort(sortQuery);

		List<GroupDayData> groupDayDatas = new ArrayList<GroupDayData>();

		for (int index = 0; index < groups.size();) {
			GroupDayData groupDayData = new GroupDayData();
			String groupId = StringUtils.trimToEmpty(groups.get(index++));// 1
			String groupName = StringUtils.trimToEmpty(groups.get(index++));// 2
			if (StringUtils.isEmpty(groupName)) {
				groupName = groupId;
			}

			if ((filters != null) && !"".equals(filters) && !groupId.contains(filters) && !groupName.contains(filters)) {
				index = index + 7;
				continue;
			}

			groupDayData.setGroupId(groupId);
			log.info("Flume admin : Group Id : " + groupDayData.getGroupId());
			groupDayData.setGroupName(groupName);
			if (groupDayData.getGroupName() == null) {
				groupDayData.setGroupName(groupDayData.getGroupId());
			}

			// 获取Fetcher数据
			groupDayData.setFetcherCount(getIntValue(groups.get(index++))// 3
					.intValue());
			groupDayData.setFetcherLastTime(getIntValue(groups.get(index++)));// 4
			groupDayData.setFetcherStatus(groups.get(index++));// 5

			// 获取Sink数据
			groupDayData.setSinkCount(getIntValue(groups.get(index++))// 6
					.intValue());
			groupDayData.setSinkLastTime(getIntValue(groups.get(index++)));// 7
			groupDayData.setSinkStatus(groups.get(index++));// 8

			groupDayData.setMonitorStatus(getIntValue(groups.get(index++)));// 9

			groupDayDatas.add(groupDayData);
		}

		PagingLoadResult<GroupDayData> result = new PagingLoadResult<GroupDayData>(groupDayDatas.size(), 0, 0);
		result.setRows(groupDayDatas);

		return GridHelper.buildResults(result);
	}

	private Long getIntValue(final String v) {
		return Long.valueOf(v == null ? "0" : v);
	}

	@Override
	public String getChannelDatas(String productId, String channelId, final Date date) throws FlumeAdminException {
		productId = productId.toLowerCase();
		channelId = channelId.toLowerCase();

		String day = getDay(date);

		MessageFormat dataGet = new MessageFormat(SORT_CHANNEL_DATA_KEY);

		SortQuery<String> sortQuery = SortQueryBuilder
				.sort(new MessageFormat(CHANNEL_LIST_KEY).format(new String[] { productId, channelId })).get("#")
				.get(new MessageFormat(SORT_CHANNEL_INFO_KEY).format(new String[] { "name" }))
				.get(dataGet.format(new String[] { day, FETCHER_COUNT }))
				.get(dataGet.format(new String[] { day, FETCHER_LAST_TIME }))
				.get(dataGet.format(new String[] { day, FETCHER_STATUS }))
				.get(dataGet.format(new String[] { day, SINK_COUNT }))
				.get(dataGet.format(new String[] { day, SINK_LAST_TIME }))
				.get(dataGet.format(new String[] { day, SINK_STATUS }))
				.get(dataGet.format(new String[] { day, SINK_COST_TIME })).alphabetical(true).build();
		List<String> datas = flumeRedisDataTemplate.sort(sortQuery);

		List<ChannelDayData> groupDayDatas = new ArrayList<ChannelDayData>();

		for (int index = 0; index < datas.size();) {
			ChannelDayData channelDayData = new ChannelDayData();

			channelDayData.setChannelId(datas.get(index++));
			log.info("Flume admin : Channel Id : " + channelDayData.getChannelId());
			channelDayData.setChannelName(datas.get(index++));
			if (channelDayData.getChannelName() == null) {
				channelDayData.setChannelName(channelDayData.getChannelId());
			}
			// 获取Fetcher数据
			channelDayData.setFetcherCount(getIntValue(datas.get(index++)).intValue());
			channelDayData.setFetcherLastTime(getIntValue(datas.get(index++)));
			channelDayData.setFetcherStatus(datas.get(index++));

			// 获取Sink数据
			channelDayData.setSinkCount(getIntValue(datas.get(index++)).intValue());
			channelDayData.setSinkLastTime(getIntValue(datas.get(index++)));
			channelDayData.setSinkStatus(datas.get(index++));
			channelDayData.setCosttime(getIntValue(datas.get(index++)).intValue());

			groupDayDatas.add(channelDayData);
		}

		PagingLoadResult<ChannelDayData> result = new PagingLoadResult<ChannelDayData>(groupDayDatas.size(), 0, 0);
		result.setRows(groupDayDatas);

		return GridHelper.buildResults(result);
	}

	@Override
	public String getChannelTimeDatas(final String channelId, final Date date) throws FlumeAdminException {
		return getTimeData(SORT_TIME_DATA_KEY_CHANNEL, channelId.toLowerCase(), date);
	}

	@Override
	public String getGroupTimeDatas(final String groupId, final Date date) throws FlumeAdminException {
		return getTimeData(SORT_TIME_DATA_KEY_GROUP, groupId.toLowerCase(), date);
	}

	/**
	 * @param date
	 * @param datas
	 * @return
	 */
	protected String getTimeData(final String template, final String name, final Date date) {

		SetOperations<String, String> setOperations = flumeRedisDataTemplate.opsForSet();
		DateWrapper today = new DateWrapper(new Date());
		int start = 23;
		if (today.isSameDay(date)) {
			// 判断查询日期是否为当天
			// 如果是当天，从当前的小时数开始向前遍历
			// 否则从24点开始向前遍历
			start = today.getHours();
		}
		for (int i = start; i >= 0; i--) {
			if (i < 10) {
				setOperations.add("hours", "0" + i);
			} else {
				setOperations.add("hours", "" + i);
			}
		}

		String day = getDay(date);

		MessageFormat dataGet = new MessageFormat(template);

		SortQuery<String> sortQuery = SortQueryBuilder.sort("hours").get("#")
				.get(dataGet.format(new String[] { name, day, FETCHER_COUNT }))
				.get(dataGet.format(new String[] { name, day, SINK_COUNT }))
				.get(dataGet.format(new String[] { name, day, SINK_COST_TIME })).order(Order.DESC).build();
		List<String> datas = flumeRedisDataTemplate.sort(sortQuery);
		flumeRedisDataTemplate.delete("hours");// 从Redis中删除临时插入用于查询的时间集合

		List<TimeData> groupDayDatas = new ArrayList<TimeData>();

		DateWrapper dwDate = new DateWrapper(date);
		dwDate = dwDate.clearTime();
		for (int index = 0; index < datas.size();) {
			TimeData channelDayData = new TimeData();

			int time = getIntValue(datas.get(index++)).intValue();
			dwDate = dwDate.addHours(time);
			channelDayData.setTime(dwDate.asDate());
			dwDate = dwDate.clearTime();

			try {
				// 获取Fetcher数据
				channelDayData.setFetcherCount(getIntValue(datas.get(index++)).intValue());
				// 获取Sink数据
				channelDayData.setSinkCount(getIntValue(datas.get(index++)).intValue());
				channelDayData.setCostTime(getIntValue(datas.get(index++)).intValue());
			} catch (Exception e) {
				continue;
			}

			groupDayDatas.add(channelDayData);
		}

		PagingLoadResult<TimeData> result = new PagingLoadResult<TimeData>(groupDayDatas.size(), 0, 0);
		result.setRows(groupDayDatas);

		return GridHelper.buildResults(result);
	}

	/**
	 * @param date
	 * @return
	 */
	public String getDay(final Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		String day = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
		return day;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getFLumeNodes(final BaseFilterPagingLoadConfig loadConfig) throws FlumeAdminException {
		String returnData = "";
		try {
			// 获取连接，查询
			String productId = loadConfig.<String> get("productId");
			String targetId = productId.toUpperCase() + "-FlumeMonitor";
			SqlSessionTemplate sqlSessionTemplate = dbConnectorConnection.getClient(TargetType.GAME, productId,
					targetId, null);

			// 构建参数&查询
			Map<String, Object> parameter = new HashMap<String, Object>();
			List<FlumeNode> flumeChannels = (List<FlumeNode>) sqlSessionTemplate.selectList(
					"flume-monitor.queryFlumeNode", parameter);

			// 返回结果
			PagingLoadResult<FlumeNode> result = new PagingLoadResult<FlumeNode>();
			result.setRows(flumeChannels);
			result.setTotal(flumeChannels.size());
			returnData = GridHelper.buildResults(result);

		} catch (Exception e) {
			log.error(e);
		}

		return returnData;
	}

	private FlumeManageService flumeManageService;

	@Autowired
	public void setFlumeManageService(final FlumeManageService flumeManageService) {
		this.flumeManageService = flumeManageService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.iwcloud.flume.admin.modules.datas.shared.service.DatasService
	 * #confirmData(java.lang.String, java.lang.String)
	 */
	@Override
	public void confirmData(final String channelId, final String date) {
		flumeManageService.confirmDataIntegrity(channelId, date);
	}

}
