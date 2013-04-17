/**      
* SecurityKilledRecordServiceImpl.java Create on 2012-11-21     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.server.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iwgame.gm.p1.security.common.server.dao.KilledRecordDao;
import com.iwgame.gm.p1.security.common.server.util.DateHelper;
import com.iwgame.gm.p1.security.common.server.util.LoadResultHelper;
import com.iwgame.gm.p1.security.common.shared.bean.GlobalResource;
import com.iwgame.gm.p1.security.common.shared.model.KilledRecord;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityKilledRecordService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/** 
 * @简述: 封杀记录记录查询实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-21 下午02:49:49 
 * @Version 1.0
 * 
 */
@Service
public class SecurityKilledRecordServiceImpl implements SecurityKilledRecordService{

	@Resource(name="killedRecordDaoImpl")
	private KilledRecordDao killedRecordDao;
	@Resource(name="globalResource")
	private GlobalResource resource;
	private final Logger log4j = Logger.getLogger(SecurityKilledRecordServiceImpl.class);
	@Override
	public String loadRecords(String productID,BaseFilterPagingLoadConfig loadConfig)
			throws Exception {
		String resultJson = StringUtils.EMPTY;
		try {
			Map<String, Object> parameter = new HashMap<String, Object>();
			if (loadConfig != null) {
				parameter.put("causeType", loadConfig.<String>get("causeType"));
				parameter.put("days", loadConfig.<String>get("days"));
				parameter.put("operator", loadConfig.<String>get("operator"));
				parameter.put("type", loadConfig.<String>get("type"));
				parameter.put("batchid", loadConfig.<String>get("batchid"));
				parameter.put("username", loadConfig.<String>get("username"));
				parameter.put("handlerStatus", loadConfig.<String>get("handlerStatus"));
				Date startDate = loadConfig.<Date>get("startDate");
				Date endDate = loadConfig.<Date>get("endDate");
				if (startDate!=null && endDate!=null) {
					parameter.put("startDate", DateHelper.getDateString(startDate, "yyyy-MM-dd"));
					parameter.put("endDate", DateHelper.getDateString(endDate, "yyyy-MM-dd"));
				}
				putParams(loadConfig, parameter);
				
				List<KilledRecord> list = killedRecordDao.getRecords(productID,parameter);
				Integer total = killedRecordDao.countRecords(productID,parameter);
				resultJson = LoadResultHelper.buildResult(list, (Integer)parameter.get("limit"), (Integer)parameter.get("offset"), total);
			}
		} catch (Exception e) {
			log4j.error("查询封杀记录失败:", e);
		}
		return resultJson;
	}

	@Override
	public List<KilledRecord> loadRecords4Export(String productID,
			Map<String, String> parameters) throws Exception {
			if (parameters!=null) {
				if (parameters.containsKey("startDate")) {
					Date startDate = new Date(Long.parseLong(parameters.get("startDate")));
					parameters.put("startDate", DateHelper.getDateString(startDate, "yyyy-MM-dd"));
				}
				if (parameters.containsKey("endDate")) {
					Date endDate = new Date(Long.parseLong(parameters.get("endDate")));	
					parameters.put("endDate", DateHelper.getDateString(endDate, "yyyy-MM-dd"));
				}
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.putAll(parameters);
				maps.put("offset", 0);
				maps.put("limit", resource.getExportMaxNum());
				try {
					List<KilledRecord> recordBeans  = killedRecordDao.getRecords(productID, maps);
					return recordBeans;
				} catch (Exception e) {
					log4j.error("查询封杀记录失败:", e);
				}
			}
		return null;
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
			sortColumn="optime";
		}else if (sortColumn.equals("causeType")) {
			sortColumn = "cause_type";
		}
		String sortType = loadConfig.getSortDir().name();
		if (sortType.equals("NONE")) {
			sortType="desc";
		}
		parameter.put("sortColumn",sortColumn);
		parameter.put("sortType", sortType);
	}
}
