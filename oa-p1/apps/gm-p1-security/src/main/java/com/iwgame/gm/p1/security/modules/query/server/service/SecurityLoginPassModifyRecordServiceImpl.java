/**      
* SecurityLoginPassModifyRecordServiceImpl.java Create on 2012-11-20     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.server.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gwt.dev.util.collect.HashMap;
import com.iwgame.gm.p1.security.common.server.util.DateHelper;
import com.iwgame.gm.p1.security.common.server.util.LoadResultHelper;
import com.iwgame.gm.p1.security.common.shared.bean.GlobalResource;
import com.iwgame.gm.p1.security.common.shared.model.LoginPassModifyRecord;
import com.iwgame.gm.p1.security.modules.query.server.dao.SecurityLoginPassModifyRecordDao;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityLoginPassModifyRecordService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;

/** 
 * @简述: 账号改密记录查询实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-20 下午03:27:18 
 */
@Service
public class SecurityLoginPassModifyRecordServiceImpl implements SecurityLoginPassModifyRecordService{

	@Resource(name="securityLoginPassModifyRecordDaoImpl")
	private SecurityLoginPassModifyRecordDao modifyRecordDao;
	@Resource(name="globalResource")
	private GlobalResource resource;
	private final Logger log4j = Logger.getLogger(SecurityLoginPassModifyRecordServiceImpl.class);
	@Override
	public String loadRecords(String productId,BaseFilterPagingLoadConfig loadConfig)
			throws Exception {
			String resultJson = StringUtils.EMPTY;
			Map<String, Object> parameter = new HashMap<String, Object>();
			if (loadConfig != null) {
				String username = loadConfig.<String>get("username");
				List<String> usernames = null;
				if (StringUtils.isNotBlank(username)) {
					usernames=Arrays.asList(StringUtils.split(username, ','));
				}
				parameter.put("list", usernames);
				Date startDate = loadConfig.<Date>get("startDate");
				Date endDate = loadConfig.<Date>get("endDate");
				if (startDate!=null && endDate!=null) {
					parameter.put("startDate", DateHelper.getDateString(startDate, "yyyy-MM-dd"));
					parameter.put("endDate", DateHelper.getDateString(endDate, "yyyy-MM-dd"));
				}
				putParams(loadConfig, parameter);
				try {
					List<LoginPassModifyRecord> list = modifyRecordDao.getRecords(productId,parameter);
					Integer total = modifyRecordDao.countRecords(productId,parameter);
					resultJson = LoadResultHelper.buildResult(list, (Integer)parameter.get("limit"), (Integer)parameter.get("offset"), total);
				} catch (Exception e) {
					log4j.error("查询改密记录失败:", e);
				}
			}
		return resultJson;
	}

	
	@Override
	public List<LoginPassModifyRecord> loadRecords4Export(String productId,Map<String, String> parameters)
			throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		if (parameters!=null) {
			if (parameters.containsKey("startDate")) {
				Date startDate = new Date(Long.parseLong(parameters.get("startDate")));
				parameter.put("startDate", DateHelper.getDateString(startDate, "yyyy-MM-dd"));
			}
			if (parameters.containsKey("endDate")) {
				Date endDate = new Date(Long.parseLong(parameters.get("endDate")));	
				parameter.put("endDate", DateHelper.getDateString(endDate, "yyyy-MM-dd"));
			}
			String username = parameters.get("username");
			List<String> usernames = null;
			if (StringUtils.isNotBlank(username)) {
				usernames=Arrays.asList(StringUtils.split(username, ','));
			}
			parameter.put("list", usernames);
			parameter.put("offset", 0);
			parameter.put("limit", resource.getExportMaxNum());
			try {
				List<LoginPassModifyRecord> list = modifyRecordDao.getRecords(productId,parameter);
				
				return list;
			} catch (Exception e) {
				log4j.error("查询改密记录失败:", e);
			}
		}
		
		return null;
	}
	/**
	 * @简述: 追加通用参数
	 * @param loadConfig
	 * @param parameter
	 */
	private void putParams(BaseFilterPagingLoadConfig loadConfig,
			Map<String, Object> parameter) {
		parameter.put("offset", loadConfig.<Integer> get("offset"));
		parameter.put("limit", loadConfig.<Integer> get("limit"));
		String sortColumn = loadConfig.getSortField();
		if (sortColumn==null) {
			sortColumn="id";
		}
		String sortType = loadConfig.getSortDir().name();
		if (sortType.equals("NONE")) {
			sortType="desc";
		}
		parameter.put("sortColumn",sortColumn);
		parameter.put("sortType", sortType);
	}
}
