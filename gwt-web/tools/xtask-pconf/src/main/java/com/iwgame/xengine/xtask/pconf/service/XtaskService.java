/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： XtaskService.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.pconf.service;

import java.io.IOException;


/**
 * 类说明
 * @简述： XtaskService接口
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午05:58:53
 */
public interface XtaskService {

	/**
	 * 生成配置策略文件
	 */
	public void makeFile() throws IOException;
}
