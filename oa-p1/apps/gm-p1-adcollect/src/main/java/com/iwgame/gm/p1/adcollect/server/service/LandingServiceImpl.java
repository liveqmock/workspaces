/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LandingServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.gm.p1.adcollect.modules.landing.shared.rpc.LandingService;
import com.iwgame.gm.p1.adcollect.server.OperatorLogger;
import com.iwgame.gm.p1.adcollect.shared.model.LandingDateBase;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xmvp.shared.Media;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * 类说明
 * 
 * @简述： 到达页
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-24 上午09:11:09
 */
@NeedAuthorization
public class LandingServiceImpl extends BaseService implements LandingService {

	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger
			.getLogger(LandingServiceImpl.class);
	private static final String ALP = "ad.landing.page.";

	private OperatorLogger businessLogger;

	@Autowired
	public void setBusinessLogger(final OperatorLogger businessLogger) {
		this.businessLogger = businessLogger;
	}

	private String resHost;

	public String getResHost() {
		return resHost;
	}

	public void setResHost(final String resHost) {
		this.resHost = resHost;
	}

	@Override
	@AccessResource(name = "ad-landing-getLandingList")
	public String getLandingList(final String productId,
			final BaseFilterPagingLoadConfig loadConfig) {
		String returnData = "";
		final Map<String, Object> parameter = new HashMap<String, Object>();
		// 分页参数
		parameter.put("limit", loadConfig.<Integer> get("limit"));
		parameter.put("offset", loadConfig.<Integer> get("offset"));
		parameter.put("sortField", loadConfig.<String> get("sortField"));
		parameter.put("sortDir", loadConfig.<String> get("sortDir"));

		final Integer total = selectOne(productId, ALP
				+ "getLandingPageListCount", parameter);
		final List<LandingDateBase> list = selectList(productId, ALP
				+ "getLandingPageList", parameter);

		for (final LandingDateBase landing : list) {
			final Media media = new Media();
			media.setName(landing.getMname());
			media.setUrl(resHost + landing.getMaterialUrl());
			media.setContentType(landing.getContentType());
			media.setHeight(landing.getHeight());
			media.setWidth(landing.getWidth());
			media.setSize(landing.getCapacity());
			landing.setMedia(media);
		}

		final PagingLoadResult<LandingDateBase> result = new PagingLoadResult<LandingDateBase>();
		result.setRows(list);
		result.setTotal(total);
		result.setLimit(loadConfig.<Integer> get("limit"));
		result.setOffset(loadConfig.<Integer> get("offset"));
		returnData = GridHelper.buildResults(result);
		return returnData;
	}

	@Override
	@AccessResource(name = "ad-landing-landing-add")
	public int addLanding(final String productId,
			final Map<String, Object> parameter) {
		Integer result = 0;
		try {
			result = insert(productId, ALP + "insertReachPage", parameter);
			final Integer id = selectOne(productId, ALP
					+ "getInsertReachPageId");
			final LandingDateBase dateBase = new LandingDateBase();
			dateBase.setId(id);
			businessLogger.writeCreateLog(productId, dateBase);

		} catch (final Exception e) {
			logger4j.error(e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-landing-landing-update")
	public int updateLanding(final String productId,
			final Map<String, Object> parameter,
			final Map<String, Object> lodBase) {
		Integer result = 0;
		try {
			final LandingDateBase oldDateBase = selectOne(productId, ALP
					+ "getReachPageById", parameter);
			result = update(productId, ALP + "updateReachPage", parameter);

			final LandingDateBase newDateBase = new LandingDateBase();
			newDateBase.setId(oldDateBase.getId());
			if (null != parameter.get("name")) {
				newDateBase.setName(parameter.get("name").toString());
			} else {
				newDateBase.setName(oldDateBase.getName());
			}
			if (null != parameter.get("url")) {
				newDateBase.setLink(parameter.get("url").toString());
			} else {
				newDateBase.setLink(oldDateBase.getLink());
			}
			if (null != parameter.get("materialId")) {
				newDateBase.setMaterialId(parameter.get("materialId")
						.toString());
			} else {
				newDateBase.setMaterialId(oldDateBase.getMaterialId());
			}
			if (null != parameter.get("status")) {
				newDateBase.setStatus((Integer) parameter.get("status"));
			} else {
				newDateBase.setStatus(oldDateBase.getStatus());
			}
			if (null != parameter.get("note")) {
				newDateBase.setNote(parameter.get("note").toString());
			} else {
				newDateBase.setNote(oldDateBase.getNote());
			}
			businessLogger.writeModifyLog(productId, oldDateBase, newDateBase);
		} catch (final Exception e) {
			logger4j.error(e);
		}
		return result;
	}

}
