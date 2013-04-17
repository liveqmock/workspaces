/**      
 * ConfigServiceImpl.java Create on 2012-9-5     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwgame.gm.p1.logmonitor.modules.config.shared.model.WarningConfigModelBean;
import com.iwgame.gm.p1.logmonitor.modules.config.shared.rpc.ConfigService;
import com.iwgame.ui.core.client.data.PagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;

/**
 * @ClassName: ConfigServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-5 下午01:46:37
 * @Version 1.0
 * 
 */
public class ConfigServiceImpl extends BaseService implements ConfigService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.gm.p1.logmonitor.modules.config.shared.rpc.ConfigService#
	 * getWarningConfigs()
	 */
	@Override
	public String getWarningConfigs(final String productId, final PagingLoadConfig loadConfig) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("limit", loadConfig.getLimit());
		params.put("offset", loadConfig.getOffset());
		List<WarningConfigModelBean> datas = selectList(productId, "logmonitor.config.warningConfigs", params);
		Integer counts = selectOne(productId, "logmonitor.config.warningConfigsCounts");
		PagingLoadResult<WarningConfigModelBean> results = new PagingLoadResult<WarningConfigModelBean>(counts,
				loadConfig.getOffset(), loadConfig.getLimit());
		results.setRows(datas);
		return GridHelper.buildResults(results);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.gm.p1.logmonitor.modules.config.shared.rpc.ConfigService#
	 * updateWarningConfig(java.lang.String, java.util.List, java.util.List,
	 * java.util.List)
	 */
	@Override
	public void updateWarningConfig(final String productId, final WarningConfigModelBean bean) {
		update(productId, "logmonitor.config.updateConfig", bean);
	}

}
