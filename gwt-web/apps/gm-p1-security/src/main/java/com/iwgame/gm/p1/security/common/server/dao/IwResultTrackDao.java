/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： IwResultTrackDao.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.server.dao;

import java.util.List;
import java.util.Map;

import com.iwgame.gm.p1.security.common.shared.model.IwResultTrack;

/**
 * 类说明
 * @简述： 结果跟踪Dao
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-20 上午11:57:07
 */
public interface IwResultTrackDao {

	public Integer insert(String productId,IwResultTrack iwrt) throws Exception;
	
	public List<IwResultTrack> getRecords(String productId,Map<String, Object> parameter) throws Exception;

	public Integer countRecords(String productId,Map<String, Object> parameter) throws Exception;
	
	public Integer updateIwResultTrack(String productId,IwResultTrack iwrt) throws Exception;
}
