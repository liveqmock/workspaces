/**      
 * KeysServiceImpl.java Create on 2012-8-20     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.iwgame.gm.p1.adcollect.modules.keys.shared.rpc.KeysService;
import com.iwgame.gm.p1.adcollect.server.OperatorLogger;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.gm.p1.adcollect.shared.model.Media;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @ClassName: KeysServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-8-20 上午10:05:32
 * @Version 1.0
 * 
 */
@NeedAuthorization
public class KeysServiceImpl extends BaseService implements KeysService {
	private static Logger logger = Logger.getLogger(
			LoggerConstants.APPUNITNAME_KEYS, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger
			.getLogger(KeysServiceImpl.class);
	private static final String NAMESPACE = "adcollect.keys.";

	private OperatorLogger businessLogger;

	@Autowired
	public void setBusinessLogger(final OperatorLogger businessLogger) {
		this.businessLogger = businessLogger;
	}

	// private String productId = ApplicationContext.getCurrentProductId();
	@Override
	@AccessResource(name = "ad-keys-queryAdInfo")
	public String queryAdInfo(final BaseFilterPagingLoadConfig loadConfig)
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
			parameter.put("media", loadConfig.<String> get("media"));
			parameter.put("adid", loadConfig.<String> get("adid"));
			parameter.put("keyword", loadConfig.<String> get("keyword"));
			final String level = loadConfig.<String> get("level");
			String type = "";
			if (loadConfig.<ArrayList> get("type") != null) {
				type += "(";
				for (int i = 0; i < loadConfig.<ArrayList> get("type").size(); i++) {
					type += "'" + loadConfig.<ArrayList> get("type").get(i)
							+ "',";
				}
				type = type.substring(0, type.length() - 1) + ")";
			}
			parameter.put("type", type);

			String mark = "";
			if (loadConfig.<ArrayList> get("mark") != null) {
				mark += "(";
				for (int i = 0; i < loadConfig.<ArrayList> get("mark").size(); i++) {
					mark += "'" + loadConfig.<ArrayList> get("mark").get(i)
							+ "',";
				}
				mark = mark.substring(0, mark.length() - 1) + ")";
			}
			parameter.put("mark", mark);

			final String productId = loadConfig.<String> get("productId");
			// 查询类型1= 按关键字 ，0=按广告ID
			final String queryType = loadConfig.<String> get("queryType");

			Object totalCount = null;
			List<AdvertisementInfo> resultList = null;
			if (("1").equals(queryType)) {
				// level=0精确,level=1 模糊
				if (("0").equals(level)) {

					totalCount = selectOne(productId,
							NAMESPACE + "getTotalAcc", parameter);
					resultList = selectList(productId,
							NAMESPACE + "getRaceAcc", parameter);
				} else {
					totalCount = selectOne(productId, NAMESPACE + "getTotal",
							parameter);
					resultList = selectList(productId, NAMESPACE + "getRace",
							parameter);
				}

			} else {
				totalCount = selectOne(productId, NAMESPACE + "getTotalById",
						parameter);
				resultList = selectList(productId, NAMESPACE + "getRaceById",
						parameter);
			}

			// 返回结果
			final PagingLoadResult<AdvertisementInfo> result = new PagingLoadResult<AdvertisementInfo>();
			result.setRows(resultList);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal((Integer) totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));

			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_QUERYADINFO,
					"加载广告列表成功");
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_QUERYADINFO, e);
		}
		return returnData;

	}

	@Override
	public List<Media> getMedia(final String productId)
			throws AccessDeniedException {
		List<Media> mediaList = new ArrayList<Media>();
		try {
			// 构建参数&查询
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 按条件进行查询
			final List<Media> resultList = selectList(productId, NAMESPACE
					+ "getMedia", parameter);
			mediaList = resultList;
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_GETMEDIA,
					"得到媒体列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_GETMEDIA, e);
			logger4j.error(e);
		}
		return mediaList;
	}

	@Override
	@AccessResource(name = "ad-keys-insertAdInfo")
	public Integer insertAdInfo(final Map<String, Object> paramMap)
			throws AccessDeniedException {
		Integer i = 0;
		String adid = "";

		try {
			final String productId = String.valueOf(paramMap.get("productId"));
			String link = String.valueOf(paramMap.get("link"));
			// 先取得最大的广告id
			final int maxId = (Integer) selectOne(productId, NAMESPACE
					+ "getAdid", paramMap);
			if (String.valueOf(maxId).length() > 6) {
				adid = String.valueOf(maxId + 1);
			} else {
				adid = paramMap.get("media") + String.valueOf(maxId + 1);
			}
			link = link + "?stat=" + adid;
			paramMap.put("adid", adid);
			paramMap.put("link", link);

			final Integer totalCount = insert(productId, NAMESPACE
					+ "insertAdInfo", paramMap);

			i = totalCount;
			// 创建媒体
			if (totalCount > 0) {
				final AdvertisementInfo info = new AdvertisementInfo();
				info.setId(adid);
				businessLogger.writeCreateLog(productId, info);
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_INSERTADINFO,
					"增加广告信息成功");
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_INSERTADINFO, e);
		}
		return i;
	}

	@Override
	@AccessResource(name = "ad-keys-updateAdInfo")
	public Integer updateAdInfo(final Map<String, Object> paramMap)
			throws AccessDeniedException {
		Integer i = 0;

		// try {

		final String productId = String.valueOf(paramMap.get("productId"));
		final String adid = (String) paramMap.get("adid");

		// 查询原来的对象值
		final AdvertisementInfo oldAdvertisementInfo = selectOne(productId,
				NAMESPACE + "getInitInfo", paramMap);

		// 将可能修改的对象的字段进行更新
		final AdvertisementInfo newAdvertisementInfo = new AdvertisementInfo();
		if (paramMap.get("type") != null) {
			newAdvertisementInfo.setType((String) paramMap.get("type"));
		} else {
			newAdvertisementInfo.setType(oldAdvertisementInfo.getType());
		}
		if ((paramMap.get("link") != null) && !"".equals(paramMap.get("link"))) {
			newAdvertisementInfo.setUrl((String) paramMap.get("link"));
		} else {
			newAdvertisementInfo.setUrl(oldAdvertisementInfo.getUrl());
		}
		if (paramMap.get("mark") != null) {
			newAdvertisementInfo.setMark((String) paramMap.get("mark"));
		} else {
			newAdvertisementInfo.setMark(oldAdvertisementInfo.getMark());
		}
		if (paramMap.get("status") != null) {
			newAdvertisementInfo.setStatus((String) paramMap.get("status"));
		} else {
			newAdvertisementInfo.setStatus(oldAdvertisementInfo.getStatus());
		}
		newAdvertisementInfo.setId(adid);
		// 更新对象
		i = update(productId, NAMESPACE + "updateAdInfo", newAdvertisementInfo);
		if (i > 0) {
			// 写更新日志
			businessLogger.writeModifyLog(productId, oldAdvertisementInfo,
					newAdvertisementInfo);
		}
		return i;
	}

	@Override
	@AccessResource(name = "ad-keys-getLog")
	public String getLog(final BaseFilterPagingLoadConfig loadConfig)
			throws AccessDeniedException {

		loadConfig.set("relId", loadConfig.<String> get("adid"));
		loadConfig.set("types", "KEYS");

		return businessLogger.getLogs(loadConfig);
		
	}
}
