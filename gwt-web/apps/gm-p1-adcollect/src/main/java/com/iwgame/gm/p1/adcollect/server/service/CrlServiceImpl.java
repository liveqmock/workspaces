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

import org.springframework.security.access.AccessDeniedException;

import com.iwgame.gm.p1.adcollect.modules.crl.shared.rpc.CrlService;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.BlackListRules;
import com.iwgame.gm.p1.adcollect.shared.model.BlackUsers;
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
public class CrlServiceImpl extends BaseService implements CrlService {
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_CRL, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(CrlServiceImpl.class);
	private static final String NAMESPACE = "adcollect.crl.";

	@Override
	@AccessResource(name = "ad-crl-getBlackListRules")
	public String getBlackListRules(String productId, BaseFilterPagingLoadConfig loadConfig)
			throws AccessDeniedException {
		String returnData = "";
		try {
			// 构建参数&查询
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页&排序参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));

			final Object totalCount = selectOne(productId, NAMESPACE + "getBlackListRulesTotal", parameter);
			final List<BlackListRules> resultList = selectList(productId, NAMESPACE + "getBlackListRules", parameter);

			// 返回结果
			final PagingLoadResult<BlackListRules> result = new PagingLoadResult<BlackListRules>();
			result.setRows(resultList);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal((Integer) totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));

			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_GETBLACKLISTRULES, "查询黑名单规则列表成功");
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_GETBLACKLISTRULES, e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "ad-crl-updateStatus")
	public String updateStatus(String productId, String id, String status) throws AccessDeniedException {
		String i = "";
		try {
			final Map<String, Object> parMap = new HashMap<String, Object>();
			parMap.put("id", id);
			parMap.put("status", status);
			// 如果不相等就修改 并记录日志
			final Integer typeCount = (Integer) update(productId, NAMESPACE + "updateBlackListRulesStatus", parMap);
			i = String.valueOf(typeCount);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_UPDATESTATUS, "修改黑名单规则状态成功");
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_UPDATESTATUS, e);
		}
		return i;
	}

	@Override
	@AccessResource(name = "ad-crl-getAccountList")
	public String getAccountList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException {
		String returnData = "";
		try {
			// 构建参数&查询
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页&排序参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));
			// 业务参数
			parameter.put("reason", loadConfig.<String> get("reason"));
			parameter.put("beginDate", loadConfig.<String> get("beginDate"));
			parameter.put("endDate", loadConfig.<String> get("endDate"));
			if (("账号名").equals(loadConfig.<String> get("type"))) {
				parameter.put("username", loadConfig.<String> get("keyword"));
			} else if (("身份证").equals(loadConfig.<String> get("type"))) {
				if (loadConfig.<String> get("keyword").length() != 40) {
					parameter.put("idcard", loadConfig.<String> get("keyword").toLowerCase());
				} else {
					parameter.put("SHA1idcard", loadConfig.<String> get("keyword"));
				}
			} else if (("邮箱").equals(loadConfig.<String> get("type"))) {
				if (loadConfig.<String> get("keyword").length() != 40) {
					parameter.put("email", loadConfig.<String> get("keyword").toLowerCase());
				} else if (loadConfig.<String> get("keyword").contains("@")) {
					parameter.put("email", loadConfig.<String> get("keyword").toLowerCase());
				} else {
					parameter.put("SHA1email", loadConfig.<String> get("keyword"));
				}
			}
			final Object totalCount = selectOne(productId, NAMESPACE + "getAccountListTotal", parameter);
			final List<BlackUsers> resultList = selectList(productId, NAMESPACE + "getAccountList", parameter);

			// 返回结果
			final PagingLoadResult<BlackUsers> result = new PagingLoadResult<BlackUsers>();
			result.setRows(resultList);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal((Integer) totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));

			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_GETACCOUNTLIST, "得到黑名单账户列表成功");
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_GETACCOUNTLIST, e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "ad-crl-getBlackManageList")
	public String getBlackManageList(String productId, BaseFilterPagingLoadConfig loadConfig)
			throws AccessDeniedException {

		String returnData = "";
		try {
			// 构建参数&查询
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页&排序参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));
			// 业务参数
			// 2:身份证 1:邮箱
			parameter.put("type", loadConfig.<String> get("type"));
			parameter.put("beginDate", loadConfig.<String> get("beginDate"));
			parameter.put("endDate", loadConfig.<String> get("endDate"));
			if (loadConfig.<String> get("keyword") != null) {
				if (loadConfig.<String> get("keyword").length() != 40) {
					parameter.put("keyword", loadConfig.<String> get("keyword").toLowerCase());
				} else if (loadConfig.<String> get("keyword").contains("@")) {
					parameter.put("keyword", loadConfig.<String> get("keyword").toLowerCase());
				} else {
					parameter.put("SHA1keyword", loadConfig.<String> get("keyword"));
				}
			}
			final Object totalCount = selectOne(productId, NAMESPACE + "getBlackManageListTotal", parameter);
			final List<BlackUsers> resultList = selectList(productId, NAMESPACE + "getBlackManageList", parameter);

			// 返回结果
			final PagingLoadResult<BlackUsers> result = new PagingLoadResult<BlackUsers>();
			result.setRows(resultList);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal((Integer) totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));

			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_GETBLACKMANAGELIST, "得到黑名单管理列表成功");
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_GETBLACKMANAGELIST, e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "ad-crl-insertBlackManage")
	public String insertBlackManage(String productId, Map<String, Object> paramMap) throws AccessDeniedException {
		String i = "-1";
		try {
			final Map<String, String> parameter = new HashMap<String, String>();
			parameter.put("type", paramMap.get("type").toString());
			if (paramMap.get("keyword").toString().length() != 40) {
				parameter.put("keyword", paramMap.get("keyword").toString().toLowerCase());
			} else if (paramMap.get("keyword").toString().contains("@")) {
				parameter.put("keyword", paramMap.get("keyword").toString().toLowerCase());
			} else {
				parameter.put("SHA1keyword", paramMap.get("keyword").toString());
			}

			final Integer totalCount = insert(productId, NAMESPACE + "insertBlackManage", paramMap);

			// 写入操作日志表
			if (totalCount > 0) {
				/*
				 * Map<String,Object> logMap = new HashMap<String, Object>();
				 * logMap.put("note", "创建媒体"); logMap.put("relId", adid);
				 * logMap.put("productId", productId); addLog(logMap);
				 */
			}
			if (totalCount != null) {
				i = String.valueOf(totalCount);
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_INSERTBLACKMANAGE, "增加黑名单成功");
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_INSERTBLACKMANAGE, e);
		}
		return i;
	}

	@Override
	@AccessResource(name = "ad-crl-delAccount")
	public Integer delAccount(String productId, String ids)throws AccessDeniedException {
		Integer result = 0;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("ids", ids);
			result = delete(productId, NAMESPACE + "delAccount", parameter);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_DELACCOUNT, "删除黑名单账号成功");
		} catch (Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_DELACCOUNT, e);
		}
		return result;
	
	}

}
