/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DayIdServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service.export;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.security.access.AccessDeniedException;

import com.iwgame.gm.p1.adcollect.server.service.BaseService;
import com.iwgame.gm.p1.adcollect.server.util.StringIsEmpty;
import com.iwgame.gm.p1.adcollect.shared.model.DayIdDate;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-28 下午02:18:39
 */
@NeedAuthorization
public class DayIdServiceImpl extends BaseService implements ExportQuery {

	private static final String AD = "ad.day.";

	@Override
	@AccessResource(name = "ad-reports-id-day-export")
	public List<BaseModelData> query(final Map<String, String> parameters) throws AccessDeniedException{
		List<BaseModelData> datas = null;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", null);
			parameter.put("offset", null);

			// 业务参数
			parameter.put("key", parameters.get("key"));
			parameter.put("beganTime", parameters.get("startTime"));
			parameter.put("endTime", parameters.get("endTime"));
			parameter.put("list", parameters.get("type"));
			final String productId = parameters.get("productId");
			final String show = parameters.get("show");
			List<DayIdDate> list = null;
			DayIdDate dayIdDate = null;
			if (show.equals("detail")) {// 明细
				list = selectList(productId, "ad.day." + "getDaydetailById", parameter);
				dayIdDate = selectOne(productId, AD + "getAggregateDaydetailById", parameter);
			}
			if (show.equals("summary")) {// 汇总
				list = selectList(productId, "ad.day." + "getDaySummaryById", parameter);
				dayIdDate = selectOne(productId, AD + "getAggregateDayById", parameter);
			}

			if (list.size() > 0) {
				datas = new ArrayList<BaseModelData>();
				// 时间格式转换
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				final NumberFormat nfRatio = NumberFormat.getPercentInstance(Locale.CHINA);
				nfRatio.setMaximumFractionDigits(2);

				final NumberFormat nfNum = NumberFormat.getInstance(Locale.CHINA);
				nfNum.setMaximumFractionDigits(2);

				// 汇总
				final BaseModelData dataSummary = new BaseModelData();
				dataSummary.set("id", " ");
				dataSummary.set("adId", " ");
				dataSummary.set("type", " ");
				dataSummary.set("keyName", "总汇");
				dataSummary.set("reg", StringIsEmpty.isEmpty(dayIdDate.getReg() + ""));
				dataSummary.set("regLogin", StringIsEmpty.isEmpty(dayIdDate.getRegLogin() + ""));
				dataSummary.set("newReg", StringIsEmpty.isEmpty(dayIdDate.getNewReg() + ""));
				dataSummary.set("newRegRatio", StringIsEmpty.isEmpty(nfRatio.format(dayIdDate.getNewRegRatio()) + ""));
				dataSummary.set("subNewReg", StringIsEmpty.isEmpty(dayIdDate.getSubNewReg() + ""));
				dataSummary.set("subNewRegRatio",
						StringIsEmpty.isEmpty(nfRatio.format(dayIdDate.getSubNewRegRatio()) + ""));
				dataSummary.set("oldReg", StringIsEmpty.isEmpty(dayIdDate.getOldReg() + ""));
				dataSummary.set("oldRegRatio", StringIsEmpty.isEmpty(nfRatio.format(dayIdDate.getOldRegRatio()) + "&"));
				dataSummary.set("newLogin", StringIsEmpty.isEmpty(dayIdDate.getNewLogin() + ""));
				dataSummary.set("subNewLogin", StringIsEmpty.isEmpty(dayIdDate.getSubNewLogin() + ""));
				dataSummary.set("oldLogin", StringIsEmpty.isEmpty(dayIdDate.getOldLogin() + ""));

				String beginDateStr = (String) parameter.get("beganTime");
				if (beginDateStr == null) {
					beginDateStr = "开始";
				} else {
					try {
						final Date beginDate = DateUtils
								.parseDate(beginDateStr, new String[] { "yyyy-MM-dd HH:mm:ss" });
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
						dataSummary.set("date", beginDateStr);
					} else {
						dataSummary.set("date", beginDateStr + " ~ " + endDateStr);
					}
				}
				if (show.equals("summary")) {// 汇总
					if (beginDateStr.equals(endDateStr)) {
						dataSummary.set("date", beginDateStr);
					} else {
						dataSummary.set("date", beginDateStr + " ~ " + endDateStr);
					}
				}

				datas.add(dataSummary);
				for (final DayIdDate dayIdItme : list) {
					final BaseModelData data = new BaseModelData();

					data.set("id", StringIsEmpty.isEmpty(dayIdItme.getId() + ""));
					data.set("adId", StringIsEmpty.isEmpty(dayIdItme.getAdId() + ""));
					data.set("type", StringIsEmpty.isEmpty(dayIdItme.getType() + ""));
					data.set("keyName", StringIsEmpty.isEmpty(dayIdItme.getKeyName() + ""));
					data.set("reg", StringIsEmpty.isEmpty(dayIdItme.getReg() + ""));
					data.set("regLogin", StringIsEmpty.isEmpty(dayIdItme.getRegLogin() + ""));
					data.set("newReg", StringIsEmpty.isEmpty(dayIdItme.getNewReg() + ""));
					data.set("newRegRatio", StringIsEmpty.isEmpty(nfRatio.format(dayIdItme.getNewRegRatio()) + ""));
					data.set("subNewReg", StringIsEmpty.isEmpty(dayIdItme.getSubNewReg() + ""));
					data.set("subNewRegRatio",
							StringIsEmpty.isEmpty(nfRatio.format(dayIdItme.getSubNewRegRatio()) + ""));
					data.set("oldReg", StringIsEmpty.isEmpty(dayIdItme.getOldReg() + ""));
					data.set("oldRegRatio", StringIsEmpty.isEmpty(nfRatio.format(dayIdItme.getOldRegRatio()) + ""));
					data.set("newLogin", StringIsEmpty.isEmpty(dayIdItme.getNewLogin() + ""));
					data.set("subNewLogin", StringIsEmpty.isEmpty(dayIdItme.getSubNewLogin() + ""));
					data.set("oldLogin", StringIsEmpty.isEmpty(dayIdItme.getOldLogin() + ""));

					if (show.equals("detail") && (null != dayIdItme.getDate())) {// 明细
						data.set("date", format.format(dayIdItme.getDate()));
					}
					if (show.equals("summary")) {// 汇总
						if (beginDateStr.equals(endDateStr)) {
							data.set("date", beginDateStr);
						} else {
							data.set("date", beginDateStr + " ~ " + endDateStr);
						}
					}
					datas.add(data);
				}
			}
		} catch (final Exception e) {
			// TODO: handle exception
		}
		return datas;
	}

}
