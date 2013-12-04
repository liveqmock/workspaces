/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： PosServiceImpl.java
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
import com.iwgame.gm.p1.adcollect.shared.model.PositionDateBase;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @Description: 广告位数据导出
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-12 上午10:24:59
 */
@NeedAuthorization
public class PosServiceImpl extends BaseService implements ExportQuery {

	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_ADMIN_MGR_EXPORT,
			LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(PosServiceImpl.class);
	private static final String AHA = "ad.hard.admin.";

	private String resHost;

	public String getResHost() {
		return resHost;
	}

	public void setResHost(final String resHost) {
		this.resHost = resHost;
	}

	@Override
	@AccessResource(name = "ad-reports-hard-admin-pos-export")
	public List<BaseModelData> query(Map<String, String> parameters) throws AccessDeniedException {
		List<BaseModelData> datas = null;
		try {
			final String productId = parameters.get("productId");
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("limit", null);
			parameter.put("offset", null);

			parameter.put("key", StringOper(parameters.get("key")));
			parameter.put("id", StringOper(parameters.get("id")));
			parameter.put("name", StringOper(parameters.get("name")));
			parameter.put("adId", StringOper(parameters.get("adId")));
			parameter.put("media", StringOper(parameters.get("media")));

			parameter.put("units",
					parameters.get("units").equals("") ? null : Integer.parseInt(parameters.get("units")));

			String mediaType = StringOper(parameters.get("mediaType"));

			if (null != mediaType) {
				mediaType = StringUtils.leftPad(mediaType, 2, '0');
				parameter.put("mediaType", mediaType);
			} else {
				parameter.put("mediaType", null);
			}

			final List<PositionDateBase> list = selectList(productId, AHA + "getPositionList", parameter);
			datas = new ArrayList<BaseModelData>();
			if (list.size() > 0) {
				// 时间格式转换
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				NumberFormat nfRMB = NumberFormat.getCurrencyInstance(Locale.CHINA);
				nfRMB.setMaximumFractionDigits(2);

				for (final PositionDateBase dataBase : list) {
					final BaseModelData data = new BaseModelData();
					data.set("mediaName", StringIsEmpty.isEmpty(dataBase.getMediaName() + ""));
					data.set("name", StringIsEmpty.isEmpty(dataBase.getName() + ""));
					data.set("id", StringIsEmpty.isEmpty(dataBase.getId() + ""));
					String type = "";
					if (dataBase.getType() == 1) {
						type = "客户端推送";
					} else if (dataBase.getType() == 3) {
						type = "推送相关位置";
					} else {
						type = "独立广告位";
					}
					data.set("type", type);
					data.set("adId", StringIsEmpty.isEmpty(dataBase.getAdId() + ""));
					data.set("unitsName", StringIsEmpty.isEmpty(dataBase.getUnitsName() + ""));
					data.set("generalPrice", StringIsEmpty.isEmpty(nfRMB.format(dataBase.getGeneralPrice()) + ""));
					data.set("specialPrice", StringIsEmpty.isEmpty(nfRMB.format(dataBase.getSpecialPrice()) + ""));
					data.set("format", StringIsEmpty.isEmpty(dataBase.getFormat() + ""));
					data.set("explain", StringIsEmpty.isEmpty(dataBase.getExplain() + ""));
					data.set("remark", StringIsEmpty.isEmpty(dataBase.getRemark() + ""));
					if (null != dataBase.getAddTime()) {
						data.set("addTime", format.format(dataBase.getAddTime()) + "");
					}
					if (null != dataBase.getUpdateTime()) {
						data.set("updateTime", format.format(dataBase.getUpdateTime()) + "");
					}
					// 拚接 图例链接
					data.set("link", resHost + dataBase.getLegend());
					datas.add(data);
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_EXPORT_POS, "导出广告位成功");
			}
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_EXPORT_POS, e);
		}
		return datas;
	}

	private String StringOper(String str) {
		if (null != str && (str.length() == 0 || str.equals(""))) {
			return null;
		} else {
			return str;
		}
	}
}
