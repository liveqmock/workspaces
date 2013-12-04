/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SafeModeRecordDao.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.server.dao;

import java.util.List;
import java.util.Map;

import com.iwgame.gm.p1.security.common.shared.model.SafeModeRecord;

/**
 * 类说明
 * @简述： 安全模式记录Dao
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午11:59:26
 */
public interface SafeModeRecordDao {

	public Integer insert(String productId,SafeModeRecord smr) throws Exception;
	
	public List<SafeModeRecord> getRecords(String productId,Map<String, Object> parameters) throws Exception;
	
	public Integer countRecords(String productId,Map<String, Object> parameter) throws Exception;
}
