/**      
* LoginPassModifyRecordExportQuery.java Create on 2012-11-21     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.apache.log4j.Logger;


import com.iwgame.gm.p1.security.common.server.util.AssemHelper;
import com.iwgame.gm.p1.security.common.server.util.DateHelper;
import com.iwgame.gm.p1.security.common.shared.model.LoginPassModifyRecord;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityLoginPassModifyRecordService;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;

/** 
 * @简述: 账号改密记录导出数据查询实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-21 上午09:29:17 
 */
public class LoginPassModifyRecordExportQuery implements ExportQuery {
	@Resource(name="securityLoginPassModifyRecordServiceImpl")
	private SecurityLoginPassModifyRecordService modifyRecordService;
	
	private final Logger log4j = Logger.getLogger(LoginPassModifyRecordExportQuery.class);
	@Override
	public List<BaseModelData> query(Map<String, String> parameters) {
		List<BaseModelData> modelDatas = new ArrayList<BaseModelData>();
		List<LoginPassModifyRecord> resultList = null;
		try {
			resultList = modifyRecordService.loadRecords4Export(parameters.get("pid"),parameters);
			if (resultList!=null) {
				for (LoginPassModifyRecord record : resultList) {
					Map<String, Object> properties = AssemHelper.assemMapData(record, true, true, false);
					properties.put("modifyTime", DateHelper.getDateString(record.getModifyTime(), "yyyy-MM-dd HH:mm:ss"));
					BaseModelData modelData = new BaseModelData(properties);
					modelDatas.add(modelData);
				}
			}
		} catch (Exception e) {
			log4j.error("导出改密记录失败", e);
		}finally{
			if (resultList!=null) {
				resultList.clear();
				resultList=null;
			}
		}
		return modelDatas;
	}

}
