/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： SheduleServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service.export;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.AccessDeniedException;

import com.iwgame.gm.p1.adcollect.server.service.BaseService;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.server.util.StringIsEmpty;
import com.iwgame.gm.p1.adcollect.shared.model.SheduleDataBase;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @Description: 广告排期数据导出
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-12 上午11:42:28
 */
@NeedAuthorization
public class SheduleServiceImpl extends BaseService implements ExportQuery {

	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_ADMIN_MGR_EXPORT,
			LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(SheduleServiceImpl.class);
	private static final String AHA = "ad.hard.admin.";

	@Override
	@AccessResource(name = "ad-reports-hard-admin-shedule-export")
	public List<BaseModelData> query(Map<String, String> parameters) throws AccessDeniedException{
		List<BaseModelData> datas = null;
		try {
			final String productId = parameters.get("productId");
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("limit", null);
			parameter.put("offset", null);

			// 跳转默认参数
			parameter.put("sid", IsNullVoidString(parameters.get("sid")));

			parameter.put("mediaId", IsNullVoidString(parameters.get("mediaId")));
			parameter.put("positionId", IsNullVoidString(parameters.get("positionId")));
			parameter.put("contractId", IsNullVoidString(parameters.get("contractId")));
			parameter.put("groupId", IsNullVoidString(parameters.get("groupId")));
			parameter.put("groupName", IsNullVoidString(parameters.get("groupName")));
			parameter.put("adId", IsNullVoidString(parameters.get("adId")));
			parameter.put("adName", IsNullVoidString(parameters.get("adName")));
			parameter.put("beganTime", IsNullVoidString(parameters.get("startTime")));
			parameter.put("endTime", IsNullVoidString(parameters.get("endTime")));

			String mediaType = parameters.get("mediaType");
			if (null != mediaType && mediaType.equals("0")) {
				mediaType = StringUtils.leftPad(mediaType, 2, '0');
				parameter.put("mediaType", mediaType);
			} else {
				parameter.put("mediaType", null);
			}

			final List<SheduleDataBase> list = selectList(productId, AHA + "getSheduleList", parameter);
			datas = new ArrayList<BaseModelData>();
			if (list.size() > 0) {
				// 时间格式转换
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				final SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				NumberFormat nfRMB = NumberFormat.getCurrencyInstance(Locale.CHINA);
				nfRMB.setMaximumFractionDigits(2);
				NumberFormat nfRatio = NumberFormat.getPercentInstance(Locale.CHINA);
				nfRatio.setMaximumFractionDigits(2);
				for (final SheduleDataBase dataBase : list) {
					final BaseModelData data = new BaseModelData();
					data.set("mediaName", StringIsEmpty.isEmpty(dataBase.getMediaName() + ""));
					data.set("posName", StringIsEmpty.isEmpty(dataBase.getPosName() + ""));
					data.set("adId", StringIsEmpty.isEmpty(dataBase.getAdId() + ""));
					if (dataBase.getMaterialName() == null || dataBase.getMaterialName().trim().length() == 0) {
						data.set("materialShow", "未关联素材");
					} else {
						data.set("materialShow", StringIsEmpty.isEmpty(dataBase.getMaterialName() + ""));
					}
					data.set("contractItemno", StringIsEmpty.isEmpty(dataBase.getContractItemno() + ""));
					data.set("name", StringIsEmpty.isEmpty(dataBase.getName() + ""));
					if (null != dataBase.getStartTime()) {
						data.set("startTime", format1.format(dataBase.getStartTime()) + "");
					}
					if (null != dataBase.getEndTime()) {
						data.set("endTime", format1.format(dataBase.getEndTime()) + "");
					}
					data.set("id", StringIsEmpty.isEmpty(dataBase.getId() + ""));
					data.set("groupName", StringIsEmpty.isEmpty(dataBase.getGroupName())+ "");
					data.set("rebate", StringIsEmpty.isEmpty(dataBase.getRebate() + ""));
					data.set("unitsName", StringIsEmpty.isEmpty(dataBase.getUnitsName() + ""));
					data.set("generalPrice", StringIsEmpty.isEmpty(nfRMB.format(dataBase.getGeneralPrice()) + ""));
					data.set("specialPrice", StringIsEmpty.isEmpty(nfRMB.format(dataBase.getSpecialPrice()) + ""));
					String usedType = "";
					if (dataBase.getUsedType().equals("1")) {
						usedType = "购买";
					} else if (dataBase.getUsedType().equals("2")) {
						usedType = "配送";
					} else {
						usedType = "------";
					}
					data.set("usedTypeShow", usedType);
					data.set("distribuRatio", StringIsEmpty.isEmpty(nfRatio.format(dataBase.getDistribuRatio()) + ""));
					String typesShow = "";
					if (dataBase.getTypes() == 1) {
						typesShow = "正式";
					} else if (dataBase.getTypes() == 2) {
						typesShow = "赠送";
					} else if (dataBase.getTypes() == 3) {
						typesShow = "补量";
					} else if (dataBase.getTypes() == 4) {
						typesShow = "测试";
					} else {
						typesShow = "------";
					}
					data.set("typesShow", typesShow);
					data.set("adUrlShow", StringIsEmpty.isEmpty(dataBase.getAdUrl() + ""));
					data.set("sourceShow", StringIsEmpty.isEmpty(dataBase.getSource() + ""));

					if (null != dataBase.getAddTime()) {
						data.set("addTime", format.format(dataBase.getAddTime()) + "");
					}
					if (null != dataBase.getUpdateTime()) {
						data.set("updateTime", format.format(dataBase.getUpdateTime()) + "");
					}
					// 拚接 图例链接
					datas.add(data);
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_EXPORT_SHEDULE, "导出广告排期成功");
			}
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_EXPORT_SHEDULE, e);
		}
		return datas;
	}

	private String IsNullVoidString(String str) {
		if (null != str && (str.length() == 0 || str.equals("")))
			return null;
		else
			return str;
	}

}
