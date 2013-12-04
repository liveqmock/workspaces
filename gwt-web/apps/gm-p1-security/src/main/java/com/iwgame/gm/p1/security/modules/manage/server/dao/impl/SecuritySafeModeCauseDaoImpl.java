/**      
* SecuritySafeModeCauseDaoImpl.java Create on 2012-11-16     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.iwgame.gm.p1.security.common.server.db.BaseService;
import com.iwgame.gm.p1.security.common.server.util.ConstantServer;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeCauseConfig;
import com.iwgame.gm.p1.security.modules.manage.server.dao.SecuritySafeModeCauseDao;

/** 
 * @简述: 安全模式备注管理dao实现
 * @作者: 朱斌
 * @修改日期: 2012-11-16 下午03:51:05 
 * @版本: 1.0
 */
@Repository
public class SecuritySafeModeCauseDaoImpl extends BaseService implements SecuritySafeModeCauseDao {

	@Override
	public List<SafeModeCauseConfig> getSafeModeCauses(String productId,
			Map<String, Object> parameter) throws Exception {
		
		return selectList(productId, ConstantServer.SQL_MAPPER_SAFE_MODE_CAUSE_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}

	@Override
	public Integer countSafeModeCauses(String productId,Map<String, Object> parameter)
			throws Exception {
		
		return selectOne(productId, ConstantServer.SQL_MAPPER_SAFE_MODE_CAUSE_DAO+ConstantServer.SQL_ID_BASE_COUNT, parameter);
	}

	@Override
	public int insertSafeModeCauses(String productId,SafeModeCauseConfig safeModeCause)
			throws Exception {
		
		return insert(productId, ConstantServer.SQL_MAPPER_SAFE_MODE_CAUSE_DAO+ConstantServer.SQL_ID_BASE_INSERT, safeModeCause);
	}

	@Override
	public SafeModeCauseConfig getSafeModeCauseById(String productId,Integer id)
			throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id", id);
		return selectOne(productId, ConstantServer.SQL_MAPPER_SAFE_MODE_CAUSE_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}

	@Override
	public int updateSafeModeCause(String productId,SafeModeCauseConfig safeModeCause)
			throws Exception {
		
		return update(productId, ConstantServer.SQL_MAPPER_SAFE_MODE_CAUSE_DAO+ConstantServer.SQL_ID_BASE_UPDATE, safeModeCause);
	}

	@Override
	public int deleteSafeModeCause(String productId,List<Integer> ids) throws Exception {
		
		return delete(productId, ConstantServer.SQL_MAPPER_SAFE_MODE_CAUSE_DAO+ConstantServer.SQL_ID_BASE_DELETE, ids);
	}

}
