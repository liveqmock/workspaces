/**      
* SecurityIwResultTrackServiceImpl.java Create on 2012-11-27     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.server.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gwt.dev.util.collect.HashMap;
import com.iwgame.gm.p1.security.common.server.dao.IwResultTrackDao;
import com.iwgame.gm.p1.security.common.server.util.DateHelper;
import com.iwgame.gm.p1.security.common.server.util.LoadResultHelper;
import com.iwgame.gm.p1.security.common.shared.model.IwResultTrack;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityIwResultTrackService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/** 
 * @ClassName: SecurityIwResultTrackServiceImpl 
 * @Description: TODO(...) 
 * @author Administrator
 * @date 2012-11-27 上午11:10:50 
 * @Version 1.0
 * 
 */
@Service
@NeedAuthorization
public class SecurityIwResultTrackServiceImpl implements
		SecurityIwResultTrackService {

	@Resource(name="iwResultTrackDaoImpl")
	private IwResultTrackDao iwResultTrackDao;
	
	private final Logger log4j = Logger.getLogger(SecurityIwResultTrackServiceImpl.class);
	@Override
	public String loadRecords(String productID,
			BaseFilterPagingLoadConfig loadConfig) throws Exception {
			String resultJson = StringUtils.EMPTY;
			Map<String, Object> parameter = new HashMap<String, Object>();
			if (loadConfig != null) {
				parameter.put("operator", loadConfig.<String>get("operator"));
				parameter.put("batchid", loadConfig.<String>get("batchid"));
				Date startDate = loadConfig.<Date>get("startDate");
				Date endDate = loadConfig.<Date>get("endDate");
				if (startDate!=null && endDate!=null) {
					parameter.put("startDate", DateHelper.getDateString(startDate, "yyyy-MM-dd"));
					parameter.put("endDate", DateHelper.getDateString(endDate, "yyyy-MM-dd"));
				}
				putParams(loadConfig, parameter);
				try {
					List<IwResultTrack> list = iwResultTrackDao.getRecords(productID,parameter);
					Integer total = iwResultTrackDao.countRecords(productID,parameter);
					resultJson = LoadResultHelper.buildResult(list, (Integer)parameter.get("limit"), (Integer)parameter.get("offset"), total);
				} catch (Exception e) {
					log4j.error("查询结果追踪失败", e);
				}
			}
		return resultJson;
	}

	 /*
	  * 追加通用参数
	  */
	private void putParams(BaseFilterPagingLoadConfig loadConfig,
			Map<String, Object> parameter) {
		parameter.put("offset", loadConfig.<Integer> get("offset"));
		parameter.put("limit", loadConfig.<Integer> get("limit"));
		String sortColumn = loadConfig.getSortField();
		if (sortColumn==null) {
			sortColumn="id";
		}else if ("submitTime".equalsIgnoreCase(sortColumn)) {
			sortColumn = "submit_time";
		}else if ("finishTime".equalsIgnoreCase(sortColumn)) {
			sortColumn = "finish_time";
		}
		String sortType = loadConfig.getSortDir().name();
		if (sortType.equals("NONE")) {
			sortType="desc";
		}
		parameter.put("sortColumn",sortColumn);
		parameter.put("sortType", sortType);
	}
}
