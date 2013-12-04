/**      
 * RealtimeLoginServiceImpl.java Create on 2012-8-31     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwgame.gm.p1.logmonitor.modules.game.shared.model.LoginModelBean;
import com.iwgame.gm.p1.logmonitor.modules.game.shared.rpc.LoginService;
import com.iwgame.ui.core.client.data.PagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;

/**
 * @ClassName: RealtimeLoginServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-8-31 下午04:03:25
 * @Version 1.0
 * 
 */
public class LoginServiceImpl extends BaseService implements LoginService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.gm.p1.logmonitor.modules.game.shared.rpc.LoginService
	 * #getRealtimeLoginDatas(java.lang.String, java.lang.Integer,
	 * java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getRealtimeLoginDatas(final String productId, final Integer errorTimes, final String zoneId,
			final Integer limit) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("errorTimes", errorTimes);
		params.put("zoneId", zoneId);
		params.put("limit", limit);
		List<LoginModelBean> datas = selectList(productId, "logmonitor.game.login.realtime", params);
		PagingLoadResult<LoginModelBean> results = new PagingLoadResult<LoginModelBean>(datas.size(), 0, limit);
		results.setRows(datas);
		return GridHelper.buildResults(results);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.gm.p1.logmonitor.modules.game.shared.rpc.LoginService#
	 * getHistoryLoginDatas(java.lang.String,
	 * com.iwgame.ui.core.client.data.PagingLoadConfig)
	 */
	@Override
	public String getHistoryLoginDatas(final String productId, final PagingLoadConfig loadConfig) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (loadConfig.getPropertyNames().contains("zoneId")) {
			params.put("zoneId", loadConfig.get("zoneId"));
		}
		if (loadConfig.getPropertyNames().contains("errorTimes")) {
			params.put("errorTimes", loadConfig.get("errorTimes"));
		}
		if (loadConfig.getPropertyNames().contains("startDate")) {
			params.put("startDate", loadConfig.get("startDate"));
		}
		if (loadConfig.getPropertyNames().contains("endDate")) {
			params.put("endDate", loadConfig.get("endDate"));
		}
		params.put("limit", loadConfig.getLimit());
		params.put("offset", loadConfig.getOffset());
		List<LoginModelBean> datas = selectList(productId, "logmonitor.game.login.history", params);
		Integer count = selectOne(productId, "logmonitor.game.login.historyCount", params);
		PagingLoadResult<LoginModelBean> results = new PagingLoadResult<LoginModelBean>(count, loadConfig.getOffset(),
				loadConfig.getLimit());
		results.setRows(datas);
		return GridHelper.buildResults(results);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.gm.p1.logmonitor.modules.game.shared.rpc.LoginService#
	 * getWarningData(java.lang.String)
	 */
	@Override
	public List<LoginModelBean> getWarningData(final String productId) {
		List<LoginModelBean> datas = selectList(productId, "logmonitor.game.login.warning");
		return datas;
	}

}
