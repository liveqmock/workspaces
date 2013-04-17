/**      
* SecuritySafeModeCauseDao.java Create on 2012-11-16     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.server.dao;

import java.util.List;
import java.util.Map;

import com.iwgame.gm.p1.security.common.shared.model.SafeModeCauseConfig;

/** 
 * @简述: 安全模式备注管理dao接口
 * @作者: 朱斌
 * @修改日期: 2012-11-16 下午03:46:58 
 * @版本: 1.0
 */
public interface SecuritySafeModeCauseDao {
	public List<SafeModeCauseConfig> getSafeModeCauses(String productId, Map<String, Object> parameter) throws Exception;

	public Integer countSafeModeCauses(String productId, Map<String, Object> parameter) throws Exception;
	
	public int insertSafeModeCauses(String productId, SafeModeCauseConfig safeModeCause) throws Exception;
	
	public SafeModeCauseConfig getSafeModeCauseById(String productId,Integer id) throws Exception;

	public int updateSafeModeCause(String productId, SafeModeCauseConfig safeModeCause) throws Exception;
	
	public int deleteSafeModeCause(String productId, List<Integer> ids) throws Exception;
}
