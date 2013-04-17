/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： CrlServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.security.access.AccessDeniedException;

import com.iwgame.gm.p1.adcollect.modules.customreport.shared.rpc.CustomreportService;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.BlackListRules;
import com.iwgame.gm.p1.adcollect.shared.model.CustomReportParam;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-9-25 下午03:43:07
 */
@NeedAuthorization
public class CustomreportServiceImpl extends BaseService implements CustomreportService {
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_NETBAR, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(CustomreportServiceImpl.class);
	private static final String NAMESPACE = "adcollect.customreport.";
                                                      
	@Override
	@AccessResource(name = "ad-customreport-getCustomReportList")
	public String getCustomReportList(String productId,BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException {
		String returnData = "";
		try {
			// 构建参数&查询
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页&排序参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));

			parameter.put("beginDate", loadConfig.<String> get("beginDate"));
			parameter.put("endDate", loadConfig.<String> get("endDate"));
			parameter.put("name", loadConfig.<String> get("name"));
			parameter.put("operator", loadConfig.<String> get("operator"));
			parameter.put("status", loadConfig.<String> get("status"));

			final Object totalCount = selectOne(productId, NAMESPACE + "getCustomReportListTotal", parameter);
			final List<BlackListRules> resultList = selectList(productId, NAMESPACE + "getCustomReportList", parameter);

			// 返回结果
			final PagingLoadResult<BlackListRules> result = new PagingLoadResult<BlackListRules>();
			result.setRows(resultList);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal((Integer) totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));

			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_GETCUSTOMREPORTLIST, "查询定制报表列表成功");
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_GETCUSTOMREPORTLIST, e);
		}
		return returnData;
	
	}

	@Override
	@AccessResource(name = "ad-customreport-delCustomReport")
	public String delCustomReport(String productId, Map<String, String> paraMap)throws AccessDeniedException {
		String i = "-1";
		try {
			final Map<String, String> parameter = new HashMap<String, String>();
			parameter.put("id", paraMap.get("id").toString());
			final Integer totalCount = delete(productId, NAMESPACE + "delCustomReport", paraMap);
			
			if (totalCount != null) {
				i = String.valueOf(totalCount);
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_DELCUSTOMREPORT, "删除定制报表成功");
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_DELCUSTOMREPORT, e);
		}
		return i;
	
	}

	@Override
	@AccessResource(name = "ad-customreport-addCustomReport")
	public String addCustomReport(String productId, Map<String, Object> paraMap,CustomReportParam reportParam)throws AccessDeniedException {
		String i = "-1";
		try {
			final Map<String, String> parameter = new HashMap<String, String>();
			parameter.put("name", paraMap.get("name").toString());
			parameter.put("remark", paraMap.get("remark").toString());
			parameter.put("operator", paraMap.get("operator").toString());
			parameter.put("param", JSONObject.fromObject(reportParam).toString());
			
			final Integer totalCount = insert(productId, NAMESPACE + "addCustomReport", parameter);

			if (totalCount != null) {
				i = String.valueOf(totalCount);
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADDCUSTOMREPORT, "增加定制报表成功");
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADDCUSTOMREPORT, e);
		}
		return i;
	
	}

}
