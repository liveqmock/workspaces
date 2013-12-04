/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DayServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

import com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc.DayService;
import com.iwgame.gm.p1.adcollect.shared.model.DayIdDate;
import com.iwgame.gm.p1.adcollect.shared.model.DayTxtDate;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xmvp.shared.data.RpcMap;

/**
 * 类说明
 * 
 * @简述： 百度关键字日表(流水)
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-29 下午04:15:58
 */
public class DayServiceImpl extends BaseService implements DayService {

	private static final Logger logger4j = Logger.getLogger(HourTrackServiceImpl.class);
	private static final String AD = "ad.day.";

	@Override
	public String getDayListById(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
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
			parameter.put("list", loadConfig.<String> get("type"));
			final String show = loadConfig.<String> get("show");
			Integer total = null;
			List<DayIdDate> list = null;
			if (show.equals("detail")) {// 明细
				total = selectOne(productId, AD + "getDaydetailByIdCount", parameter);
				list = selectList(productId, AD + "getDaydetailById", parameter);
			}
			if (show.equals("summary")) {// 汇总
				total = selectOne(productId, AD + "getDaySummaryByIdCount", parameter);
				list = selectList(productId, AD + "getDaySummaryById", parameter);
			}
			final PagingLoadResult<DayIdDate> result = new PagingLoadResult<DayIdDate>();
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
	public String getDayListByTxt(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
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
			parameter.put("list", loadConfig.<String> get("type"));
			final String show = loadConfig.<String> get("show");
			Integer total = null;
			List<DayTxtDate> list = null;
			if (show.equals("detail")) {// 明细
				total = selectOne(productId, AD + "getDaydetailByTxtCount", parameter);
				list = selectList(productId, AD + "getDaydetailByTxt", parameter);// sortField=click
																					// sortDir=ASC
			}
			if (show.equals("summary")) {// 汇总
				total = selectOne(productId, AD + "getDaySummaryByTxtCount", parameter);
				list = selectList(productId, AD + "getDaySummaryByTxt", parameter);// sortField=click
																					// sortDir=ASC
			}
			final PagingLoadResult<DayTxtDate> result = new PagingLoadResult<DayTxtDate>();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc.DayService#
	 * getAggregateDayByTxt(java.lang.String,
	 * com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig)
	 */
	@Override
	public RpcMap getAggregateDayByTxt(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
		final Map<String, Object> parameter = new HashMap<String, Object>();

		// 业务参数
		parameter.put("key", loadConfig.<String> get("key"));
		parameter.put("beganTime", loadConfig.<Date> get("startTime"));
		parameter.put("endTime", loadConfig.<Date> get("endTime"));
		parameter.put("list", loadConfig.<String> get("type"));
		final String show = loadConfig.<String> get("show");
		DayTxtDate data = null;
		if (show.equals("detail")) {// 明细
			data = selectOne(productId, AD + "getAggregateDaydetailByTxt", parameter);
		}
		if (show.equals("summary")) {// 汇总
			data = selectOne(productId, AD + "getAggregateDayByTxt", parameter);
		}
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("used", data.getUsed());
		result.put("show", data.getShow());
		result.put("click", data.getClick());
		result.put("clickPrice", data.getClickPrice());
		// result.put("rank", data.getRank());
		result.put("clickRatio", data.getClickRatio());
		result.put("cpm", data.getCpm());
		result.put("reg", data.getReg());
		result.put("regLogin", data.getRegLogin());
		result.put("cpa", data.getCpa());
		result.put("regRatio", data.getRegRatio());
		result.put("loginPrice", data.getLoginPrice());
		result.put("newReg", data.getNewReg());
		result.put("newRegRatio", data.getNewRegRatio());
		result.put("subNewReg", data.getSubNewReg());
		result.put("subNewRegRatio", data.getSubNewRegRatio());
		result.put("oldReg", data.getOldReg());
		result.put("oldRegRatio", data.getOldRegRatio());
		result.put("newLogin", data.getNewLogin());
		result.put("subNewLogin", data.getSubNewLogin());
		result.put("oldLogin", data.getOldLogin());
		result.put("keyName", "汇总");
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String beginDateStr = (String) parameter.get("beganTime");
		if (beginDateStr == null) {
			beginDateStr = "开始";
		} else {
			try {
				final Date beginDate = DateUtils.parseDate(beginDateStr, new String[] { "yyyy-MM-dd HH:mm:ss" });
				beginDateStr = format.format(beginDate);
			} catch (final ParseException e) {
				beginDateStr = "开始";
			}
		}
		String endDateStr = (String) parameter.get("endTime");
		if (endDateStr == null) {
			endDateStr = "至今";
		} else {
			try {
				final Date endDate = DateUtils.parseDate(endDateStr, new String[] { "yyyy-MM-dd HH:mm:ss" });
				endDateStr = format.format(DateUtils.addDays(endDate, -1));
			} catch (final ParseException e) {
				endDateStr = "至今";
			}
		}
		if (show.equals("detail")) {// 明细
			if (beginDateStr.equals(endDateStr)) {
				result.put("date", beginDateStr);
			} else {
				result.put("date", beginDateStr + " ~ " + endDateStr);
			}
		}
		if (show.equals("summary")) {// 汇总
			if (beginDateStr.equals(endDateStr)) {
				result.put("summarydate", beginDateStr);
			} else {
				result.put("summarydate", beginDateStr + " ~ " + endDateStr);
			}
		}
		final RpcMap map = new RpcMap();
		map.putAll(result);

		return map;
	}

	@Override
	public RpcMap getAggregateDayById(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
		final Map<String, Object> parameter = new HashMap<String, Object>();

		// 业务参数
		parameter.put("key", loadConfig.<String> get("key"));
		parameter.put("beganTime", loadConfig.<Date> get("startTime"));
		parameter.put("endTime", loadConfig.<Date> get("endTime"));
		parameter.put("list", loadConfig.<String> get("type"));

		final String show = loadConfig.<String> get("show");
		DayIdDate data = null;
		if (show.equals("detail")) {// 明细
			data = selectOne(productId, AD + "getAggregateDaydetailById", parameter);
		}
		if (show.equals("summary")) {// 汇总
			data = selectOne(productId, AD + "getAggregateDayById", parameter);
		}

		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("reg", data.getReg());
		result.put("regLogin", data.getRegLogin());
		result.put("newReg", data.getNewReg());
		result.put("newRegRatio", data.getNewRegRatio());
		result.put("subNewReg", data.getSubNewReg());
		result.put("subNewRegRatio", data.getSubNewRegRatio());
		result.put("oldReg", data.getOldReg());
		result.put("oldRegRatio", data.getOldRegRatio());
		result.put("newLogin", data.getNewLogin());
		result.put("subNewLogin", data.getSubNewLogin());
		result.put("oldLogin", data.getOldLogin());
		result.put("keyName", "汇总");
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String beginDateStr = (String) parameter.get("beganTime");
		if (beginDateStr == null) {
			beginDateStr = "开始";
		} else {
			try {
				final Date beginDate = DateUtils.parseDate(beginDateStr, new String[] { "yyyy-MM-dd HH:mm:ss" });
				beginDateStr = format.format(beginDate);
			} catch (final ParseException e) {
				beginDateStr = "开始";
			}
		}
		String endDateStr = (String) parameter.get("endTime");
		if (endDateStr == null) {
			endDateStr = "至今";
		} else {
			try {
				final Date endDate = DateUtils.parseDate(endDateStr, new String[] { "yyyy-MM-dd HH:mm:ss" });
				endDateStr = format.format(DateUtils.addDays(endDate, -1));
			} catch (final ParseException e) {
				endDateStr = "至今";
			}
		}
		if (show.equals("detail")) {// 明细
			if (beginDateStr.equals(endDateStr)) {
				result.put("date", beginDateStr);
			} else {
				result.put("date", beginDateStr + " ~ " + endDateStr);
			}
		}
		if (show.equals("summary")) {// 汇总
			if (beginDateStr.equals(endDateStr)) {
				result.put("summarydate", beginDateStr);
			} else {
				result.put("summarydate", beginDateStr + " ~ " + endDateStr);
			}
		}
		final RpcMap map = new RpcMap();
		map.putAll(result);

		return map;
	}

}
