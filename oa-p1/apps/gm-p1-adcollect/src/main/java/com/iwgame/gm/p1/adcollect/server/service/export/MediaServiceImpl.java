/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： MediaServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.service.export;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;

import com.iwgame.gm.p1.adcollect.server.service.BaseService;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.server.util.StringIsEmpty;
import com.iwgame.gm.p1.adcollect.shared.model.MeidaDataBase;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @Description: 硬广媒体数据导出
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-11 上午11:56:12
 */
@NeedAuthorization
public class MediaServiceImpl  extends BaseService implements ExportQuery {
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_ADMIN_MGR_EXPORT, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(MediaServiceImpl.class);
	private static final String AHA = "ad.hard.admin.";

	@Override
	@AccessResource(name = "ad-reports-hard-admin-media-export")
	public List<BaseModelData> query(Map<String, String> parameters) throws AccessDeniedException{
		List<BaseModelData> datas = null;
		try {
			final String productId = parameters.get("productId");
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("limit", null);
			parameter.put("offset", null);

			parameter.put("id", parameters.get("id").equals("")?null:parameters.get("id"));
			parameter.put("name", parameters.get("name").equals("")?null:parameters.get("name"));
			parameter.put("type", !parameters.get("type").equals("")?Integer.parseInt(parameters.get("type")):null);
			parameter.put("sort", !parameters.get("sort").equals("")?Integer.parseInt(parameters.get("sort")):null);

			final List<MeidaDataBase> list = selectList(productId, AHA + "getMediaList", parameter);
			datas = new ArrayList<BaseModelData>();
			if (list.size() > 0) {
				// 时间格式转换
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				for (final MeidaDataBase dataBase : list) {
					final BaseModelData data = new BaseModelData();
					data.set("typeStr", StringIsEmpty.isEmpty(dataBase.getTypeStr() + ""));
					data.set("name", StringIsEmpty.isEmpty(dataBase.getName() + ""));
					data.set("id", StringIsEmpty.isEmpty(dataBase.getId() + ""));
					data.set("sortStr", StringIsEmpty.isEmpty(dataBase.getSortStr() + ""));
					data.set("subclass", StringIsEmpty.isEmpty(dataBase.getSubclass() + ""));
					data.set("adds", StringIsEmpty.isEmpty(dataBase.getAdds() + ""));
					data.set("data1", StringIsEmpty.isEmpty(dataBase.getData1() + ""));
					data.set("data2", StringIsEmpty.isEmpty(dataBase.getData2() + ""));
					data.set("data3", StringIsEmpty.isEmpty(dataBase.getData3() + ""));
					data.set("data4", StringIsEmpty.isEmpty(dataBase.getData4() + ""));
					data.set("count", StringIsEmpty.isEmpty(dataBase.getCount() + ""));
					data.set("contractCount", StringIsEmpty.isEmpty(dataBase.getContractCount() + ""));
					if (null != dataBase.getAddTime()) {
						data.set("addTime", format.format(dataBase.getAddTime()) + "");
					}
					if (null != dataBase.getUpdateTime()) {
						data.set("updateTime", format.format(dataBase.getUpdateTime()) + "");
					}

					datas.add(data);
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_ADMIN_EXPORT_MEDIS, "导出媒体成功");
			}
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_ADMIN_EXPORT_MEDIS, e);
		}
		return datas;
	}

}

