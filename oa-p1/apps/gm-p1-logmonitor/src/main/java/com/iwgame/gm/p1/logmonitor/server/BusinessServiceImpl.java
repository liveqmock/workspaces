/**      
 * BusinessServiceImpl.java Create on 2012-9-3     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.iwgame.gm.p1.logmonitor.modules.business.shared.model.BizLogDetailModelBean;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.model.BizLogModelBean;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.model.SourceWarningModelBean;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.model.SourceWarningViewBean;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.model.WarningModelBean;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc.BusinessService;
import com.iwgame.ui.core.client.data.PagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;

/**
 * @ClassName: BusinessServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-3 下午03:42:53
 * @Version 1.0
 * 
 */
public class BusinessServiceImpl extends BaseService implements BusinessService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc.BusinessService
	 * #getLogData(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getLogData(final String productId, final String tableType, final String dateType,
			final int threshold, final String filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tableType", tableType);
		params.put("dateType", dateType);
		if (threshold > 0) {
			params.put("threshold", threshold);
		}
		if ((filter != null) && !"".equals(filter)) {
			params.put("filter", filter);
		}
		List<BizLogModelBean> datas = selectList(productId, "logmonitor.biz.fetchLog", params);
		PagingLoadResult<BizLogModelBean> results = new PagingLoadResult<BizLogModelBean>(datas.size(), 0, 100);
		results.setRows(datas);
		return GridHelper.buildResults(results);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc.BusinessService
	 * #getLogDetailDatas(java.lang.String,
	 * com.iwgame.ui.core.client.data.PagingLoadConfig)
	 */
	@Override
	public String getLogDetailDatas(final String productId, final PagingLoadConfig loadConfig) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (loadConfig.getPropertyNames().contains("requestStatus")) {
			params.put("requestStatus", loadConfig.get("requestStatus"));
		}
		loadParams("requestStatus", loadConfig, params);
		loadParams("startDate", loadConfig, params);
		loadParams("endDate", loadConfig, params);
		loadParams("requestType", loadConfig, params);
		loadParams("requestIp", loadConfig, params);
		loadParams("requestSource", loadConfig, params);
		loadParams("requestSourceExt", loadConfig, params);
		loadParams("requestUsername", loadConfig, params);
		params.put("limit", loadConfig.getLimit());
		params.put("offset", loadConfig.getOffset());
		List<BizLogDetailModelBean> datas = selectList(productId, "logmonitor.biz.fectchLogDetails", params);
		Integer counts = selectOne(productId, "logmonitor.biz.fectchLogDetailsCount", params);
		PagingLoadResult<BizLogDetailModelBean> results = new PagingLoadResult<BizLogDetailModelBean>(counts,
				loadConfig.getOffset(), loadConfig.getLimit());
		results.setRows(datas);
		return GridHelper.buildResults(results);
	}

	private void loadParams(final String paramName, final PagingLoadConfig loadConfig, final Map<String, Object> params) {
		if (loadConfig.getPropertyNames().contains(paramName)) {
			params.put(paramName, loadConfig.get(paramName));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc.BusinessService
	 * #getWarningData(java.lang.String)
	 */
	@Override
	public String getWarningData(final String productId) {
		List<WarningModelBean> datas = selectList(productId, "logmonitor.biz.fetchWarning");
		PagingLoadResult<WarningModelBean> results = new PagingLoadResult<WarningModelBean>(100, 0, 100);
		results.setRows(datas);
		return GridHelper.buildResults(results);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc.BusinessService
	 * #getWaringData(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<SourceWarningViewBean> getWaringData(final String productId, final Date date) {
		DateFormat hf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hour", hf.format(date));
		params.put("day", df.format(date));
		List<SourceWarningModelBean> datas = selectList(productId, "logmonitor.biz.fetchSourceWarning", params);
		List<SourceWarningViewBean> results = new ArrayList<SourceWarningViewBean>();
		Map<String, SourceWarningViewBean> temps = new LinkedHashMap<String, SourceWarningViewBean>();
		for (SourceWarningModelBean bean : datas) {
			SourceWarningViewBean vb = null;
			if (temps.containsKey(bean.getName())) {
				vb = temps.get(bean.getName());
			} else {
				vb = new SourceWarningViewBean();
				vb.setName(bean.getName());
				vb.setThreshold(bean.getThreshold());
				temps.put(bean.getName(), vb);
			}
			if ("登录".equals(bean.getType())) {
				vb.setLoginHourSuccess(bean.getHourSuccess());
				vb.setLoginHourFailed(bean.getHourFailed());
				vb.setLoginHourTotal(bean.getHourTotal());
				vb.setLoginDayTotal(bean.getDayTotal());
			}
			if ("注册".equals(bean.getType())) {
				vb.setRegistHourSuccess(bean.getHourSuccess());
				vb.setRegistHourFailed(bean.getHourFailed());
				vb.setRegistHourTotal(bean.getHourTotal());
				vb.setRegistDayTotal(bean.getDayTotal());
			}
			if ("账号检测".equals(bean.getType())) {
				vb.setAccountHourSuccess(bean.getHourSuccess());
				vb.setAccountHourFailed(bean.getHourFailed());
				vb.setAccountHourTotal(bean.getHourTotal());
				vb.setAccountDayTotal(bean.getDayTotal());
			}
		}
		for (Map.Entry<String, SourceWarningViewBean> entry : temps.entrySet()) {
			results.add(entry.getValue());
		}
		return results;
	}
}
