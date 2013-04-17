/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ADDataServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc.ADDataService;
import com.iwgame.gm.p1.adcollect.server.util.StringIsEmpty;
import com.iwgame.gm.p1.adcollect.shared.model.ADDataBase;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * 类说明
 * 
 * @简述： 百度广告实时追踪
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-13 下午12:30:31
 */
public class ADDataServiceImpl extends BaseService implements ADDataService, ExportQuery {
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(ADDataServiceImpl.class);

	@Override
	public String getADDataListById(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
		String returnData = "";
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("adid", loadConfig.<Integer> get("adid"));
			if ((null == loadConfig.<Date> get("startTime")) || (null == loadConfig.<Date> get("endTime"))) {
				return null;
			}
			parameter.put("startTime", loadConfig.<Date> get("startTime"));
			parameter.put("endTime", loadConfig.<Date> get("endTime"));
			parameter.put("mid", "");
			parameter.put("type", 1);
			parameter.put("total", 0);

			if (parameter.get("adid").toString().equals("")) {
				parameter.put("isKey", 0); // 0 空 1 是id 2 是关键字
			} else {
				if (loadConfig.<String> get("isKey").equals("key")) {
					parameter.put("isKey", 2); // 0 空 1 是id 2 是关键字
				} else if (loadConfig.<String> get("isKey").equals("id")) {
					parameter.put("isKey", 1); // 0 空 1 是id 2 是关键字
				} else {
					parameter.put("isKey", 0); // 0 空 1 是id 2 是关键字
				}
			}

			parameter.put("isload", 0);// 有分页

			List<ADDataBase> par = selectList(productId, "ad.addata.spShowRptBaiduMinute", parameter);
			int  total = (Integer) parameter.get("total");
			PagingLoadResult<ADDataBase> result = new PagingLoadResult<ADDataBase>();
			result.setRows(par);
			result.setTotal(total);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);
		} catch (Exception e) {
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	public List<BaseModelData> query(final Map<String, String> arg0) {
		List<BaseModelData> datas = null;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", 0);
			parameter.put("offset", 0);
			parameter.put("adid", arg0.get("adid"));
			parameter.put("startTime", arg0.get("startTime"));
			parameter.put("endTime", arg0.get("endTime"));
			parameter.put("mid", "");
			parameter.put("type", 1);
			parameter.put("isload", 1);// 无分也

			if (parameter.get("adid").toString().equals("")) {
				parameter.put("isKey", 0); // 0 空 1 是id 2 是关键字
			} else {
				if (arg0.get("isKey").equals("key")) {
					parameter.put("isKey", 2); // 0 空 1 是id 2 是关键字
				} else if (arg0.get("isKey").equals("id")) {
					parameter.put("isKey", 1); // 0 空 1 是id 2 是关键字
				} else {
					parameter.put("isKey", 0); // 0 空 1 是id 2 是关键字
				}
			}

			final String productId = arg0.get("productId");
			// 调用存储
			final List<ADDataBase> list  = selectList(productId, "ad.addata.spShowRptBaiduMinute", parameter);
			if (list.size() > 0) {
				datas = new ArrayList<BaseModelData>();

				for (final ADDataBase adDataBase : list) {
					final BaseModelData data = new BaseModelData();
					data.set("date", StringIsEmpty.isEmpty(adDataBase.getDate()));
					data.set("adId", StringIsEmpty.isEmpty(adDataBase.getAdId() + ""));
					data.set("click", StringIsEmpty.isEmpty(adDataBase.getClick() + ""));
					data.set("ipClick", StringIsEmpty.isEmpty(adDataBase.getIpClick() + ""));
					data.set("reg", StringIsEmpty.isEmpty(adDataBase.getReg() + ""));
					data.set("ipReg", StringIsEmpty.isEmpty(adDataBase.getIpReg() + ""));
					datas.add(data);
				}
			}
		} catch (Exception e) {
			logger4j.error(e);
		}
		return datas;
	}

}
