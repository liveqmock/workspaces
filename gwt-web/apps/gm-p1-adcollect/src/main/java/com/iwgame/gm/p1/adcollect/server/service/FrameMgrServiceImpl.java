/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： FrameMgrServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.iwgame.gm.p1.adcollect.modules.frame.shared.rpc.FrameMgrService;
import com.iwgame.gm.p1.adcollect.server.OperatorLogger;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.server.util.StringIsEmpty;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.FrameDataBase;
import com.iwgame.gm.p1.adcollect.shared.model.UseFrameDataBase;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.ui.grid.shared.GridHelper;
import com.iwgame.ui.grid.shared.model.PagingLoadResult;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @Description: 框架管理服务
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-13 下午2:23:25
 */
@NeedAuthorization
public class FrameMgrServiceImpl extends BaseService implements FrameMgrService, ExportQuery {

	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_FRAME_MGR, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(FrameMgrServiceImpl.class);

	private static final String AF = "ad.frame.";

	private OperatorLogger businessLogger;

	@Autowired
	public void setBusinessLogger(final OperatorLogger businessLogger) {
		this.businessLogger = businessLogger;
	}

	private String StringOper(String str) {
		if (null != str && (str.length() == 0 || str.equals(""))) {
			return null;
		} else {
			return str;
		}
	}
	
