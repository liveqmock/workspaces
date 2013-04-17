/**      
* SecurityDangerIdCardDaoImpl.java Create on 2012-11-19     
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
import com.iwgame.gm.p1.security.common.shared.model.DangerIdCard;
import com.iwgame.gm.p1.security.modules.manage.server.dao.SecurityDangerIdCardDao;

/** 
 * @简述: 高危身份证dao实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-19 下午02:38:49 
 */
@Repository
public class SecurityDangerIdCardDaoImpl extends BaseService implements SecurityDangerIdCardDao{
	
	@Override
	public List<DangerIdCard> getDangerIdCards(String productId,Map<String, Object> parameter)
			throws Exception {
		return selectList(productId, ConstantServer.SQL_MAPPER_DANGER_ID_CARD_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}
	
	@Override
	public Integer countDangerIdCards(String productId,Map<String, Object> parameter)
			throws Exception {
		return selectOne(productId, ConstantServer.SQL_MAPPER_DANGER_ID_CARD_DAO+ConstantServer.SQL_ID_BASE_COUNT, parameter);
	}

	@Override
	public DangerIdCard getById(String productId,Integer id) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id", id);
		return selectOne(productId, ConstantServer.SQL_MAPPER_DANGER_ID_CARD_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}

	@Override
	public DangerIdCard getByIdCard(String productId,String idCard) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("idCard", idCard);
		return selectOne(productId, ConstantServer.SQL_MAPPER_DANGER_ID_CARD_DAO+ConstantServer.SQL_ID_BASE_SELECT, parameter);
	}

	@Override
	public int batchInsert(String productId,List<DangerIdCard> idCards) throws Exception {
		
		return insert(productId, ConstantServer.SQL_MAPPER_DANGER_ID_CARD_DAO+"batchInsert", idCards);
	}

	@Override
	public int insert(String productId, DangerIdCard idCard) throws Exception {
		
		return insert(productId, ConstantServer.SQL_MAPPER_DANGER_ID_CARD_DAO+ConstantServer.SQL_ID_BASE_INSERT, idCard);
	}
	/**
	 * 逻辑删除,更新status为1
	 */
	@Override
	public int delete(String productId,List<Integer> ids) throws Exception {
		return update(productId, ConstantServer.SQL_MAPPER_DANGER_ID_CARD_DAO+ConstantServer.SQL_ID_BASE_DELETE, ids);
	}

	@Override
	public int update(String productId,DangerIdCard dangerIdCard) throws Exception {
		return update(productId, ConstantServer.SQL_MAPPER_DANGER_ID_CARD_DAO+ConstantServer.SQL_ID_BASE_UPDATE, dangerIdCard);
	}
	
}
