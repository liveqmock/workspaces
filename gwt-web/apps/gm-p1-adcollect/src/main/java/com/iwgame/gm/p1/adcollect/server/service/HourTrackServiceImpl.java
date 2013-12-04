/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： HourTrackServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc.HourTrackService;
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.gm.p1.adcollect.shared.model.AdvidHour;
import com.iwgame.gm.p1.adcollect.shared.model.AdvtxtHour;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;

/**
 * 类说明
 * 
 * @简述： 百度关键字小时追踪表
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-29 上午10:03:03
 */
public class HourTrackServiceImpl extends BaseService implements HourTrackService {

	private static final Logger logger4j = Logger.getLogger(HourTrackServiceImpl.class);
	private static final String AHT = "ad.hour.track.";

	@Override
	public String getHourTrackListById(String productId, BaseFilterPagingLoadConfig loadConfig) {
		String returnData = "";
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));

			// 业务参数
			parameter.put("key", loadConfig.<String> get("key"));
			parameter.put("beganTime", loadConfig.<Date> get("startTime"));
			parameter.put("endTime", loadConfig.<Date> get("endTime"));
			final String date = parameter.get("endTime").toString();
			final String tableName = date.substring(0, 4) + date.substring(5, 7);
			parameter.put("tableName", tableName);
			final Integer total = selectOne(productId, AHT + "getHourTrackListByIdCount", parameter);
			final List<AdvidHour> list = selectList(productId, AHT + "getHourTrackListById", parameter);
			final PagingLoadResult<AdvidHour> result = new PagingLoadResult<AdvidHour>();
			result.setRows(list);
			result.setTotal(total);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);

		} catch (final Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return returnData;
	}

	@Override
	public String getHourTrackListByTxt(String productId, BaseFilterPagingLoadConfig loadConfig) {
		String returnData = "";
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));

			// 业务参数
			parameter.put("key", loadConfig.<String> get("key"));
			parameter.put("beganTime", loadConfig.<Date> get("startTime"));
			parameter.put("endTime", loadConfig.<Date> get("endTime"));
			final String date = parameter.get("endTime").toString();
			final String tableName = date.substring(0, 4) + date.substring(5, 7);
			parameter.put("tableName", tableName);

			final Integer total = selectOne(productId, AHT + "getHourTrackListByTxtCount", parameter);
			final List<AdvtxtHour> list = selectList(productId, AHT + "getHourTrackListByTxt", parameter);
			final PagingLoadResult<AdvtxtHour> result = new PagingLoadResult<AdvtxtHour>();
			result.setRows(list);
			result.setTotal(total);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);

		} catch (final Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return returnData;
	}

	@Override
	public List<AdvertisementInfo> getRaceListByKeyName(String productId, String keyName) {
		final Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("keyword", keyName);
		final List<AdvertisementInfo> resultList = selectList(productId, "adcollect.keys.getRaceListByKeyName",
				parameter);
		return resultList;
	}
}
