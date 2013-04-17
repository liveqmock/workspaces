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
import com.iwgame.gm.p1.security.common.shared.model.KilledRecord;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledCauseType;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityKilledRecordService;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;

/** 
 * @简述: 封杀记录记录导出数据查询实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-21 上午09:29:17 
 */
public class KilledRecordExportQuery implements ExportQuery {
	@Resource(name="securityKilledRecordServiceImpl")
	private SecurityKilledRecordService killedRecordService;
	
	private final Logger log4j = Logger.getLogger(KilledRecordExportQuery.class);
	
	@Override
	public List<BaseModelData> query(Map<String, String> parameters) {
		List<BaseModelData> modelDatas = new ArrayList<BaseModelData>();
		List<KilledRecord> resultList = null;
		try {
			resultList = killedRecordService.loadRecords4Export(parameters.get("pid"),parameters);
			if (resultList!=null) {
					for (KilledRecord record : resultList) {
						Map<String, Object> properties = AssemHelper.assemMapData(record, true, true, false);
						int type = record.getType();
						if (type==1) {
							properties.put("type","KILL");
						}else if (type==2) {
							properties.put("type","LOCK");
						}else if (type==3) {
							properties.put("type","ACTIVE");
						}
						Integer causeType = record.getCauseType();
						if (causeType.toString().equals(SecurityKilledCauseType.hackTool.getValue())) {
							properties.put("causeType","外挂");
						}else if (causeType.toString().equals(SecurityKilledCauseType.stealAccount.getValue())) {
							properties.put("causeType","盗号");
						}else if (causeType.toString().equals(SecurityKilledCauseType.accountSecurity.getValue())) {
							properties.put("causeType","账户安全");
						}else {
							properties.put("causeType","其他");
						}
						
						String handleStatus = record.getHandleStatus();
						//处理状态 0:完成 1:处理中 2:失败
						if ("0".equals(handleStatus)) {
							properties.put("handleStatus","完成");
						}else if ("1".equals(handleStatus)) {
							properties.put("handleStatus","处理中");
						}else if ("2".equals(handleStatus)) {
							properties.put("handleStatus","失败");
						}
						
						properties.put("optime", DateHelper.getDateString(record.getOptime(), "yyyy-MM-dd HH:mm:ss"));
						BaseModelData modelData = new BaseModelData(properties);
						modelDatas.add(modelData);
					}
			}
		} catch (Exception e) {
			log4j.error("导出封杀记录失败：", e);
		}finally{
			if (resultList!=null) {
				resultList.clear();
				resultList=null;
			}
		}
		return modelDatas;
	}

}
