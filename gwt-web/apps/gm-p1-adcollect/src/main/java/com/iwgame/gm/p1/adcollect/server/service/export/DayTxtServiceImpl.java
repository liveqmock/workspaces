/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DayTxtServiceImpl.java
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
import com.iwgame.gm.p1.adcollect.shared.model.DayTxtDate;
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
 * @创建时间：2012-8-28 下午02:18:09
 */
@NeedAuthorization
public class DayTxtServiceImpl extends BaseService implements ExportQuery {

	private static final String AD = "ad.day.";

	@Override
	@AccessResource(name = "ad-reports-txt-day-export")
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
			List<DayTxtDate> list = null;
			DayTxtDate dayTxt = null;
			if (show.equals("detail")) {// 明细
				list = selectList(productId, "ad.day." + "getDaydetailByTxt", parameter);
				dayTxt = selectOne(productId, AD + "getAggregateDaydetailByTxt", parameter);
			}
			if (show.equals("summary")) {// 汇总
				list = selectList(productId, "ad.day." + "getDaySummaryByTxt", parameter);
				dayTxt = selectOne(productId, AD + "getAggregateDayByTxt", parameter);
			}
			if (list.size() > 0) {
				datas = new ArrayList<BaseModelData>();
				// 时间格式转换
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				final NumberFormat nfRatio = NumberFormat.getPercentInstance(Locale.CHINA);
				nfRatio.setMaximumFractionDigits(2);

				final NumberFormat nfNum = NumberFormat.getInstance(Locale.CHINA);
				nfNum.setMaximumFractionDigits(2);

				final BaseModelData dataSummary = new BaseModelData();
				dataSummary.set("id", StringIsEmpty.isEmpty(""));
				dataSummary.set("type", StringIsEmpty.isEmpty(""));
				dataSummary.set("keyName", "汇总");
				dataSummary.set("click", StringIsEmpty.isEmpty(dayTxt.getClick() + ""));
				dataSummary.set("reg", StringIsEmpty.isEmpty(dayTxt.getReg() + ""));
				dataSummary.set("regLogin", StringIsEmpty.isEmpty(dayTxt.getRegLogin() + ""));
				dataSummary.set("newReg", StringIsEmpty.isEmpty(dayTxt.getNewReg() + ""));
				dataSummary.set("newRegRatio", StringIsEmpty.isEmpty(nfRatio.format(dayTxt.getNewRegRatio()) + ""));
				dataSummary.set("subNewReg", StringIsEmpty.isEmpty(dayTxt.getSubNewReg() + ""));
				dataSummary.set("subNewRegRatio",
						StringIsEmpty.isEmpty(nfRatio.format(dayTxt.getSubNewRegRatio()) + ""));
				dataSummary.set("oldReg", StringIsEmpty.isEmpty(dayTxt.getOldReg() + ""));
				dataSummary.set("oldRegRatio", StringIsEmpty.isEmpty(nfRatio.format(dayTxt.getOldRegRatio()) + ""));
				dataSummary.set("newLogin", StringIsEmpty.isEmpty(dayTxt.getNewLogin() + ""));
				dataSummary.set("subNewLogin", StringIsEmpty.isEmpty(dayTxt.getSubNewLogin() + ""));
				dataSummary.set("oldLogin", StringIsEmpty.isEmpty(dayTxt.getOldLogin() + ""));
				dataSummary.set("used", StringIsEmpty.isEmpty(dayTxt.getUsed() + ""));
				dataSummary.set("show", StringIsEmpty.isEmpty(dayTxt.getShow() + ""));
				dataSummary.set("clickPrice", StringIsEmpty.isEmpty(nfNum.format(dayTxt.getClickPrice()) + ""));
				dataSummary.set("rank", " ");
				dataSummary.set("clickRatio", StringIsEmpty.isEmpty(nfRatio.format(dayTxt.getClickRatio()) + ""));
				dataSummary.set("regRatio", StringIsEmpty.isEmpty(nfRatio.format(dayTxt.getRegRatio()) + ""));
				dataSummary.set("cpm", StringIsEmpty.isEmpty(nfNum.format(dayTxt.getCpm()) + ""));
				dataSummary.set("cpa", StringIsEmpty.isEmpty(nfNum.format(dayTxt.getCpa()) + ""));
				dataSummary.set("loginPrice", StringIsEmpty.isEmpty(nfNum.format(dayTxt.getLoginPrice()) + ""));
				dataSummary.set("adIdSub", " ");

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
				for (final DayTxtDate dayTxtDate : list) {
					final BaseModelData data = new BaseModelData();

					data.set("id", StringIsEmpty.isEmpty(dayTxtDate.getId() + ""));
					data.set("type", StringIsEmpty.isEmpty(dayTxtDate.getType() + ""));
					data.set("keyName", StringIsEmpty.isEmpty(dayTxtDate.getKeyName() + ""));
					data.set("click", StringIsEmpty.isEmpty(dayTxtDate.getClick() + ""));
					data.set("reg", StringIsEmpty.isEmpty(dayTxtDate.getReg() + ""));
					data.set("regLogin", StringIsEmpty.isEmpty(dayTxtDate.getRegLogin() + ""));
					data.set("newReg", StringIsEmpty.isEmpty(dayTxtDate.getNewReg() + ""));
					data.set("newRegRatio", StringIsEmpty.isEmpty(nfRatio.format(dayTxtDate.getNewRegRatio()) + ""));
					data.set("subNewReg", StringIsEmpty.isEmpty(dayTxtDate.getSubNewReg() + ""));
					data.set("subNewRegRatio",
							StringIsEmpty.isEmpty(nfRatio.format(dayTxtDate.getSubNewRegRatio()) + ""));
					data.set("oldReg", StringIsEmpty.isEmpty(dayTxtDate.getOldReg() + ""));
					data.set("oldRegRatio", StringIsEmpty.isEmpty(nfRatio.format(dayTxtDate.getOldRegRatio()) + ""));
					data.set("newLogin", StringIsEmpty.isEmpty(dayTxtDate.getNewLogin() + ""));
					data.set("subNewLogin", StringIsEmpty.isEmpty(dayTxtDate.getSubNewLogin() + ""));
					data.set("oldLogin", StringIsEmpty.isEmpty(dayTxtDate.getOldLogin() + ""));
					if (show.equals("detail") && (null != dayTxtDate.getDate())) {// 明细
						data.set("date", format.format(dayTxtDate.getDate()));
					}
					if (show.equals("summary")) {// 汇总
						if (beginDateStr.equals(endDateStr)) {
							data.set("date", beginDateStr);
						} else {
							data.set("date", beginDateStr + " ~ " + endDateStr);
						}
					}
					data.set("used", StringIsEmpty.isEmpty(nfNum.format(dayTxtDate.getUsed()) + ""));
					data.set("show", StringIsEmpty.isEmpty(dayTxtDate.getShow() + ""));
					data.set("clickPrice", StringIsEmpty.isEmpty(nfNum.format(dayTxtDate.getClickPrice()) + ""));
					data.set("rank", StringIsEmpty.isEmpty(nfNum.format(dayTxtDate.getRank()) + ""));
					data.set("clickRatio", StringIsEmpty.isEmpty(nfRatio.format(dayTxtDate.getClickRatio()) + ""));
					data.set("regRatio", StringIsEmpty.isEmpty(nfRatio.format(dayTxtDate.getRegRatio()) + ""));
					data.set("cpm", StringIsEmpty.isEmpty(nfNum.format(dayTxtDate.getCpm()) + ""));
					data.set("cpa", StringIsEmpty.isEmpty(nfNum.format(dayTxtDate.getCpa()) + ""));
					data.set("loginPrice", StringIsEmpty.isEmpty(nfNum.format(dayTxtDate.getLoginPrice()) + ""));
					data.set("adIdSub", StringIsEmpty.isEmpty(dayTxtDate.getAdIdSub() + ""));

					datas.add(data);
				}
			}
		} catch (final Exception e) {
			// TODO: handle exception
		}
		return datas;
	}

}
