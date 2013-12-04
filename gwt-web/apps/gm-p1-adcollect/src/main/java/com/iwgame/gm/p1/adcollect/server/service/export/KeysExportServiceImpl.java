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
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.server.log.Logger;
import com.iwgame.xportal.common.server.security.access.annotation.AccessResource;
import com.iwgame.xportal.common.server.security.access.annotation.NeedAuthorization;

/**
 * @Description: 关键字列表导出
 * @author 李攀
 * @Version 2.1
 * @email  lipan@iwgame.com
 * @date 2012-12-11 上午11:56:12
 */
@NeedAuthorization
public class KeysExportServiceImpl  extends BaseService implements ExportQuery {
	private static Logger logger = Logger.getLogger(LoggerConstants.APPUNITNAME_KEYS, LoggerConstants.APPNAME);
	private final org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getLogger(KeysExportServiceImpl.class);
	private static final String NAMESPACE = "adcollect.keys.";

	@Override
	@AccessResource(name = "ad-keys-export")
	public List<BaseModelData> query(Map<String, String> parameters) throws AccessDeniedException{
		List<BaseModelData> datas = null;
		try {
			final Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("limit", null);
			parameter.put("offset", null);
             
			// 业务参数
			parameter.put("media", parameters.get("media"));
			parameter.put("adid", parameters.get("adid"));
			parameter.put("keyword", parameters.get("keyword"));
			final String level = parameters.get("level");
			
			String type = getSqlParamString(parameters.get("type"));
			parameter.put("type", type);

			String mark = getSqlParamString(parameters.get("mark"));
			parameter.put("mark", mark);

			final String productId = parameters.get("productId");
			// 查询类型1= 按关键字 ，0=按广告ID
			final String queryType = parameters.get("queryType");
			List<AdvertisementInfo> resultList = null;
			if (("1").equals(queryType)) {
				// level=0精确,level=1 模糊
				if (("0").equals(level)) {
					resultList = selectList(productId,NAMESPACE + "getRaceAcc", parameter);
				} else {
					resultList = selectList(productId, NAMESPACE + "getRace",parameter);
				}
			} else {
				resultList = selectList(productId, NAMESPACE + "getRaceById",parameter);
			}
			
			datas = new ArrayList<BaseModelData>();
			if (resultList.size() > 0) {
				// 时间格式转换
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				for (final AdvertisementInfo dataBase : resultList) {
					final BaseModelData data = new BaseModelData();
					data.set("addtime", StringIsEmpty.isEmpty(dataBase.getAddtime() + ""));
					data.set("type", StringIsEmpty.isEmpty(dataBase.getType() + ""));
					data.set("keyword", StringIsEmpty.isEmpty(dataBase.getKeyword() + ""));
					data.set("id", StringIsEmpty.isEmpty(dataBase.getId() + ""));
					data.set("mark", StringIsEmpty.isEmpty(dataBase.getMark() + ""));
					data.set("status", StringIsEmpty.isEmpty(("0".equals(dataBase.getStatus())?"禁用":"启用") + ""));
					data.set("url", StringIsEmpty.isEmpty(dataBase.getUrl() + ""));
					datas.add(data);
				}
				logger.writeSuccessfullyLog(LoggerConstants.ACTION_KEYS_EXPORT, "导出关键字成功");
			}
		} catch (final Exception e) {
			logger4j.error(e);
			logger.writeFailedLog(LoggerConstants.ACTION_KEYS_EXPORT, e);
		}
		return datas;
	}
   
	/**
	 * 将[a,b]处理成('a','b')
	 * @param string
	 * @return
	 */
	private String getSqlParamString(String initParam){
		String sqlParamString = "";
		if (initParam!= null) {
			sqlParamString += "(";
            //去除'[',']'
			initParam = initParam.substring(1, initParam.length()-1);
			if (initParam.contains(",")) {
		        String[] paramArr = initParam.split(",");
				for (int i = 0; i < paramArr.length; i++) {
				    sqlParamString += "'"+paramArr[i].trim()+"',";
				}
				sqlParamString = sqlParamString.substring(0, sqlParamString.length() - 1);
			}else {
				sqlParamString +="'"+initParam+"'";
			}
			sqlParamString += ")";	
        	}
		return sqlParamString;
	}
}

