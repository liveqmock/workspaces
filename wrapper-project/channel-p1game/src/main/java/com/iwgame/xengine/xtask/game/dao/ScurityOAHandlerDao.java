/****************************************************************
 *  文件名     ： ScurityOAHandlerDao.java
 *  日期         :  2012-11-23
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.game.dao;

import java.util.Map;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-11-23上午11:52:10
 * @版本: v1.0
 */
public interface ScurityOAHandlerDao {

	// 更新封杀历史表中才处理状态 成功
	public int updateKilledRecordSuccess(Map<String, String> param);

	// 更新封杀历史表中才处理状态 失败
	public int updateKilledRecordFailed(Map<String, String> param);

	// 更新记录跟踪表的中成功数量
	public int updateResultTrackSuccess(String param);

	// 更新记录跟踪表的中失败数量
	public int updateResultTrackFailed(String param);

	// 玩家自助解冻记录新增到历史表
	public int insertKilledRecordByUnlook(Map<String, Object> param);

	// 玩家自助解除安全模式存入历史表
	public int insertSafeRecordByUnSafe(Map<String, Object> param);

	// 检查玩家帐号状态
	public Map<String, Integer> checkUserStatusByUsername(String username);

}
