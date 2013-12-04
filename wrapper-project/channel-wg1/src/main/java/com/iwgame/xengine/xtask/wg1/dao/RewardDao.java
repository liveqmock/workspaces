/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： RewardDao.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.dao;

import java.util.List;
import java.util.Map;

import com.iwgame.xengine.xtask.wg1.model.GemConfig;
import com.iwgame.xengine.xtask.wg1.model.PropertyConfig;
import com.iwgame.xengine.xtask.wg1.model.Torch;

/**
 * 类说明
 * @简述： 发奖相关数据库操作DAO
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午05:53:33
 */
public interface RewardDao {
	 //保存发奖记录日志
	 public void saveResouceLog(Map<String, Object> paramMap);
	 
	 //查询资源发放策略
	 public List<PropertyConfig> getPropertyList(Map<String, Object> paraMap);
	 
	 //查询宝石发放策略
	 public List<GemConfig> getGemConfigList(Map<String,Object> paraMap);

	 //查询积分发放策略
	 public List<Torch> getTorchList(Map<String,Object> paraMap);
}
