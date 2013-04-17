/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityAccountDaoImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.server.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iwgame.gm.p1.security.common.server.dao.KilledRecordDao;
import com.iwgame.gm.p1.security.common.server.db.BaseService;
import com.iwgame.gm.p1.security.common.server.util.ConstantServer;
import com.iwgame.gm.p1.security.common.shared.model.KilledRecord;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-19 下午05:49:38
 */
@Service
public class KilledRecordDaoImpl extends BaseService implements KilledRecordDao{
	
	@Override
	public List<KilledRecord> getRecords(String productId,Map<String, Object> parameter)
			throws Exception {
		return selectList(productId, ConstantServer.SQL_MAPPER_KILLED_RECORD_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}
	
	@Override
	public Integer countRecords(String productId,Map<String, Object> parameter) throws Exception {
		
		return selectOne(productId, ConstantServer.SQL_MAPPER_KILLED_RECORD_DAO+ConstantServer.SQL_ID_BASE_SELECT_COUNT, parameter);
	}
	
	@Override
	public Integer insert(String productId,KilledRecord kr) {
		Integer sn = insert(productId, 
				ConstantServer.SQL_MAPPER_KILLED_RECORD_DAO+ConstantServer.SQL_ID_BASE_INSERT, kr);
		return sn;
	}

	@Override
	public KilledRecord getRecordByUsername(String productId, String username)
			throws Exception {
		return selectOne(productId, ConstantServer.SQL_MAPPER_KILLED_RECORD_DAO+"selectByUsername", username);
	}

}
