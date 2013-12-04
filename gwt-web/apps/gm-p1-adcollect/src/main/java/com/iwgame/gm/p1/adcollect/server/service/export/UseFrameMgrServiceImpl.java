/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： UseFrameMgrServiceImpl.java
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

import com.iwgame.gm.p1.adcollect.server.service.BaseService;
import com.iwgame.gm.p1.adcollect.server.util.LoggerConstants;
import com.iwgame.gm.p1.adcollect.server.util.StringIsEmpty;
import com.iwgame.gm.p1.adcollect.shared.model.UseFrameDataBase;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @Description: 使用框架数据导出服务
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-19 上午9:38:11
 */
@NeedAuthorization
public class UseFrameMgrServiceImpl extends BaseService implements ExportQuery {
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_FRAME_MGR, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(UseFrameMgrServiceImpl.class);

	private static final String AF = "ad.frame.";
	
	@Override
	@AccessResource(name = "ad-frame-manage-use-frame-export")
	public List<BaseModelData> query(Map<String, String> parameters) {
		List<BaseModelData> datas = null;
		try {
			final String productId = parameters.get("productId").toString();
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("limit", null);
			parameter.put("offset", null);
			// 业务参数
			parameter.put("name", StringOper(parameters.get("name")));
			parameter.put("media", StringOper(parameters.get("media")));
			if (null != parameters.get("mediaType") && !(parameters.get("mediaType").toString()).equals("")) {
				String mt = parameters.get("mediaType").toString();
				parameter.put("mediaType", StringUtils.leftPad(mt, 2, '0'));
			}else{
				parameter.put("mediaType", null);
			}
			
			final List<UseFrameDataBase> list = selectList(productId, AF + "getUseFrameList", parameter);
			datas = new ArrayList<BaseModelData>();
			if (list.size() > 0) {
				// 时间格式转换
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				NumberFormat nfRMB = NumberFormat.getCurrencyInstance(Locale.CHINA);
				nfRMB.setMaximumFractionDigits(3);
				
				for (final UseFrameDataBase dataBase : list) {
					final BaseModelData data = new BaseModelData();
					data.set("mediaName", StringIsEmpty.isEmpty(dataBase.getMediaName() + ""));
					data.set("frameName", StringIsEmpty.isEmpty(dataBase.getFrameName() + ""));
					if (null != dataBase.getUpdateTime()) {
						data.set("updateTime", format.format(dataBase.getUpdateTime()) + "");
					}
					data.set("changes", StringIsEmpty.isEmpty(dataBase.getChanges() + ""));
					
					data.set("changeAmount", StringIsEmpty.isEmpty(nfRMB.format(dataBase.getChangeAmount()) + ""));
					
					data.set("balance", StringIsEmpty.isEmpty(nfRMB.format(dataBase.getBalance()) + ""));
					data.set("contractItmo", StringIsEmpty.isEmpty(dataBase.getContractItmo() + ""));
					data.set("effectTracking", StringIsEmpty.isEmpty(dataBase.getEffectTracking() + ""));

					datas.add(data);
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_HARD_FRAME_DATA_USE_EXPORT, "导出使用框架成功");
			}
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_HARD_FRAME_DATA_USE_EXPORT, e);
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

