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
import com.iwgame.gm.p1.security.common.shared.model.SafeModeRecord;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecuritySafeModeRecordService;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;

/** 
 * @简述: 安全模式操作日志导出查询实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-23 下午06:29:17 
 */
public class SafeModeRecordExportQuery implements ExportQuery {
	@Resource(name="securitySafeModeRecordServiceImpl")
	private SecuritySafeModeRecordService safeModeRecordService;
	
	private final Logger log4j = Logger.getLogger(SafeModeRecordExportQuery.class);
	@Override
	public List<BaseModelData> query(Map<String, String> parameters) {
		List<BaseModelData> modelDatas = new ArrayList<BaseModelData>();
		List<SafeModeRecord> resultList = null;
		try {
			resultList = safeModeRecordService.loadRecords4Export(parameters.get("pid"),parameters);
			if (resultList!=null) {
					for (SafeModeRecord record : resultList) {
						Map<String, Object> properties = AssemHelper.assemMapData(record, true, true, false);
						int type = record.getModeType();
						if (type==1) {
							properties.put("modeType", "增加安全模式");
						}else if (type==2) {
							properties.put("modeType", "解除安全模式");
						}
						
						properties.put("optime", DateHelper.getDateString(record.getOptime(), "yyyy-MM-dd HH:mm:ss"));
						BaseModelData modelData = new BaseModelData(properties);
						modelDatas.add(modelData);
					}
			}
		} catch (Exception e) {
			log4j.error("导出安全模式记录失败", e);
		}finally{
			if (resultList!=null) {
				resultList.clear();
				resultList=null;
			}
		}
		return modelDatas;
	}

}
