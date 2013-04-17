/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： AdminMgrServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.server.OperatorLogger;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.shared.model.AdGroupDataBase;
import com.iwgame.gm.p1.adcollect.shared.model.ContractDataBase;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.LogosDataBasee;
import com.iwgame.gm.p1.adcollect.shared.model.MaterialDateBase;
import com.iwgame.gm.p1.adcollect.shared.model.MeidaDataBase;
import com.iwgame.gm.p1.adcollect.shared.model.NetbarClientDataBase;
import com.iwgame.gm.p1.adcollect.shared.model.NetbarIpDataBase;
import com.iwgame.gm.p1.adcollect.shared.model.PayableDataBase;
import com.iwgame.gm.p1.adcollect.shared.model.PositionDateBase;
import com.iwgame.gm.p1.adcollect.shared.model.SheduleDataBase;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xmvp.shared.Media;
import com.iwgame.xportal.common.server.dao.ProductDao;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;
import com.iwgame.xportal.common.shared.model.Product;

/**
 * 类说明
 * 
 * @简述： 硬广
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-24 下午05:24:13
 */
@NeedAuthorization
public class AdminMgrServiceImpl extends BaseService implements AdminMgrService {
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_ADMIN_MGR, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(AdminMgrServiceImpl.class);

	private ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	private OperatorLogger businessLogger;

	@Autowired
	public void setBusinessLogger(final OperatorLogger businessLogger) {
		this.businessLogger = businessLogger;
	}

	private static final String AHA = "ad.hard.admin.";
	private static final String ALP = "ad.landing.page.";

	private String resHost;

	public String getResHost() {
		return resHost;
	}

	public void setResHost(final String resHost) {
		this.resHost = resHost;
	}

	@Override
	public List<DropDownListData> getType(final String productId, final int type) {
		List<DropDownListData> list = null;
		try {
			list = selectList(productId, AHA + "getTypeBySort", type);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_TYPE, "获取类型分类成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_TYPE, e);
			logger4j.error(e);
		}
		return list;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-getmateriallist")
	public String getMaterialList(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
		String returnData = "";
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));
			// 业务参数
			parameter.put("type", loadConfig.<Integer> get("type"));
			parameter.put("id", loadConfig.<String> get("id"));
			parameter.put("name", loadConfig.<String> get("name"));
			parameter.put("beganTime", loadConfig.<Date> get("startTime"));
			parameter.put("endTime", loadConfig.<Date> get("endTime"));