	@Override
	@AccessResource(name = "ad-frame-manage-export")
	public List<BaseModelData> query(Map<String, String> parameters) {
		List<BaseModelData> datas = null;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", null);
			parameter.put("offset", null);
			// 业务参数
			String productId = parameters.get("productId").toString();
			parameter.put("startDate", StringOper(parameters.get("startDate")));
			parameter.put("endDate", StringOper(parameters.get("endDate")));
			parameter.put("name", StringOper(parameters.get("name")));
			parameter.put("media", StringOper(parameters.get("media")));
			if (null != parameters.get("mediaType") && !(parameters.get("mediaType").toString()).equals("")) {
				String mt = parameters.get("mediaType").toString();
				parameter.put("mediaType", StringUtils.leftPad(mt, 2, '0'));
			}else{
				parameter.put("mediaType", null);
			}
			final List<FrameDataBase> list = selectList(productId, AF + "getFrameList", parameter);
			datas = new ArrayList<BaseModelData>();
			if(list.size()>0){
				// 时间格式转换
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				NumberFormat nfRMB = NumberFormat.getCurrencyInstance(Locale.CHINA);
				nfRMB.setMaximumFractionDigits(2);
				
			     NumberFormat nfNum4 = NumberFormat.getInstance(Locale.CHINA);
			     nfNum4.setMaximumFractionDigits(4);
				
				for (FrameDataBase frame : list) {
					final BaseModelData data = new BaseModelData();
					data.set("mediaName", StringIsEmpty.isEmpty(frame.getMediaName() + ""));
					data.set("name", StringIsEmpty.isEmpty(frame.getName() + ""));
					data.set("amount", StringIsEmpty.isEmpty(nfRMB.format(frame.getAmount())+ ""));
					data.set("rebate", StringIsEmpty.isEmpty(nfNum4.format(frame.getRebate())+ ""));
					data.set("securityDeposit", StringIsEmpty.isEmpty(nfRMB.format(frame.getSecurityDeposit())+ ""));
					if (null != frame.getStartTime()) {
						data.set("startTime", format.format(frame.getStartTime()) + "");
					}
					if (null != frame.getEndTime()) {
						data.set("endTime", format.format(frame.getEndTime()) + "");
					}
					data.set("balance", StringIsEmpty.isEmpty(nfRMB.format(frame.getBalance())+ ""));
					datas.add(data);
				}
			}
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_FRAME_DATA_EXPORT, "导出框架数据成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_FRAME_DATA_EXPORT, e);
			logger4j.error(e);
		}
		return datas;
	}

	@Override
	@AccessResource(name = "ad-frame-manage-add")
	public int addFrame(String productId, FrameDataBase newBase) throws AccessDeniedException {
		Integer result = 0;
		try {
			result = insert(productId, AF + "insertFrame", newBase);
			businessLogger.writeCreateLog(productId, newBase);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_FRAME_ADD_FRAME, "添加框架成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_FRAME_ADD_FRAME, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-frame-manage-query")
	public String getFrameList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException {
		String returnData = "";
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));
			// 业务参数

			parameter.put("startDate", loadConfig.<Date> get("startDate"));
			parameter.put("endDate", loadConfig.<Date> get("endDate"));
			parameter.put("name", loadConfig.<String> get("name"));
			parameter.put("media", loadConfig.<String> get("media"));
			if (null != loadConfig.<String> get("mediaType")) {
				String mt = loadConfig.<String> get("mediaType").toString();
				parameter.put("mediaType", StringUtils.leftPad(mt, 2, '0'));
			}

			final Integer total = selectOne(productId, AF + "getFrameListCount", parameter);
			final List<FrameDataBase> list = selectList(productId, AF + "getFrameList", parameter);

			final PagingLoadResult<FrameDataBase> result = new PagingLoadResult<FrameDataBase>();
			result.setRows(list);
			result.setTotal(total);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_FRAME_MGR_GET_LIST, "获取框架列表成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_FRAME_MGR_GET_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	public boolean checkFrameName(String productId, String name) {
		Boolean result = false;
		try {
			Integer num = selectOne(productId, AF + "checkFrameName", name);
			if (num == 0) {
				result = true;
			}
		} catch (Exception e) {
			logger4j.error("checkFrameName", e);
		}
		return result;
	}

	@Override
	public int checkFrameTime(String productId, Map<String, Object> parameter) {
		Integer result = 0;
		try {
			final List<FrameDataBase> list = selectList(productId, AF + "getFrameTimeByMediaId",
					parameter.get("mediaId").toString());
			Date start = (Date) parameter.get("start");
			Date end = (Date) parameter.get("end");
			for (FrameDataBase date : list) {
				if (date.getStartTime().getTime() <= start.getTime() && start.getTime() <= date.getEndTime().getTime()) {
					result++;
					continue;
				}
				if (date.getStartTime().getTime() <= end.getTime() && end.getTime() <= date.getEndTime().getTime()) {
					result++;
					continue;
				}
			}
		} catch (Exception e) {
			logger4j.error("getFrameTimeByMediaId", e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-frame-manage-update")
	public int updateFrame(String productId, FrameDataBase newBase) throws AccessDeniedException {
		Integer result = 0;
		try {
			int id = newBase.getId();
			final FrameDataBase oldBase = selectOne(productId, AF + "getFrameById", id);
			result = update(productId, AF + "updateFrameById", newBase);
			businessLogger.writeModifyLog(productId, oldBase, newBase);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_FRAME_UPDATE, "修改框架成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_FRAME_UPDATE, e);
			logger4j.equals(e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-frame-manage-del")
	public int delFrameByIds(String productId, String ids) throws AccessDeniedException {
		Integer result = 0;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("ids", ids);
			result = delete(productId, AF + "delFrameByIds", parameter);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_FRAME_DEL, "删除框架成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_FRAME_DEL, e);
			logger4j.equals(e);
		}
		return result;
	}

	@Override
	public List<DropDownListData> getFrameName(String productId, String mediaId) throws AccessDeniedException {
		List<DropDownListData> list = null;
		try {
			list = selectList(productId, AF + "getFrameName", mediaId);
		} catch (Exception e) {
			logger4j.equals(e);
		}
		return list;
	}

	@Override
	@AccessResource(name = "ad-frame-manage-use-frame-add")
	public int addUseFrame(String productId, UseFrameDataBase newBase) throws AccessDeniedException {
		Integer result = -1;
		try {
			selectOne(productId, AF + "insertUseFrame", newBase);
			result = newBase.getResult();
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_FRAME_ADD_USEFRAME, "添加使用框架成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_FRAME_ADD_USEFRAME, e);
			logger4j.error(e);
		}
		return result;
	}

	@Override
	@AccessResource(name = "ad-frame-manage-use-frame-getdate")
	public String getUseFrameList(String productId, BaseFilterPagingLoadConfig loadConfig) throws AccessDeniedException {
		String returnData = "";
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			// 分页参数
			parameter.put("limit", loadConfig.<Integer> get("limit"));
			parameter.put("offset", loadConfig.<Integer> get("offset"));
			parameter.put("sortField", loadConfig.<String> get("sortField"));
			parameter.put("sortDir", loadConfig.<String> get("sortDir"));
			// 业务参数

			parameter.put("name", loadConfig.<String> get("name"));
			parameter.put("media", loadConfig.<String> get("media"));
			if (null != loadConfig.<String> get("mediaType")) {
				String mt = loadConfig.<String> get("mediaType").toString();
				parameter.put("mediaType", StringUtils.leftPad(mt, 2, '0'));
			}

			final Integer total = selectOne(productId, AF + "getUseFrameListCount", parameter);
			final List<UseFrameDataBase> list = selectList(productId, AF + "getUseFrameList", parameter);

			final PagingLoadResult<UseFrameDataBase> result = new PagingLoadResult<UseFrameDataBase>();
			result.setRows(list);
			result.setTotal(total);
			result.setLimit(loadConfig.<Integer> get("limit"));
			result.setOffset(loadConfig.<Integer> get("offset"));
			returnData = GridHelper.buildResults(result);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_FRAME_MGR_GET0_USE_LIST, "获使用框架列表成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_FRAME_MGR_GET0_USE_LIST, e);
			logger4j.error(e);
		}
		return returnData;
	}

	@Override
	@AccessResource(name = "ad-frame-manage-use-frame-del")
	public int delUseFrameByIds(String productId, String ids) throws AccessDeniedException {
		Integer result = 0;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("ids", ids);
			result = delete(productId, AF + "delUseFrameByIds", parameter);
			logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_FRAME_DEL_USE, "删除使用框架成功");
		} catch (Exception e) {
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_FRAME_DEL_USE, e);
			logger4j.equals(e);
		}
		return result;
	}
	
}
