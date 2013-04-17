/**      
* SecurityLoginPassModifyRecordDaoImpl.java Create on 2012-11-20     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.server.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.iwgame.gm.p1.security.common.server.db.BaseService;
import com.iwgame.gm.p1.security.common.server.util.ConstantServer;
import com.iwgame.gm.p1.security.common.shared.model.LoginPassModifyRecord;
import com.iwgame.gm.p1.security.modules.query.server.dao.SecurityLoginPassModifyRecordDao;

/** 
 * @简述: 账号改密记录查询dao实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-20 下午02:31:22 
 */
@Repository
public class SecurityLoginPassModifyRecordDaoImpl extends BaseService implements
		SecurityLoginPassModifyRecordDao {
	@Override
	public List<LoginPassModifyRecord> getRecords(String productId,Map<String, Object> parameter)
			throws Exception {
		return selectList(productId, ConstantServer.SQL_MAPPER_LOGIN_PASS_MODIFY_RECORD_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}

	@Override
	public Integer countRecords(String productId,Map<String, Object> parameter) throws Exception {
		
		return selectOne(productId, ConstantServer.SQL_MAPPER_LOGIN_PASS_MODIFY_RECORD_DAO+ConstantServer.SQL_ID_BASE_COUNT, parameter);
	}

}
