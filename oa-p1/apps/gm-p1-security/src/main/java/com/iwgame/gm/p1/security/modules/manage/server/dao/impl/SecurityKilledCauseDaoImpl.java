/**      
* KilledCauseDaoImpl.java Create on 2012-11-15     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.iwgame.gm.p1.security.common.server.db.BaseService;
import com.iwgame.gm.p1.security.common.server.util.ConstantServer;
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.gm.p1.security.modules.manage.server.dao.SecurityKilledCauseDao;

/** 
 * @简述: 封杀原因dao实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期 2012-11-15 下午03:57:12 
 */
@Repository
public class SecurityKilledCauseDaoImpl extends BaseService implements SecurityKilledCauseDao{
	@Override
	public List<KilledCauseConfig> getKilledCauses(String productId,Map<String, Object> parameter) throws Exception{
		
		return selectList(productId, ConstantServer.SQL_MAPPER_KILLED_CAUSE_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}

	@Override
	public List<KilledCauseConfig> getByCauseTypeAndKilledType(
			String productId, Integer causeType, Integer killedType)
			throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("causeType", causeType);
		parameter.put("killedType", killedType);
		return selectList(productId, ConstantServer.SQL_MAPPER_KILLED_CAUSE_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}
	
	@Override
	public Integer countKilledCauses(String productId,Map<String, Object> parameter)
			throws Exception {
		
		return selectOne(productId, ConstantServer.SQL_MAPPER_KILLED_CAUSE_DAO+ConstantServer.SQL_ID_BASE_COUNT, parameter);
	}
	@Override
	public List<KilledCauseConfig> getKilledCausesByCauseType (String productId,
			Integer causeType) throws Exception{
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("causeType", causeType);
		return selectList(productId, ConstantServer.SQL_MAPPER_KILLED_CAUSE_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}

	@Override
	public List<KilledCauseConfig> getKilledCausesByKilledType(String productId,Integer killedType) throws Exception{
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("killedType", killedType);
		return selectList(productId, ConstantServer.SQL_MAPPER_KILLED_CAUSE_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}

	@Override
	public int insertKilledCauses(String productId,KilledCauseConfig killedCause)
			throws Exception {
		
		return insert(productId, ConstantServer.SQL_MAPPER_KILLED_CAUSE_DAO+ConstantServer.SQL_ID_BASE_INSERT, killedCause);
	}
	
	@Override
	public KilledCauseConfig getKilledCauseById(String productId,Integer id) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id", id);
		return selectOne(productId, ConstantServer.SQL_MAPPER_KILLED_CAUSE_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}
	
	@Override
	public int updateKilledCause(String productId,KilledCauseConfig killedCause)throws Exception {
		
		return update(productId, ConstantServer.SQL_MAPPER_KILLED_CAUSE_DAO+ConstantServer.SQL_ID_BASE_UPDATE, killedCause);
	}
	
	@Override
	public int deleteKilledCause(String productId,List<Integer> ids) throws Exception {
		
		return delete(productId, ConstantServer.SQL_MAPPER_KILLED_CAUSE_DAO+ConstantServer.SQL_ID_BASE_DELETE, ids);
	}
}
