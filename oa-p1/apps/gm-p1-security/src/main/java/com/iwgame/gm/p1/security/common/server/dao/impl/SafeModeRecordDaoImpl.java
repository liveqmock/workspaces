/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SafeModeRecordDaoImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.server.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iwgame.gm.p1.security.common.server.dao.SafeModeRecordDao;
import com.iwgame.gm.p1.security.common.server.db.BaseService;
import com.iwgame.gm.p1.security.common.server.util.ConstantServer;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeRecord;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 下午12:02:26
 */
@Service
public class SafeModeRecordDaoImpl extends BaseService implements SafeModeRecordDao {
	
	@Override
	public Integer insert(String productId,SafeModeRecord smr) throws Exception {
		Integer sn = insert(productId, 
				ConstantServer.SQL_MAPPER_SAFE_MODE_RECORD_DAO+ConstantServer.SQL_ID_BASE_INSERT, smr);
		return sn;
	}

	@Override
	public List<SafeModeRecord> getRecords(String productId,
			Map<String, Object> parameters) throws Exception {
		return selectList(productId, ConstantServer.SQL_MAPPER_SAFE_MODE_RECORD_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameters);
	}

	@Override
	public Integer countRecords(String productId, Map<String, Object> parameter)
			throws Exception {
		return selectOne(productId, ConstantServer.SQL_MAPPER_SAFE_MODE_RECORD_DAO+ConstantServer.SQL_ID_BASE_SELECT_COUNT, parameter);
	}

}
