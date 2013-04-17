/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： MMailDao.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.dao;

import java.util.Map;

/**
 * 类说明
 * @简述： 邮件操作相关
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-11 下午06:34:50
 */
public interface MMailDao {
	 //保存邮件发放记录
	 public void saveMailLog(Map<String, Object> paramMap);

}
