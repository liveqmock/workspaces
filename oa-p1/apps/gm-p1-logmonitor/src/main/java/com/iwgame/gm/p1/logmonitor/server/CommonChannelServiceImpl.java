/****************************************************************
 *  文件名     ： CommonChannelServiceImpl.java
 *  日期         :  2012-10-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.AppSource;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.BizDict;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.CommonChannelLogDetailModelBena;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model.CommonChannelLogReportModelBena;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc.CommonChannelService;
import com.iwgame.ui.core.client.data.PagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xconnector.GMConnection.TargetType;
import com.iwgame.xconnector.db.DBConnection;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/** 
 * @ClassName:    CommonChannelServiceImpl 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-19上午11:17:51
 * @Version:      1.0 
 */

@NeedAuthorization
@SuppressWarnings("unchecked")
public class CommonChannelServiceImpl implements CommonChannelService{
	
	private final Logger logger = Logger.getLogger("gm-p1-logmonitor", "GM");
	
	@Resource
	private DBConnection dbConnectorConnection;
	
	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc.CommonChannelService#retrieveAppSource()
	 */
	
	@Override
	
	public List<AppSource> retrieveAppSource() {
		logger.writeSuccessfullyLog("logmonitor-xhttpservice", "查询AppSource列表");
		List<AppSource> datas = (List<AppSource>) getSqlSessionTemplate().selectList("logmonitor-xhttpservice.getAppSources");
		return datas;
	}

	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc.CommonChannelService#retrieveBizDict()
	 */
	@Override
	public List<BizDict> retrieveBizDict() {
		logger.writeSuccessfullyLog("logmonitor-xhttpservice", "查询BizDict列表");
		List<BizDict> datas = (List<BizDict>) getSqlSessionTemplate().selectList("logmonitor-xhttpservice.getBizDicts");
		return datas;
	}
	
	
	protected SqlSessionTemplate getSqlSessionTemplate(){
		return dbConnectorConnection.getClient(TargetType.GAME, "P-P1", "P-P1-xhttpservicelog", null);
	}

	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc.CommonChannelService#getAllLogData()
	 */
	@Override
	public String getAllLogData(String productid, PagingLoadConfig loadConfig) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productid", productid); 
		params.put("appname", loadConfig.get("appname")); 
		params.put("bizcode", loadConfig.get("bizcode"));
		params.put("logtype", loadConfig.get("logtype"));
		params.put("startDate", loadConfig.get("startDate"));
		params.put("endDate", loadConfig.get("endDate"));
		params.put("keywords", loadConfig.get("keywords"));
		params.put("limit", loadConfig.getLimit());
		params.put("offset", loadConfig.getOffset());
		List<CommonChannelLogDetailModelBena> datas = (List<CommonChannelLogDetailModelBena>) getSqlSessionTemplate().selectList("logmonitor-xhttpservice.getAllLogData",params);
		Integer counts = (Integer) getSqlSessionTemplate().selectOne("logmonitor-xhttpservice.getAllLogDataCount",params);
		PagingLoadResult<CommonChannelLogDetailModelBena> results = new PagingLoadResult<CommonChannelLogDetailModelBena>(counts,loadConfig.getOffset(), loadConfig.getLimit());
		results.setRows(datas);
		return GridHelper.buildResults(results);
	}

	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc.CommonChannelService#getLogreport(java.lang.String, com.iwgame.ui.core.client.data.PagingLoadConfig)
	 */
	@Override
	public String getLogreport(String productid, PagingLoadConfig loadConfig) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productid", productid); 
		params.put("bizcode", loadConfig.get("bizcode"));
		params.put("appname", loadConfig.get("appname"));
		params.put("startDate", loadConfig.get("startDate"));
		params.put("endDate", loadConfig.get("endDate"));
		params.put("limit", loadConfig.getLimit());
		params.put("offset", loadConfig.getOffset());
		List<CommonChannelLogReportModelBena> datas  = null;
		String model = loadConfig.get("model") == null ? "0" : loadConfig.get("model").toString();
		if(Integer.valueOf(model ) == 1){
			//按月统计
			datas = (List<CommonChannelLogReportModelBena>) getSqlSessionTemplate().selectList("logmonitor-xhttpservice.getLogReportMonth",params);
		}else if(Integer.valueOf(model) == 2){
			//按年统计
			datas = (List<CommonChannelLogReportModelBena>) getSqlSessionTemplate().selectList("logmonitor-xhttpservice.getLogReportYear",params);
		}else{
			//按日统计
			datas = (List<CommonChannelLogReportModelBena>) getSqlSessionTemplate().selectList("logmonitor-xhttpservice.getLogReportDay",params);
		}
		PagingLoadResult<CommonChannelLogReportModelBena> results = new PagingLoadResult<CommonChannelLogReportModelBena>();
		results.setRows(datas);
		results.setTotal(datas.size());
		return GridHelper.buildResults(results);
	}

}
