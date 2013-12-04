/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： ExplainMgrServiceImpl.java
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

import com.iwgame.gm.p1.adcollect.modules.explain.shared.rpc.ExplainMgrService;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.DictionaryDataBase;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @Description: 说明服务
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-20 下午3:02:43
 */
@NeedAuthorization
public class ExplainMgrServiceImpl extends BaseService implements ExplainMgrService{

	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_EXPLAIN_MGR, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(ExplainMgrServiceImpl.class);

	private static final String AE = "ad.explain.";
	
	private String resHost;

	public String getResHost() {
		return resHost;
	}

	public void setResHost(final String resHost) {
		this.resHost = resHost;
	}
	
	@Override
	public String getDictionaryLIst(String productId, BaseFilterPagingLoadConfig loadConfig)
			throws AccessDeniedException {
		String returnData = "";
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));
			// 业务参数

			final Integer total = selectOne(productId, AE + "getDictionaryListCount", parameter);
			final List<DictionaryDataBase> list = selectList(productId, AE + "getDictionaryList", parameter);
			for (DictionaryDataBase base : list) {
				base.setPath(resHost + base.getPath());
			}
			final PagingLoadResult<DictionaryDataBase> result = new PagingLoadResult<DictionaryDataBase>();
			result.setRows(list);
			result.setTotal(total);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);

			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_DICTIONARY_LIST, "获取数据字典列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_DICTIONARY_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "ad-frame-manage-dictionary-del")
	public int delDictionaryByIds(String productId, String ids) throws AccessDeniedException {
		Integer result = 0;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("ids", ids);
			result = delete(productId, AE + "delDictionaryByIds", parameter);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_DICTIONARY_DEL, "删除数据字典成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_DICTIONARY_DEL, e);
			logger4j.equals(e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-frame-manage-dictionary-add")
	public void chenkUploadAuthority() throws AccessDeniedException {
		
	}

	@Override
	@AccessResource(name = "ad-frame-manage-dictionary-download")
	public void chenkDownLoadAuthority() throws AccessDeniedException {
		
	}
	
}

