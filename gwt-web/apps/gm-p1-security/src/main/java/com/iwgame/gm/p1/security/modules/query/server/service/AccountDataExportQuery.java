/**      
* AccountDataExportQuery.java Create on 2012-11-28     
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
import com.iwgame.gm.p1.security.common.shared.bean.AccountDocs;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityRegisInfoQueryService;
import com.iwgame.ui.grid.server.ExportQuery;
import com.iwgame.xmvp.shared.data.BaseModelData;

/** 
 * @简述: 账户资料导出数据查询实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-28 上午10:39:35 
 */
public class AccountDataExportQuery implements ExportQuery {

	@Resource(name="securityRegisInfoQueryServiceImpl")
	private SecurityRegisInfoQueryService queryService;
	
	private final Logger log4j = Logger.getLogger(AccountDataExportQuery.class);
	
	@Override
	public List<BaseModelData> query(Map<String, String> paramater) {
		List<BaseModelData> modelDatas = new ArrayList<BaseModelData>();
		List<AccountDocs> accountDocs = null;
		try {
			accountDocs = queryService.loadAccounts4Export(paramater);
			if (accountDocs!=null&&accountDocs.size()>0) {
				for (AccountDocs accountDoc : accountDocs) {
					Map<String, Object> properties = AssemHelper.assemMapData(accountDoc, true, true, false);
					BaseModelData modelData = new BaseModelData(properties);
					modelDatas.add(modelData);
				}
				return modelDatas;
			}
		} catch (Exception e) {
			log4j.error("导出账户失败", e);
		}finally{
			if (accountDocs!=null) {
				accountDocs.clear();
				accountDocs=null;
			}
		}
		return modelDatas;
	}

}
