/**      
* SecurityLoginPassModifyRecordDao.java Create on 2012-11-20     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.server.dao;

import java.util.List;
import java.util.Map;

import com.iwgame.gm.p1.security.common.shared.model.LoginPassModifyRecord;

/** 
 * @简述: 账号改密记录查询dao
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-20 下午02:26:17 
 */
public interface SecurityLoginPassModifyRecordDao {
	public List<LoginPassModifyRecord> getRecords(String productId,Map<String, Object> parameters) throws Exception;
	
	public Integer countRecords(String productId,Map<String, Object> parameter) throws Exception;
}
