/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： MNoticeDao.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.dao;

import java.util.Map;

/**
 * 类说明
 * @简述： 公告相关dao类
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-12 上午11:59:18
 */
public interface MNoticeDao {

	//添加公告发送日志
	public void addNoticeLog(Map<String ,Object> param);
}
