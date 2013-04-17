/****************************************************************
 *  系统名称  ： '[killer]'
 *  文件名    ： LogsServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.server.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iwgame.account.kill.modules.logs.shared.model.AutoKillLog;
import com.iwgame.account.kill.modules.logs.shared.rpc.LogsService;
import com.iwgame.account.kill.server.common.BaseService;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-11 下午06:12:55
 */
@NeedAuthorization
public class LogsServiceImpl extends BaseService implements LogsService, ExportQuery {

	private static final Logger logger4j = Logger.getLogger(LogsServiceImpl.class);

	@Override
	@AccessResource(name = "kill-mean-logs-querylist")
	public String loadLogsList(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
		String returnData = "";
		try {
			Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));

			// 业务参数
			parameter.put("killDays", loadConfig.<Integer> get("days"));
			parameter.put("policyTitle", loadConfig.<String> get("policy"));
			parameter.put("accountName", loadConfig.<String> get("username"));
			parameter.put("startTime", loadConfig.<Date> get("startTime"));
			parameter.put("endTime", loadConfig.<Date> get("endTime"));

			Integer total = selectOne(productId, "kill.logs.getAutoLogsCount", parameter);
			List<AutoKillLog> list = selectList(productId, "kill.logs.getAutoLogs", parameter);
			PagingLoadResult<AutoKillLog> result = new PagingLoadResult<AutoKillLog>();
			result.setRows(list);
			result.setTotal(total);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);

		} catch (Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "kill-mean-logs-query-export")
	public List<BaseModelData> query(final Map<String, String> param) {
		List<BaseModelData> datas = null;
		try {
			Map<String, Object> parameter = new HashMap<String, Object>();
			// 业务参数
			parameter.put("killDays", param.get("days"));
			parameter.put("policyTitle", param.get("policy"));
			parameter.put("accountName", param.get("username"));
			parameter.put("startTime", param.get("startTime"));
			parameter.put("endTime", param.get("endTime"));
			String productId = param.get("productId");

			List<AutoKillLog> list = selectList(productId, "kill.logs.getAutoLogs", parameter);
			if (list.size() > 0) {
				datas = new ArrayList<BaseModelData>();
				// 时间格式转换
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// sdf.format(grj.getJoinTime())
				for (AutoKillLog akl : list) {
					BaseModelData data = new BaseModelData();
					data.set("id", akl.getId() + "");
					data.set("optType", akl.getOptType() + "");
					data.set("policyTitle", akl.getPolicyTitle() + "");
					data.set("policyContent", akl.getPolicyContent() + "");
					data.set("accountid", akl.getAccountid() + "");
					data.set("accountName", akl.getAccountName() + "");

					if (akl.getKillTime() != null) {
						data.set("killTime", sdf.format(akl.getKillTime()) + "");
					} else {
						data.set("killTime", "");
					}

					data.set("remark", akl.getRemark() + "");
					data.set("objectId", akl.getObjectId() + "");
					data.set("object", akl.getObject() + "");
					data.set("killDays", akl.getKillDays() + "");
					data.set("operator", akl.getOperator() + "");

					datas.add(data);
				}
			}

		} catch (Exception e) {
			logger4j.error(e.getMessage(), e);
		}
		return datas;
	}

}
