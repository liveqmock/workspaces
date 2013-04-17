/****************************************************************
 *  系统名称  ： '广告系统任务处理'
 *  文件名    ： UpdateMonitorDao.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.dao;

import java.util.List;
import java.util.Map;

import com.iwgame.gm.p1.task.bean.CustomReport;
import com.iwgame.gm.p1.task.bean.MediaIp;



/**
 * 类说明
 * @简述： 广告任务DAO
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-11-6 下午02:21:03
 */
public interface AdcollectMonitorDao {
	
	public List<MediaIp> getIpList();
	
	public void addArea(Map<String, String> paramMap);
     
	//取得未处理的任务
	public List<CustomReport> getCustomReportList();
	
	//设置未处理任务状态为处理中
	public int updateStatus(Map<String, String> paraMap);

	//任务失败 重新恢复状态
	public int regainStatus(Map<String, String> paraMap);

	//无数据重置状态
	public int noResultRegainStatus(Map<String, String> paraMap);

	//将处理完成的任务结果写入数据库
	public int updateCustomResult(Map<String, String> paraMap);

}