			final Integer total = selectOne(productId, AHA + "getMaterialListCount", parameter);
			final List<MaterialDateBase> list = selectList(productId, AHA + "getMaterialList", parameter);
			for (final MaterialDateBase materia : list) {
				final Media media = new Media();
				media.setName(materia.getName());
				media.setUrl(resHost + materia.getUrl());
				media.setContentType(materia.getContentType());
				media.setHeight(materia.getHeight());
				media.setWidth(materia.getWidth());
				media.setSize(materia.getCapacity());
				materia.setMedia(media);
			}
			final PagingLoadResult<MaterialDateBase> result = new PagingLoadResult<MaterialDateBase>();
			result.setRows(list);
			result.setTotal(total);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);

			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_MATERIAL_LIST, "获取素材列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_MATERIAL_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-getmedialist")
	public String getMediaList(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
		String returnData = "";
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));
			// 业务参数

			parameter.put("type", loadConfig.<Integer> get("type"));
			parameter.put("sort", loadConfig.<Integer> get("sort"));
			parameter.put("name", loadConfig.<String> get("name"));
			parameter.put("id", loadConfig.<String> get("id"));

			final Integer total = selectOne(productId, AHA + "getMediaListCount", parameter);
			final List<MeidaDataBase> list = selectList(productId, AHA + "getMediaList", parameter);

			final PagingLoadResult<MeidaDataBase> result = new PagingLoadResult<MeidaDataBase>();
			result.setRows(list);
			result.setTotal(total);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_MEDIA_LIST, "获取媒体列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_MEDIA_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-media-add")
	public int addMedia(final String productId, final Map<String, Object> parameter) throws AccessDeniedException {
		Integer result = 0;

		try {
			final Integer typeId = (Integer) parameter.get("mediaType");
			final String typeIdStr = StringUtils.leftPad(Integer.toString(typeId), 2, '0');
			// if ((typeId < 10) && (typeId > -1)) {
			// typeIdStr = "0" + typeId;
			// } else {
			// typeIdStr = typeIdStr + typeId;
			// }
			final Map<String, Object> parameter1 = new HashMap<String, Object>();
			parameter1.put("typeIdStr", typeIdStr);
			final Integer priorId = selectOne(productId, AHA + "getMediaMaxId", parameter1);
			String stringId = "";
			if (priorId != null) {
				stringId = StringUtils.leftPad(Integer.toString(priorId + 1), 4, '0');
			} else {
				stringId = typeIdStr + "01";
			}
			parameter.put("id", stringId);

			// 获取所有产品ID方法
			List<String> list = getProducts();
			for (String product : list) {
				result = insert(product, AHA + "insertMedia", parameter);
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_MEDIA, "产品" + product + "成功");
				final MeidaDataBase base = new MeidaDataBase();
				base.setId(stringId);
				businessLogger.writeCreateLog(productId, base);
			}

			// result = insert(productId, AHA + "insertMedia", parameter);
			// businessLogger

			// final MeidaDataBase base = new MeidaDataBase();
			// base.setId(stringId);
			// businessLogger.writeCreateLog(productId, base);

		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_MEDIA, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-media-update")
	public int updateMedia(final String productId, final Map<String, Object> parameter,
			final Map<String, Object> oldBase) {
		Integer result = 0;
		try {
			final MeidaDataBase oldDataBase = selectOne(productId, AHA + "getMediaById", parameter);
			List<String> list = getProducts();
			for (String product : list) {
				result = update(product, AHA + "updateMediaById", parameter);
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_MEDIA, "产品" + product + "成功");
				final MeidaDataBase newDataBase = new MeidaDataBase();
				newDataBase.setSortStr(parameter.get("mediaSortStr").toString());
				if (null != parameter.get("subclass")) {
					newDataBase.setSubclass(parameter.get("subclass").toString());
				} else {
					newDataBase.setSubclass(oldDataBase.getSubclass());
				}
				if (null != parameter.get("adds")) {
					newDataBase.setAdds(parameter.get("adds").toString());
				} else {
					newDataBase.setAdds(oldDataBase.getAdds());
				}
				if (null != parameter.get("data1")) {
					newDataBase.setData1((Integer) parameter.get("data1"));
				} else {
					newDataBase.setData1(oldDataBase.getData1());
				}
				if (null != parameter.get("data2")) {
					newDataBase.setData2((Integer) parameter.get("data2"));
				} else {
					newDataBase.setData2(oldDataBase.getData2());
				}
				if (null != parameter.get("data3")) {
					newDataBase.setData3((Integer) parameter.get("data3"));
				} else {
					newDataBase.setData3(oldDataBase.getData3());
				}
				if (null != parameter.get("data4")) {
					newDataBase.setData4((Integer) parameter.get("data4"));
				} else {
					newDataBase.setData4(oldDataBase.getData4());
				}
				newDataBase.setId(oldDataBase.getId());
				businessLogger.writeModifyLog(product, oldDataBase, newDataBase);
			}
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_MEDIA, e);
		}
		return result;
	}

	@Override
	public List<DropDownListData> getMedia(final String productId, final int type) {
		List<DropDownListData> list = null;
		try {
			list = selectList(productId, AHA + "getMediatIdAndName", type);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_MEDIA, "获取硬广媒体列表成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_MEDIA, e);
			logger4j.error(e);
		}
		return list;
	}

	@Override
	public boolean checkGroupName(final String productId, final String name) {
		boolean result = true;
		try {
			final Integer count = selectOne(productId, AHA + "checkGroupName", name);
			if (count > 0) {
				result = false;
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_GROUP_NAME, "检查组名成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_GROUP_NAME, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-group-add")
	public int addGroup(final String productId, final String name, final String mediaId) {
		Integer result = 0;
		try {
			final AdGroupDataBase base = new AdGroupDataBase();
			base.setName(name);
			base.setMediaid(mediaId);
			result = insert(productId, AHA + "addGroup", base);
			businessLogger.writeCreateLog(productId, base);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_ADD_GROUP, "添加广告组成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_ADD_GROUP, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	public String getGroupList(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
		String returnData = "";
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<String> get("limit"));
			parameter.put("offset", loadConfig.<String> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));
			// 业务参数
			String mediaId = " ";
			String name = " ";
			String id = " ";
			mediaId = loadConfig.<String> get("media");
			name = loadConfig.<String> get("name");
			id = loadConfig.<String> get("id");
			String type = "all";
			if (name.trim().length() > 0) {
				type = "name";
			}
			if (id.trim().length() > 0) {
				type = "id";
			}
			parameter.put("mediaId", mediaId);
			parameter.put("name", name);
			parameter.put("id", id);
			parameter.put("type", type);

			String mediaType = loadConfig.<String> get("mediaType");
			if (null != mediaType) {
				mediaType = StringUtils.leftPad(mediaType, 2, '0');
				parameter.put("mediaType", mediaType);
			} else {
				parameter.put("mediaType", null);
			}

			final Integer totalCount = selectOne(productId, AHA + "getGroupListCount", parameter);
			final List<AdGroupDataBase> list = selectList(productId, AHA + "getGroupList", parameter);
			// 返回结果
			final PagingLoadResult<AdGroupDataBase> result = new PagingLoadResult<AdGroupDataBase>();
			result.setRows(list);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal(totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_GROUP_LIST, "获取广告组列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_GROUP_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-getPositionList")
	public String getPositionList(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
		String returnData = "";
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));
			// 业务参数

			parameter.put("key", loadConfig.<String> get("key"));
			parameter.put("id", loadConfig.<String> get("id"));
			parameter.put("name", loadConfig.<String> get("name"));
			parameter.put("adId", loadConfig.<String> get("adId"));
			parameter.put("media", loadConfig.<String> get("media"));
			parameter.put("units", loadConfig.<Integer> get("units"));

			String mediaType = loadConfig.<String> get("mediaType");
			if (null != mediaType) {
				mediaType = StringUtils.leftPad(mediaType, 2, '0');
				parameter.put("mediaType", mediaType);
			} else {
				parameter.put("mediaType", null);
			}

			final Integer total = selectOne(productId, AHA + "getPositionListCount", parameter);
			final List<PositionDateBase> list = selectList(productId, AHA + "getPositionList", parameter);

			final PagingLoadResult<PositionDateBase> result = new PagingLoadResult<PositionDateBase>();

			for (final PositionDateBase base : list) {
				final Media media = new Media();
				media.setName(base.getName());
				media.setUrl(resHost + base.getLegend());
				media.setContentType(base.getContentType());
				media.setHeight(base.getHeight());
				media.setWidth(base.getWidth());
				media.setSize(base.getCapacity());
				base.setMedia(media);
			}
			result.setRows(list);
			result.setTotal(total);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_POS_LIST, "获取广告位列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_POS_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	public String getLog(final BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException {
		return businessLogger.getLogs(loadConfig);
	}

	@Override
	public Boolean checkPositionName(final String productId, final String name) {
		boolean result = true;
		try {
			final Integer count = selectOne(productId, AHA + "checkPositionName", name);
			if (count > 0) {
				result = false;
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_POS_NAME, "检查广告位名成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_POS_NAME, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	public Integer addPayable(final String productId, final String title) {
		Integer result = 0;
		try {
			List<String> list = getProducts();
			for (String product : list) {
				result = insert(product, AHA + "addPayable", title);
				if (result != 1) {
					return result;
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_CONTRACT_PAYABLE, "产品" + product
						+ "添加合同抬头成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_CONTRACT_PAYABLE, e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-getPayableList")
	public String getPayableList(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
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

			Object totalCount = null;
			List<PayableDataBase> resultList = null;

			totalCount = selectOne(productId, AHA + "getPayableListCount", parameter);
			resultList = selectList(productId, AHA + "getPayableList", parameter);

			// 返回结果
			final PagingLoadResult<PayableDataBase> result = new PagingLoadResult<PayableDataBase>();
			result.setRows(resultList);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal((Integer) totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));

			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_PAYABLE_LIST, "获取合同抬头列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_PAYABLE_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	public Integer updatePayable(final String productId, final int id, final String title) {
		Integer result = 0;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("title", title);
			parameter.put("id", id);
			List<String> list = getProducts();
			for (String product : list) {
				result = update(product, AHA + "updatePayable", parameter);
				if (result != 1) {
					return result;
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_CONTRACT_PAYABLE, "产品" + product
						+ "修改合同抬头成功");
			}
		} catch (Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_UPDATE_CONTRACT_PAYABLE, e);
		}
		return result;
	}

	@Override
	public Integer deletePayable(final String productId, final int id) {
		Integer result = 0;
		try {
			List<String> list = getProducts();
			for (String product : list) {
				result = delete(product, AHA + "deletePayable", id);
				if (result != 1) {
					return result;
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_DEL_CONTRACT_PAYABLE, "产品" + product
						+ "删除合同抬头成功");
			}
		} catch (Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_DEL_CONTRACT_PAYABLE, e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-getLogosList")
	public String getLogosList(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
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

			final Integer totalCount = selectOne(productId, AHA + "getLogosListCount", parameter);
			final List<LogosDataBasee> list = selectList(productId, AHA + "getLogosList", parameter);
			for (final LogosDataBasee base : list) {
				final Media media = new Media();
				media.setName(base.getName());
				media.setUrl(resHost + base.getPath());
				media.setContentType(base.getContentType());
				media.setHeight(base.getHeight());
				media.setWidth(base.getWidth());
				media.setSize(base.getCapacity());
				base.setMedia(media);
			}
			// 返回结果
			final PagingLoadResult<LogosDataBasee> result = new PagingLoadResult<LogosDataBasee>();
			result.setRows(list);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal(totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));

			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_LOGO_LIST, "获取合同logo列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_LOGO_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	public int deleteLogos(final String productId, final int id) {
		// TODO(删除 Logo.........................................)
		Integer result = 0;
		try {
			List<String> list = getProducts();
			for (String product : list) {
				result = delete(product, AHA + "deleteLogos", id);
				if (result != 1) {
					return result;
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_DEL_CONTRACT_LOGO, "产品" + product
						+ "删除合同Logo成功");
			}
		} catch (Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_DEL_CONTRACT_LOGO, e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-getContractList")
	public String getContractList(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
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
			parameter.put("itemno", loadConfig.<String> get("itemno"));
			parameter.put("name", loadConfig.<String> get("name"));
			parameter.put("applyman", loadConfig.<String> get("applyman"));
			parameter.put("totalMin", loadConfig.<Integer> get("totalMin"));
			parameter.put("totalMax", loadConfig.<Integer> get("totalMax"));
			parameter.put("media", loadConfig.<Integer> get("media"));
			parameter.put("time", loadConfig.<Date> get("time"));
			parameter.put("endTime", loadConfig.<Date> get("endTime"));
			parameter.put("payTime", loadConfig.<Date> get("payTime"));
			parameter.put("endPayTime", loadConfig.<Date> get("endPayTime"));

			final Integer totalCount = selectOne(productId, AHA + "getContractListCount", parameter);
			final List<ContractDataBase> list = selectList(productId, AHA + "getContractList", parameter);
			for (final ContractDataBase c : list) {
				if ((null != c.getPath()) && (c.getPath().length() > 0)) {
					c.setPath(resHost + c.getPath());
				} else {
					c.setPath(null);
				}
			}
			// 返回结果
			final PagingLoadResult<ContractDataBase> result = new PagingLoadResult<ContractDataBase>();
			result.setRows(list);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal(totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));

			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_CONTRACT_LIST, "获取合同列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_CONTRACT_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	public List<DropDownListData> getLogoList(final String productId) {
		List<DropDownListData> list = null;
		try {

			list = selectList(productId, AHA + "getLOGOSListDropDown");
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_LOGO, "获取LOGO成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_LOGO, e);
			logger4j.error(e);
		}
		return list;
	}

	@Override
	public List<DropDownListData> getPayableList(final String productId) {
		List<DropDownListData> list = null;
		try {
			list = selectList(productId, AHA + "getPayableListDropDown");
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_PAYABLE, "获取抬头成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_PAYABLE, e);
			logger4j.error(e);
		}
		return list;
	}

	@Override
	public Boolean checkItemno(final String productId, final String itemno) {
		boolean result = false;
		try {
			final Integer itemnoCountById = selectOne(productId, AHA + "itemnoCountById", itemno);// itemnoCountById
			if (itemnoCountById == 0) {
				result = true;
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_ITEMNO, "验证合同编号成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_ITEMNO, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	public List<DropDownListData> autoMaterialId(final String productId, final String query) {
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("query", query);
			return selectList(productId, AHA + "getMaterialIdAndName", parameter);
		} catch (Exception e) {
			logger4j.error(e);
		}
		return null;
	}

	@Override
	public List<DropDownListData> autoContractIdAndName(final String productId, final String query) {
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("query", query);
			return selectList(productId, AHA + "autoContractIdAndName", parameter);
		} catch (Exception e) {
			logger4j.error(e);
		}
		return null;
	}

	@Override
	public List<DropDownListData> autoGroupIdAndName(final String productId, final String query) {
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("query", query);
			return selectList(productId, AHA + "autoGroupIdAndName", parameter);
		} catch (Exception e) {
			logger4j.error(e);
		}
		return null;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-add-shedule")
	public int addShedule(final String productId, final SheduleDataBase newDateBase) {
		Integer result = 0;
		try {
			//  广告链接 后面加上参数  广告id
			newDateBase.setAdUrl(newDateBase.getAdUrl()+"?stat="+newDateBase.getAdId());
			result = insert(productId, AHA + "addShedule", newDateBase);
			// 调用修改 补全 排期码
			String posid = newDateBase.getPosId();
			String key = (String) selectOne(productId, AHA + "getPositionKeyById", posid);
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("idNew", newDateBase.getId() + key);
			parameter.put("idOld", newDateBase.getId());
			update(productId, AHA + "updateSheduleId", parameter);
			newDateBase.setId(parameter.get("idNew")+"");
			// 写日志  
			businessLogger.writeCreateLog(productId, newDateBase);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_ADD_SHEDULE, "添加广告排期成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_ADD_SHEDULE, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-getSheduleList")
	public String getSheduleList(final String productId, final BaseFilterPagingLoadConfig loadConfig) {
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

			// 跳转默认参数
			parameter.put("sid", loadConfig.<String> get("sid"));

			parameter.put("mediaId", loadConfig.<String> get("mediaId"));
			parameter.put("positionId", loadConfig.<String> get("positionId"));
			parameter.put("contractId", loadConfig.<String> get("contractId"));
			parameter.put("groupId", loadConfig.<String> get("groupId"));
			parameter.put("groupName", loadConfig.<String> get("groupName"));
			parameter.put("adId", loadConfig.<String> get("adId"));
			parameter.put("adName", loadConfig.<String> get("adName"));
			parameter.put("beganTime", loadConfig.<Date> get("startTime"));
			parameter.put("endTime", loadConfig.<Date> get("endTime"));

			String mediaType = loadConfig.<String> get("mediaType");
			if (null != mediaType) {
				mediaType = StringUtils.leftPad(mediaType, 2, '0');
				parameter.put("mediaType", mediaType);
			} else {
				parameter.put("mediaType", null);
			}

			final Integer totalCount = selectOne(productId, AHA + "getSheduleListCount", parameter);
			final List<SheduleDataBase> list = selectList(productId, AHA + "getSheduleList", parameter);
			// 返回结果
			final PagingLoadResult<SheduleDataBase> result = new PagingLoadResult<SheduleDataBase>();
			result.setRows(list);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal(totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_SHEDULE_LIST, "获取广告排期列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_SHEDULE_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-update-shedule")
	public int updateShedule(final String productId, final SheduleDataBase newDateBase,
			final SheduleDataBase oldDateBase) {
		Integer result = 0;
		try {
			String adurl = newDateBase.getAdUrl();
			int index =  adurl.indexOf("?stat=");
			if(index==-1){
				newDateBase.setAdUrl(newDateBase.getAdUrl()+"?stat="+newDateBase.getAdId());
			}
			//  广告链接 后面加上参数  广告id
			result = update(productId, AHA + "updateShedule", newDateBase);
			oldDateBase.setId(newDateBase.getId());
			businessLogger.writeModifyLog(productId, oldDateBase, newDateBase);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_UPDATE_SHEDULE, "修改广告排期成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_UPDATE_SHEDULE, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	public boolean checkSheduleId(final String productId, final String id) {
		boolean result = false;
		try {
			Integer count = 0;
			count = selectOne(productId, AHA + "checkSheduleId", id);
			if (count.intValue() < 1) {
				result = true;
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_SHEDULEID, "验证广告排期成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_SHEDULEID, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	public String checkMaterialId(final String productId, final String id) {
		String result = null;
		try {
			Integer count = selectOne(productId, AHA + "getCheckMaterialId", id);
			if (count.intValue() != 1) {
				result = "不存在次素材";
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_MATERIALID, "验证素材成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_MATERIALID, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	public String checkGroupId(final String productId, final String id) {
		String result = null;
		try {
			Integer count = selectOne(productId, AHA + "getCheckGroupId", id);
			if (count.intValue() != 1) {
				result = "不存在此广告组";
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_GROUPID, "验证广告组成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_GROUPID, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	public String checkContractId(final String productId, final String id) {
		String result = null;
		try {
			Integer count = selectOne(productId, AHA + "getCheckContractId", id);
			if (count.intValue() != 1) {
				result = "不存在此合同";
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_CONTRACTID, "验证合同成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_CONTRACTID, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	public List<DropDownListData> getPositionListDorp(final String productId, final String mediaId) {
		List<DropDownListData> list = null;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("mediaId", mediaId);
			list = selectList(productId, AHA + "getPositionListDorp", parameter);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_POS_DORP, "获取广告位下拉列表成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_POS_DORP, e);
			logger4j.error(e);
		}
		return list;
	}

	@Override
	public List<DropDownListData> getAutoAdUrlListDorp(final String productId, final String query) {
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("query", query);
			return selectList(productId, ALP + "getAutoAdUrlListDorp", parameter);
		} catch (Exception e) {
			logger4j.error(e);
		}
		return null;
	}

	@Override
	public String checkAdUrl(final String productId, final String id) {
		String result = null;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("url", id);
			Integer count = selectOne(productId, ALP + "checkAdUrl", parameter);
			if (count.intValue() != 1) {
				result = "不存在此广告连接";
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_AD_URL, "验证广告url成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_CHECK_AD_URL, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-hard-admin-add-pos")
	public void chenkAddPosAuthority() throws AccessDeniedException {
		// (验证权限)
	}

	@Override
	@AccessResource(name = "ad-hard-admin-update-pos")
	public void chenkUpdatePosAuthority() throws AccessDeniedException {
		// (验证权限)
	}

	@Override
	@AccessResource(name = "ad-hard-admin-material-add")
	public void chenkAddMaterialAuthority() throws AccessDeniedException {
		// (验证权限)
	}

	@Override
	@AccessResource(name = "ad-hard-admin-contract-add")
	public void chenkAddContractAuthority() throws AccessDeniedException {
		// (验证权限)
	}

	@Override
	@AccessResource(name = "ad-hard-admin-contract-update")
	public void chenkUpdateContractAuthority() throws AccessDeniedException {
		// (验证权限)
	}

	@Override
	public String getPosNameById(String productId, String id) throws AccessDeniedException {
		String result = null;
		try {
			result = selectOne(productId, AHA + "getPosNameById", id);
		} catch (Exception e) {
			logger4j.error(e);
		}
		return result;
	}

	/**
	 * 获取所有产品ID方法
	 * 
	 * @return
	 */
	protected List<String> getProducts() {
		List<Product> product = productDao.getProducts();
		List<String> list = new ArrayList<String>();
		for (Product p : product) {
			list.add(p.getName());
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService
	 * #getNetbarIpList(java.lang.String,
	 * com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig)
	 */
	@Override
	public String getNetbarIpList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException {
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
			parameter.put("mediaId", loadConfig.<String> get("mediaId"));
			parameter.put("ipArea", loadConfig.<String> get("ipArea"));
			parameter.put("ip", loadConfig.<String> get("ip"));

			final Integer totalCount = selectOne(productId, AHA + "getNetbarIpListCount", parameter);
			final List<NetbarIpDataBase> list = selectList(productId, AHA + "getNetbarIpList", parameter);
			// 返回结果
			final PagingLoadResult<NetbarIpDataBase> result = new PagingLoadResult<NetbarIpDataBase>();
			result.setRows(list);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal(totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_NETBAR_IP_LIST, "获取网吧对应ip列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_NETBAR_IP_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService
	 * #addNetbarIp(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@AccessResource(name = "ad-hard-admin-add-netbarip")
	public int addNetbarIp(String productId, String mediaId, String ip) {
		Integer result = 0;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("mediaId", mediaId);
			parameter.put("ip", ip);
			List<String> list = getProducts();
			for (String product : list) {
				result = insert(product, AHA + "addNetbarIp", parameter);
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_NETBAR_IP, "产品" + product
						+ "添加媒体对应ip成功");
			}
		} catch (Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_ADD_NETBAR_IP, e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService
	 * #delNetbarIps(java.lang.String, java.lang.String)
	 */
	@Override
	@AccessResource(name = "ad-hard-admin-del-netbarip")
	public int delNetbarIps(String productId, String ids) {
		Integer result = 0;
		try {
			List<String> list = getProducts();
			for (String product : list) {
				final Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("ids", ids);
				result = delete(product, AHA + "delNetbarIps", parameter);
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_ADMIN_MGR_DEL_NETBAR_IP, "产品" + product
						+ "删除媒体对应ip成功");
			}
		} catch (Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_ADMIN_MGR_DEL_NETBAR_IP, e);
		}
		return result;
	}

	@Override
	public List<DropDownListData> getContactListDorp(String productId, String mediaId) throws AccessDeniedException {
		List<DropDownListData> list = null;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("mediaId", mediaId);
			list = selectList(productId, AHA + "getContactListDorp", parameter);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_CONTACT_LIST_DORP, "合同编号下拉列表成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_CONTACT_LIST_DORP, e);
			logger4j.error(e);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService
	 * #getNetbarClientList(java.lang.String,
	 * com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig)
	 */
	@Override
	public String getNetbarClientList(String productId, BaseFilterPagingLoadConfig loadConfig)
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
			parameter.put("mediaId", loadConfig.<String> get("mediaId"));
			parameter.put("version", loadConfig.<String> get("version"));
			parameter.put("code", loadConfig.<String> get("code"));

			final Integer totalCount = selectOne(productId, AHA + "getNetbarClientListCount", parameter);
			final List<NetbarClientDataBase> list = selectList(productId, AHA + "getNetbarClientList", parameter);
			// 返回结果
			final PagingLoadResult<NetbarClientDataBase> result = new PagingLoadResult<NetbarClientDataBase>();
			result.setRows(list);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setTotal(totalCount);
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_NETBAR_CLIENT_LIST, "获取客户端识别码列表成功");
		} catch (final Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_NETBAR_CLIENT_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService
	 * #addClent(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@AccessResource(name = "ad-hard-admin-add-netbar-clent")
	public int addClent(String productId, String mediaId, String version, String code) {
		Integer result = 0;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("mediaId", mediaId);
			parameter.put("version", version);
			parameter.put("code", code);
			List<String> list = getProducts();
			for (String product : list) {
				result = insert(product, AHA + "addClent", parameter);
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_ADD_CLENT, "产品"+product+"添加客户端识别码成功");
				if (result != 1) {
					return result;
				}
			}
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_ADD_CLENT, e);
			logger4j.error(e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService
	 * #updateClent(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@AccessResource(name = "ad-hard-admin-update-netbar-clent")
	public int updateClent(String productId, String mediaId, String version, String code, int id) {
		Integer result = 0;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("mediaId", mediaId);
			parameter.put("version", version);
			parameter.put("code", code);
			parameter.put("id", id);
			List<String> list = getProducts();
			for (String product : list) {
				result = update(product, AHA + "updateClent", parameter);
				if (result != 1) {
					return result;
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_UPDATE_CLENT, "产品"+product+"修改客户端识别码成功");
			}
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_UPDATE_CLENT, e);
			logger4j.error(e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService
	 * #chenkbatchAddNetbarIpAuthority()
	 */
	@Override
	@AccessResource(name = "ad-hard-admin-batch-add-netbarip")
	public void chenkbatchAddNetbarIpAuthority() throws AccessDeniedException {
		// (验证权限)

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService
	 * #getUnitsIsNetBar(java.lang.String)
	 */
	@Override
	public List<DropDownListData> getUnitsIsNetBar(String productId) throws AccessDeniedException {
		List<DropDownListData> list = null;
		try {
			list = selectList(productId, AHA + "getUnitsIsNetBar");
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_GET_UNITS_IS_NETBAR, "获取网吧的售卖类型陈功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_GET_UNITS_IS_NETBAR, e);
			logger4j.error(e);
		}
		return list;
	}

}
