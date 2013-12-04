/****************************************************************
 *  系统名称  ： '消息任务系统-蜀门&醉逍遥'
 *  文件名    ： XtaskServiceImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.ucenter.service;

import java.util.Map;

/**
 * 类说明
 * 
 * @简述： WebService 访问类型的 XtaskService接口
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-4-10 下午05:58:53
 */
public interface XtaskWebService {

	/**
	 * 方法说明：水晶活动
	 * 
	 * @param activename
	 *            活动名称
	 * @param params
	 *            请求参数
	 */
	public void goldSend(String activename, Map<String, Object> params);

}
