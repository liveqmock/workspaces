/**      
* KilledCauseDao.java Create on 2012-11-15     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.server.dao;

import java.util.List;
import java.util.Map;

import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;

/** 
 * @简述: 封杀原因dao接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-15 下午03:56:50 
 */
public interface SecurityKilledCauseDao {
	
	public List<KilledCauseConfig> getKilledCauses(String productId, Map<String, Object> parameter) throws Exception;

	public Integer countKilledCauses(String productId, Map<String, Object> parameter) throws Exception;
	
	public List<KilledCauseConfig> getKilledCausesByCauseType(String productId,Integer causeType) throws Exception;
	
	public List<KilledCauseConfig> getKilledCausesByKilledType(String productId,Integer killedType) throws Exception;
	
	public List<KilledCauseConfig> getByCauseTypeAndKilledType(String productId,Integer causeType,Integer killedType) throws Exception;
	
	public int insertKilledCauses(String productId, KilledCauseConfig killedCause) throws Exception;
	
	public KilledCauseConfig getKilledCauseById(String productId, Integer id) throws Exception;

	public int updateKilledCause(String productId, KilledCauseConfig killedCause) throws Exception;
	
	public int deleteKilledCause(String productId, List<Integer> ids) throws Exception;
}
