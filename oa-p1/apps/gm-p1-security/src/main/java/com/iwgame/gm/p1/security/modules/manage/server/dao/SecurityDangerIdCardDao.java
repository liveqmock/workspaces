/**      
* SecurityDangetIdCardDao.java Create on 2012-11-19     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.server.dao;

import java.util.List;
import java.util.Map;


import com.iwgame.gm.p1.security.common.shared.model.DangerIdCard;

/** 
 * @简述: 高危身份证dao接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-19 下午02:28:20 
 */
public interface SecurityDangerIdCardDao {
	public List<DangerIdCard> getDangerIdCards(String productId, Map<String, Object> parameter) throws Exception;
	
	public Integer countDangerIdCards(String productId, Map<String, Object> parameter) throws Exception;
	
	public DangerIdCard getById(String productId, Integer id) throws Exception;
	
	public DangerIdCard getByIdCard(String productId,String idCard) throws Exception;
	
	public int batchInsert(String productId, List<DangerIdCard> idCards) throws Exception;
	
	public int insert(String productId, DangerIdCard idCard) throws Exception;
	
	public int delete(String productId, List<Integer> ids) throws Exception;
	
	public int update(String productId, DangerIdCard dangerIdCard) throws Exception;
	
}
